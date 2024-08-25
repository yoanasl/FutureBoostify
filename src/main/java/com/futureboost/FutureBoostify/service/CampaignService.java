package com.futureboost.FutureBoostify.service;

import com.futureboost.FutureBoostify.dto.CampaignDTO;
import com.futureboost.FutureBoostify.exception.CampaignNotFoundException;
import com.futureboost.FutureBoostify.model.Campaign;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface CampaignService {

    /**
     * Create a new campaign based on the provided CampaignDTO.
     *
     * @param campaignDto The CampaignDTO object containing campaign details.
     * @return The created Campaign object.
     */
    @Transactional
    CampaignDTO createCampaign(@Valid CampaignDTO campaignDto, List<MultipartFile> mediaFiles);


    /**
     * Update an existing campaign with the provided details.
     *
     * @param campaignId  The ID of the campaign to update.
     * @param campaignDto The CampaignDTO object containing updated campaign details.
     */

    @Transactional
    Optional<Campaign> updateCampaign(Long campaignId, @Valid CampaignDTO campaignDto);

    /**
     * Delete a campaign by its unique identifier.
     *
     * @param campaignId The ID of the campaign to delete.
     */
    @Transactional
    void deleteCampaign(Long campaignId) throws CampaignNotFoundException;
}

