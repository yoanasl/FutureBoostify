package com.futureboost.FutureBoostify.model;

import com.futureboost.FutureBoostify.enums.CampaignType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaigns")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String description;
//todo fix the user test
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_campaign_user"))
    private User user;

    @Column(name = "is_group_campaign")
    private boolean groupCampaign;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CampaignType type;

    @Column(nullable = false)
    private Long goal;

    @Column(name = "status")
    private String status;


    @Column(name = "current_amount", nullable = false)
    private Long currentAmount;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ElementCollection
    private List<String> mediaFiles;

    @Column(nullable = false)
    private String location;

    @Lob
    @Column(name = "payment_methods")
    private String paymentMethods;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Constructors, getters, and setters
}
