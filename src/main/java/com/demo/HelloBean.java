package com.demo;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("helloBean")
@RequestScoped
public class HelloBean {
    private String message =  "Hello from bean";
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String sayHello() {
        this.message = "hello from command button";
        return null;
    }
    public String readFromDB() {
        this.message = DB.execute("SELECT * FROM mytable").get(0).get("field").toString();
        return null;
    }
}
