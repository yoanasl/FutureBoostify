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
public class CampaignDTO {

    private String title;
    private String description;
//    private Long userId;
    private boolean groupCampaign;
    private String status;
    private Long goal;
    private Long currentAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String mediaFiles;
    private CampaignType type;
    private String location;
    private String paymentMethods;

}