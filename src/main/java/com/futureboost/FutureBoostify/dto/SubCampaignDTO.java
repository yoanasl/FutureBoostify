package com.futureboost.FutureBoostify.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubCampaignDTO {
    private Long campaignId;
    private String title;
    private String description;
    private Integer goal;
    private Integer currentAmount;
}
