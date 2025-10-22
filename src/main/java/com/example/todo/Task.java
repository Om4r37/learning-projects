package com.example.todo;

import java.util.ArrayList;

public class Task {
    String id;
    String content;
    int userId;

    public Task() {
    }

    public Task(String id) {
        var row = DB.execute("SELECT * FROM tasks WHERE id = ?", id).getFirst();
        this.id = row.get("id").toString();
        this.content = row.get("content").toString();
        this.userId = Integer.parseInt(row.get("user_id").toString());
    }

    public Task(String id, String content, int userId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static ArrayList<Task> getTasks(String user_id) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        var tasksRows = DB.execute("select * from tasks where user_id = ?;", user_id);
        for (var row : tasksRows) {
            tasks.add(new Task(
                    row.get("id").toString(),
                    row.get("content").toString(),
                    Integer.parseInt(row.get("user_id").toString())
            ));
        }
        return tasks;
    }
}
