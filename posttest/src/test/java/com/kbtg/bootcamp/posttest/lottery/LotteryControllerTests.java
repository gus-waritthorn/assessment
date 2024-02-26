package com.kbtg.bootcamp.posttest.lottery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LotteryControllerTests {

    @Mock
    private LotteryService lotteryService;

    @InjectMocks
    private LotteryController lotteryController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddLottery() {
        LotteryRequestDto request = new LotteryRequestDto();
        request.setTicket("123456");
        request.setPrice(100);
        request.setAmount(1);

        LotteryResponseDto response = new LotteryResponseDto("123456");

        when(lotteryService.addLottery(request)).thenReturn(response);

        ResponseEntity<LotteryResponseDto> result = lotteryController.addLottery(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testGetLotteries() {
        List<Lottery> lotteries = new ArrayList<>();
        Lottery lottery1 = new Lottery();
        lottery1.setTicket("123456");
        lotteries.add(lottery1);

        Lottery lottery2 = new Lottery();
        lottery2.setTicket("654321");
        lotteries.add(lottery2);

        when(lotteryService.getLotteries()).thenReturn(lotteries);

        Map<String, List<String>> result = lotteryController.getLotteries();

        assertEquals(2, result.get("tickets").size());
        assertEquals("123456", result.get("tickets").get(0));
        assertEquals("654321", result.get("tickets").get(1));
    }
}
