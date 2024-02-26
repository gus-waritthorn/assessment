package com.kbtg.bootcamp.posttest.lottery;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class LotteryServiceTests {
    @Mock
    private LotteryRepository lotteryRepository;

    @InjectMocks
    private LotteryService lotteryService;

    @Test
    public void testAddLottery() {
        LotteryRequestDto request = new LotteryRequestDto();
        request.setTicket("123456");
        request.setPrice(100);
        request.setAmount(1);

        Lottery lottery = new Lottery();
        lottery.setTicket(request.getTicket());
        lottery.setPrice(request.getPrice());
        lottery.setAmount(request.getAmount());

        when(lotteryRepository.save(any(Lottery.class))).thenReturn(lottery);

        LotteryResponseDto result = lotteryService.addLottery(request);

        assertEquals("123456", result.getTicket());
    }

    @Test
    public void testGetLotteries() {
        Lottery lottery = new Lottery();
        lottery.setTicket("123456");
        lottery.setPrice(100);
        lottery.setAmount(1);

        when(lotteryRepository.findAll()).thenReturn(Arrays.asList(lottery));

        List<Lottery> result = lotteryService.getLotteries();

        assertEquals(1, result.size());
        assertEquals(lottery, result.get(0));
        verify(lotteryRepository).findAll();
    }
}
