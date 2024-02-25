package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.userticket.UserTicket;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    private String id;

    private String username;
    private String role;

    @OneToMany(mappedBy = "user")
    private List<UserTicket> user;

    public User(String id, String username, String role, List<UserTicket> user) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public List<UserTicket> getUser() {
        return user;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUser(List<UserTicket> user) {
        this.user = user;
    }
}


