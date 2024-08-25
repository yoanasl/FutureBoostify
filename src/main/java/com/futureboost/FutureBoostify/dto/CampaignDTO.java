package com.futureboost.FutureBoostify.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.futureboost.FutureBoostify.enums.CampaignStatus;
import com.futureboost.FutureBoostify.enums.CampaignType;
import com.futureboost.FutureBoostify.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignDTO {

    private String title;
    private String description;
    private CampaignType type;
    private BigDecimal goal;
    private Date startDate;
    private Date endDate;
    private String location;
    private List<PaymentMethodDTO> paymentMethodIds;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SubCampaignDTO> subCampaigns; // Optional

}


