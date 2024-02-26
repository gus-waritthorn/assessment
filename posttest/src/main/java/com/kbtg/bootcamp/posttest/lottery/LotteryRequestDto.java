package com.kbtg.bootcamp.posttest.lottery;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LotteryRequestDto {
    @NotNull(message = "Ticket is required")
    @Pattern(regexp = "\\d{6}", message = "Ticket must be 6 digits")
    private String ticket;

    @NotNull(message = "Price is required")
    @Digits(integer = 8, fraction = 0, message = "Price must be numeric with up to 8 digits in total")
    private Integer price;

    @NotNull(message = "Amount is required")
    @Digits(integer = 8, fraction = 0, message = "Price must be numeric with up to 8 digits in total")
    private Integer amount;

    public LotteryRequestDto() {
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

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
