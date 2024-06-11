package model;

import java.sql.Timestamp;

public class User {
    long id;
    String phoneNumber;
    int gender;
    String role;
    Timestamp createdAt;
    String email;
    String fullName;
    String username;
    String password;

    public User(long id, String phoneNumber, int gender, String role, Timestamp createdAt, String email, String fullName, String username, String password) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.role = role;
        this.createdAt = createdAt;
        this.email = email;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
