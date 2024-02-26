package com.kbtg.bootcamp.posttest.userticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserTicketRepository extends JpaRepository<UserTicket, Integer> {
    List<UserTicket> findByUserId(String userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserTicket ut WHERE ut.user.id = :userId AND ut.lottery.id = :lotteryId")
    void deleteByUserIdAndLotteryId(String userId, Integer lotteryId);
}


