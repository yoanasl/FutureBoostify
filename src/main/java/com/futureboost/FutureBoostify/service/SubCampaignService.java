package com.futureboost.FutureBoostify.service;

import com.futureboost.FutureBoostify.dto.SubCampaignDTO;
import com.futureboost.FutureBoostify.model.SubCampaign;

import java.util.List;

public interface SubCampaignService {

    public SubCampaignDTO createSubCampaign(List<SubCampaignDTO> subCampaignDTO);
    public SubCampaignDTO saveSubCampaign(List<SubCampaignDTO> subCampaignDTO);
    public SubCampaign createSubCampaignObject(SubCampaignDTO subCampaignDTO);
}
