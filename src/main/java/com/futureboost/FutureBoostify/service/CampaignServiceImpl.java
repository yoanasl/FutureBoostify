package com.futureboost.FutureBoostify.service;

import com.futureboost.FutureBoostify.dto.CampaignDTO;
import com.futureboost.FutureBoostify.model.Campaign;
import com.futureboost.FutureBoostify.repository.CampaignRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Transactional
@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private ModelMapper modelMapper;
    private CampaignRepository campaignRepository;

    @Override
    public Campaign createCampaign(CampaignDTO campaignDto) {
        return null;
    }

    @Override
    public Campaign getCampaignById(Long campaignId) {
        return campaignRepository.findById(campaignId)
                .orElseThrow(() -> new EntityNotFoundException("Campaign not found with id: " + campaignId));
    }

    @Override
    public void updateCampaign(Long campaignId, CampaignDTO campaignDto) {
        Campaign existingCampaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new EntityNotFoundException("Campaign not found with id: " + campaignId));

        existingCampaign.setTitle(campaignDto.getTitle());
        existingCampaign.setDescription(campaignDto.getDescription());
        // Update other fields as needed

        campaignRepository.save(existingCampaign);
    }

    @Override
    public void deleteCampaign(Long campaignId) {
        campaignRepository.deleteById(campaignId);
    }


    public Campaign convertDtoToEntity(CampaignDTO campaignDto) {
        return modelMapper.map(campaignDto, Campaign.class);
    }
}
