package com.futureboost.FutureBoostify.dto;

import com.futureboost.FutureBoostify.enums.CampaignStatus;
import com.futureboost.FutureBoostify.enums.CampaignType;
import com.futureboost.FutureBoostify.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignDTO {

//    private Long id;
    private String title;
    private String description;
//    private Long userId;
//    private Boolean isGroupCampaign;
    private CampaignType type;
    private CampaignStatus status;
    private BigDecimal goal;
    private BigDecimal currentAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
    private String mediaFiles;
    private String location;
    private List<PaymentMethodDTO> paymentMethodIds;
//    private List<Long> ratings;
//    private List<CommentDTO> comments;
    private List<SubCampaignDTO> subCampaigns;
//    private List<User> contributions;

}