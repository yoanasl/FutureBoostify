package com.futureboost.FutureBoostify.model;

import com.futureboost.FutureBoostify.enums.CampaignStatus;
import com.futureboost.FutureBoostify.enums.CampaignType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    @Column(name = "is_group_campaign", nullable = false)
//    private Boolean isGroupCampaign = false;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CampaignType type;

    private CampaignStatus status;

    @Column(nullable = false)
    private BigDecimal  goal;


    @Column(name = "current_amount")
    private BigDecimal currentAmount = BigDecimal.valueOf(0.0);

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


//    @CollectionTable(name = "campaign_media_files", joinColumns = @JoinColumn(name = "campaign_id"))
    @Column(name = "media_file")
    private List<String> mediaFiles;

    @Column(nullable = false)
    private String location;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "campaign_payment_methods",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id")
    )
    private List<PaymentMethod> paymentMethods;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SubCampaign> subCampaigns;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CampaignContribution> contributions;
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Constructors, getters, and setters

}
