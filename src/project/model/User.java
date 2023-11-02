package project.model;

import project.config.Config;

import java.io.Serializable;

public class User implements Serializable {
    private Long userId;
    private String username;
    private String email;
    private String password;
    private boolean role = false;
    private boolean status = true;

    public User(String username, String email, String password, boolean role) {
        this.userId = Config.RandomId();
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isRole() {
        return role;
    }

    public boolean isStatus() {
        return status;
    }


    @Override
    public String toString() {
        return "userId: " + userId +
                ",\n username: '" + username +
                ",\n email: '" + email +
                ",\n role: " + role +
                ",\n status: " + status + "\n";
    }
}
