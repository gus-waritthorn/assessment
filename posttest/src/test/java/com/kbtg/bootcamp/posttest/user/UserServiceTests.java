package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import com.kbtg.bootcamp.posttest.userticket.UserTicketRepository;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.userticket.UserTicket;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private LotteryRepository lotteryRepository;

    @Mock
    private UserTicketRepository userTicketRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testBuyLottery() {
        String userId = "2222222222";
        Integer ticketId = 1;

        User user = new User();
        user.setId("2222222222");
        user.setUsername("customer");
        user.setRole("CUSTOMER");

        Lottery lottery = new Lottery();
        lottery.setId(1);
        lottery.setTicket("123456");
        lottery.setPrice(100);
        lottery.setAmount(1);
        lottery.setAvailable(true);

        UserTicket userTicket = new UserTicket();
        userTicket.setUser(user);
        userTicket.setLottery(lottery);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(lotteryRepository.findById(ticketId)).thenReturn(Optional.of(lottery));
        when(userTicketRepository.save(any(UserTicket.class))).thenReturn(userTicket);

        UserTicket result = userService.buyLottery(userId, ticketId);
        
        assertEquals(userTicket.getUser().getUsername(), result.getUser().getUsername());
        assertEquals(userTicket.getLottery().getTicket(), result.getLottery().getTicket());
        verify(userRepository, times(1)).findById(userId);
        verify(lotteryRepository, times(1)).findById(ticketId);
        verify(userTicketRepository, times(1)).save(any(UserTicket.class));
    }

    @Test
    public void testGetAllMyLottery() {
        String userId = "2222222222";

        Lottery lottery = new Lottery();
        lottery.setId(1);
        lottery.setTicket("123456");
        lottery.setPrice(100);
        lottery.setAmount(1);

        User user = new User();
        user.setId("2222222222");
        user.setUsername("customer");
        user.setRole("CUSTOMER");

        List<UserTicket> userTickets = new ArrayList<>();

        UserTicket userTicket = new UserTicket();
        userTicket.setUser(user);
        userTicket.setLottery(lottery);
        userTickets.add(userTicket);

        when(userTicketRepository.findByUserId(userId)).thenReturn(userTickets);

        TicketInfo mockResult = new TicketInfo(Arrays.asList("123456"), 1, 100);
        TicketInfo result = userService.getAllMyLottery(userId);

        assertEquals(mockResult.getTickets().size(), result.getTickets().size());
    }

    @Test
    public void testSellBack() {
        String userId = "2222222222";
        Integer ticketId = 1;

        Lottery lottery = new Lottery();
        lottery.setTicket("123456");
        lottery.setPrice(100);
        lottery.setAmount(1);

        when(lotteryRepository.findById(ticketId)).thenReturn(Optional.of(lottery));

        userService.sellBack(userId, ticketId);

        verify(lotteryRepository, times(1)).findById(ticketId);
        verify(userTicketRepository, times(1)).deleteByUserIdAndLotteryId(userId, ticketId);
    }
}