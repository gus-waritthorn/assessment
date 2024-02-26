package com.kbtg.bootcamp.posttest.lottery;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
public class LotteryController {
    private LotteryService lotteryService;
    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @PostMapping("/admin/lotteries")
    public ResponseEntity<LotteryResponseDto> addLottery(@Valid @RequestBody LotteryRequestDto request) {
        LotteryResponseDto response = lotteryService.addLottery(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/lotteries")
    public Map<String, List<String>> getLotteries() {
        List<Lottery> lotteries = lotteryService.getLotteries();
        List<String> ticketList = new ArrayList<>();
        for(Lottery lottery: lotteries) {
            ticketList.add(lottery.getTicket());
        }
        Map<String, List<String>> response = new HashMap<>();
        response.put("tickets", ticketList);

        return response;
    }
}
