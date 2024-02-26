package com.kbtg.bootcamp.posttest.userticket;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.user.User;
import jakarta.persistence.*;

@Entity
public class UserTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "lottery_id")
    private Lottery lottery;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserTicket() {
    }

    public Integer getId() {
        return id;
    }

    public Lottery getLottery() {
        return lottery;
    }

    public User getUser() {
        return user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLottery(Lottery lottery) {
        this.lottery = lottery;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
