package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.userticket.UserTicket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuyLottery() {
        String userId = "2222222222";
        Integer ticketId = 1;

        UserTicket userTicket = new UserTicket();
        userTicket.setId(1);

        when(userService.buyLottery(userId, ticketId)).thenReturn(userTicket);

        Map<String, String> result = userController.buyLottery(userId, ticketId);

        assertEquals("1", result.get("id"));
    }

    @Test
    public void testGetAllMyLottery() {
    String userId = "2222222222";

    TicketInfo ticketInfo = new TicketInfo(Arrays.asList("123456"), 1, 100);

        when(userService.getAllMyLottery(userId)).thenReturn(ticketInfo);

        TicketInfo result = userController.getAllMyLottery(userId);

        assertEquals(1, result.getTickets().size());
        assertEquals(1, result.getCount());
        assertEquals(100, result.getTotalPrice());
    }

    @Test
    public void testSellBack() {
        String userId = "2222222222";
        Integer ticketId = 1;

        Map<String, String> response = new HashMap<>();
        response.put("ticket", "123456");
        when(userService.sellBack(userId, ticketId)).thenReturn(response);

        Map<String, String> result = userController.sellBack(userId, ticketId);

        assertEquals("123456", result.get("ticket"));
    }
}