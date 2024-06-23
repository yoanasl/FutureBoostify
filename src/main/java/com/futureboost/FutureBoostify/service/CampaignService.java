package com.futureboost.FutureBoostify.service;

import com.futureboost.FutureBoostify.dto.CampaignDTO;
import com.futureboost.FutureBoostify.model.Campaign;

public interface CampaignService {
    Campaign createCampaign(CampaignDTO campaignDto);
    Campaign getCampaignById(Long campaignId);
    void updateCampaign(Long campaignId, CampaignDTO campaignDto);
    void deleteCampaign(Long campaignId);
}

