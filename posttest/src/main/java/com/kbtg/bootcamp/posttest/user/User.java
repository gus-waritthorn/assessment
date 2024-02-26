package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.userticket.UserTicket;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "user", schema = "public")
public class User {
    @Id
    private String id;

    private String username;
    private String role;

    @OneToMany(mappedBy = "user")
    private List<UserTicket> userTickets;

    public User() {
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

    public List<UserTicket> getUserTickets() {
        return userTickets;
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

    public void setUserTickets(List<UserTicket> userTickets) {
        this.userTickets = userTickets;
    }
}


