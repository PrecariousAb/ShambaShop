
package com.example.shambashop;

public class User {
    public String uid;
    public String name;
    public String email;
    public String phone;
    public String userType; // "consumer" or "farmer"

    // Empty constructor required for Firebase
    public User() {
    }

    public User(String uid, String name, String email, String phone, String userType) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
    }

    // Getters and setters
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
