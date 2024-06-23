package com.futureboost.FutureBoostify.model;

import com.futureboost.FutureBoostify.enums.CampaignType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "campaigns")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "is_group_campaign")
    private boolean groupCampaign;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Integer goal;

    @Column(name = "current_amount", nullable = false)
    private Integer currentAmount;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "media_files", columnDefinition = "jsonb")
    private String mediaFiles;

    @Column(nullable = false)
    private CampaignType type;

    @Column(nullable = false)
    private String location;

    @Column(name = "payment_methods", columnDefinition = "jsonb")
    private String paymentMethods;

    // Constructors, getters, and setters
}
