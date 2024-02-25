package com.kbtg.bootcamp.posttest.lottery;

import com.kbtg.bootcamp.posttest.user.User;
import com.kbtg.bootcamp.posttest.userticket.UserTicket;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "lottery")
public class Lottery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ticket;
    private Integer price;
    private Integer amount;
    private Boolean isAvailable;

    @OneToMany(mappedBy = "lottery")
    private List<UserTicket> lottery;

    public Lottery(Integer id, String ticket, Integer price, Integer amount, Boolean isAvailable, List<UserTicket> lottery) {
        this.id = id;
        this.ticket = ticket;
        this.price = price;
        this.amount = amount;
        this.isAvailable = isAvailable;
        this.lottery = lottery;
    }

    public Integer getId() {
        return id;
    }

    public String getTicket() {
        return ticket;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public List<UserTicket> getLottery() {
        return lottery;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public void setLottery(List<UserTicket> lottery) {
        this.lottery = lottery;
    }
}
