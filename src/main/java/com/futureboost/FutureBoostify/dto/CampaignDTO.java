package com.futureboost.FutureBoostify.dto;

import com.futureboost.FutureBoostify.enums.CampaignType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class CampaignDTO {

    private String title;
    private String description;
    private Long userId;
    private boolean groupCampaign;
    private String status;
    private Integer goal;
    private Integer currentAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
//    private LocalDateTime createdAt;
    private String mediaFiles;
    private CampaignType type;
    private String location;
    private String paymentMethods;

}