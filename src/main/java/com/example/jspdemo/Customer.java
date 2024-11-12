package com.example.jspdemo;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String hash;

    public Customer() {
    }

    public Customer(String username, String hash) {
        this.username = username;
        this.hash = hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer Customer = (Customer) o;
        return id == Customer.id && Objects.equals(username, Customer.username) && Objects.equals(hash, Customer.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, hash);
    }

    @Override
    public String toString() {
        return "userModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
