package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.userticket.UserTicket;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/{userId}/lotteries/{ticketId}")
    public Map<String, String> buyLottery(@PathVariable String userId, @PathVariable Integer ticketId) {
        UserTicket userTicket = userService.buyLottery(userId, ticketId);
        Map<String, String > response = new HashMap<>();
        response.put("id", userTicket.getId().toString());
        return response;
    }

    @GetMapping("/users/{userId}/lotteries")
    public TicketInfo getAllMyLottery(@PathVariable String userId) {
        return userService.getAllMyLottery(userId);
    }

    @DeleteMapping("/users/{userId}/lotteries/{ticketId}")
    public Map<String, String> sellBack(@PathVariable String userId, @PathVariable Integer ticketId) {
        return userService.sellBack(userId, ticketId);
    }

}
