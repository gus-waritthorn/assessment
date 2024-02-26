package com.kbtg.bootcamp.posttest.user;

import java.util.List;

public class TicketInfo {
    private List<String> tickets;
    private int count;
    private int totalPrice;

    public TicketInfo(List<String> tickets, int count, int totalPrice) {
        this.tickets = tickets;
        this.count = count;
        this.totalPrice = totalPrice;
    }

    public List<String> getTickets() {
        return tickets;
    }

    public int getCount() {
        return count;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTickets(List<String> tickets) {
        this.tickets = tickets;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
