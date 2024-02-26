package com.kbtg.bootcamp.posttest.lottery;

import com.kbtg.bootcamp.posttest.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LotteryService {
    private LotteryRepository lotteryRepository;

    public LotteryService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    public LotteryResponseDto addLottery(LotteryRequestDto request) {
        Lottery lottery = new Lottery();
        lottery.setTicket(request.getTicket());
        lottery.setPrice(request.getPrice());
        lottery.setAmount(request.getAmount());
        lotteryRepository.save(lottery);
        return new LotteryResponseDto(lottery.getTicket());
    }

    public List<Lottery> getLotteries() {
        return lotteryRepository.findAll();
    }
}
