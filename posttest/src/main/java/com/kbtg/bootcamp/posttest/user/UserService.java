package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.exception.BadRequestException;
import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import com.kbtg.bootcamp.posttest.userticket.UserTicket;
import com.kbtg.bootcamp.posttest.userticket.UserTicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService {

    private UserRepository userRepository;
    private LotteryRepository lotteryRepository;

    private UserTicketRepository userTicketRepository;

    public UserService(UserRepository userRepository, LotteryRepository lotteryRepository, UserTicketRepository userTicketRepository) {
        this.userRepository = userRepository;
        this.lotteryRepository = lotteryRepository;
        this.userTicketRepository = userTicketRepository;
    }

    @Transactional
    public UserTicket buyLottery(String userId, Integer ticketId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Lottery> optionalLottery = lotteryRepository.findById(ticketId);

        if (optionalUser.isEmpty() || optionalLottery.isEmpty()) {
            throw new BadRequestException("Invalid user or lottery");
        }
        User user = optionalUser.get();
        Lottery lottery = optionalLottery.get();

        UserTicket userTicket = new UserTicket();
        userTicket.setUser(user);
        userTicket.setLottery(lottery);

        userTicketRepository.save(userTicket);

        lottery.setAvailable(false);
        lotteryRepository.save(lottery);

        return userTicket;
    };

    public TicketInfo getAllMyLottery(String userId) {
        List<UserTicket> userTickets = userTicketRepository.findByUserId(userId);
        ArrayList<Integer> ticketIdsList = new ArrayList<>();
        ArrayList<String> ticketNoList = new ArrayList<>();
        for (UserTicket userTicket: userTickets) {
            ticketIdsList.add(userTicket.getLottery().getId());
            ticketNoList.add(userTicket.getLottery().getTicket());
        }
        List<Lottery> lotteries = lotteryRepository.findAllById(ticketIdsList);

        Integer count = 0;
        Integer totalPrice = 0;
        for (Lottery lottery: lotteries) {
            count += lottery.getAmount();
            totalPrice += lottery.getPrice();
        }

        return new TicketInfo(ticketNoList, count, totalPrice);
    }

    public Map<String, String> sellBack(String userId, Integer ticketId) {
        Optional<Lottery> optionalLottery = lotteryRepository.findById(ticketId);

        if(optionalLottery.isEmpty()) {
            throw new NotFoundException("Invalid lottery");
        }
        Lottery lottery = optionalLottery.get();
        userTicketRepository.deleteByUserIdAndLotteryId(userId, ticketId);

        Map<String, String> response = new HashMap<>();
        response.put("ticket", lottery.getTicket());
        return response;
    }
}
