package com.futureboost.FutureBoostify.service;

import com.futureboost.FutureBoostify.dto.SubCampaignDTO;
import com.futureboost.FutureBoostify.model.SubCampaign;

public interface SubCampaignService {

    public SubCampaignDTO createSubCampaign(SubCampaignDTO subCampaignDTO);
    public SubCampaign createSubCampaignObject(SubCampaignDTO subCampaignDTO);
}
