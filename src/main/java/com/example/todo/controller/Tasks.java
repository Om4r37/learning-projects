package com.example.todo.controller;

import com.example.todo.CookieSigner;
import com.example.todo.DB;
import com.example.todo.Task;
import com.example.todo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Tasks {
    @GetMapping("/tasks")
    public String tasks(Model model, @CookieValue(name = "id", defaultValue = "0") String id) {
        if (id.equals("0") || !CookieSigner.verify(id.split(":")[0], id.split(":")[1])) return "redirect:/";
        User user = new User(id);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("tasks", Task.getTasks(id.split(":")[0]));
        return "tasks";
    }

    @GetMapping("/add-task")
    public String addTask(Model model, @CookieValue(name = "id", defaultValue = "0") String id) {
        if (id.equals("0") || !CookieSigner.verify(id.split(":")[0], id.split(":")[1])) return "redirect:/";
        User user = new User(id);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("task", new Task());
        return "addTask";
    }

    @PostMapping("/add-task")
    public static String addTask(@ModelAttribute("task") Task task, @CookieValue(name = "id", defaultValue = "0") String id) {
        if (id.equals("0") || !CookieSigner.verify(id.split(":")[0], id.split(":")[1])) return "redirect:/";
        String query = "INSERT INTO tasks (content, user_id) VALUES (?, ?)";
        DB.execute(query, task.getContent(), id.split(":")[0]);
        return "redirect:/tasks";
    }

    @GetMapping("/delete-task")
    public String deleteTask(@RequestParam String taskId, @CookieValue(name = "id", defaultValue = "0") String id) {
        if (id.equals("0") || !CookieSigner.verify(id.split(":")[0], id.split(":")[1])) return "redirect:/";
        String query = "DELETE FROM tasks WHERE id = ? AND user_id = ?";
        DB.execute(query, taskId, id);
        return "redirect:/tasks";
    }

    @GetMapping("/update-task")
    public String updateTask(@RequestParam String taskId, Model model, @CookieValue(name = "id", defaultValue = "0") String id) {
        if (id.equals("0") || !CookieSigner.verify(id.split(":")[0], id.split(":")[1])) return "redirect:/";
        User user = new User(id);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("task", new Task(taskId));
        return "editTask";
    }

    @PostMapping("/update-task")
    public static String updateTask(@ModelAttribute("task") Task task, @CookieValue(name = "id", defaultValue = "0") String id) {
        if (id.equals("0") || !CookieSigner.verify(id.split(":")[0], id.split(":")[1])) return "redirect:/";
        String query = "UPDATE tasks SET content = ? WHERE user_id = ? AND id = ?";
        DB.execute(query, task.getContent(), id.split(":")[0], task.getId());
        return "redirect:/tasks";
    }
}
