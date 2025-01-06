package com.immate.immate.entity.vote;

import com.immate.immate.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VoteEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stock_vote_id")
    private StockVote stockVote;

    @ManyToOne
    @JoinColumn(name = "vote_stock_id")
    private VoteStock voteStock;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private VoteOption voteOption;

    private LocalDateTime votedAt;

    public enum VoteOption {
        BUY, HOLD, SELL
    }
} 