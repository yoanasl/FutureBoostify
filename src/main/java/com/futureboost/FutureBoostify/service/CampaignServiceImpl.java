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

import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private ModelMapper modelMapper;
    private CampaignRepository campaignRepository;
    @Override
    public Campaign createCampaign(CampaignDTO campaignDto) {
        Campaign newCampaign = convertDtoToEntity(campaignDto);
        return campaignRepository.save(newCampaign);
    }

    @Override
    public Optional<Campaign> updateCampaign(Long campaignId, CampaignDTO campaignDto) {
        Campaign existingCampaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new EntityNotFoundException("Campaign not found with id: " + campaignId));

        if (campaignDto.getTitle() == null || !campaignDto.getTitle().isEmpty()) {
            existingCampaign.setTitle(campaignDto.getTitle());
        }
        if (campaignDto.getDescription() != null || !campaignDto.getDescription().isEmpty()) {
            existingCampaign.setDescription(campaignDto.getDescription());
        }
        if (campaignDto.isGroupCampaign()) {
            existingCampaign.setGroupCampaign(campaignDto.isGroupCampaign());
        }
        if (campaignDto.getStatus() != null || !campaignDto.getStatus().isEmpty()) {
            existingCampaign.setStatus(campaignDto.getStatus());
        }
        if (campaignDto.getGoal() != null ) {
            existingCampaign.setGoal(campaignDto.getGoal());
        }
        if (campaignDto.getCurrentAmount() != null ) {
            existingCampaign.setCurrentAmount(campaignDto.getCurrentAmount());
        }
        if (campaignDto.getStartDate() != null ) {
            existingCampaign.setStartDate(campaignDto.getStartDate());
        }
        if (campaignDto.getEndDate() != null ) {
            existingCampaign.setEndDate(campaignDto.getEndDate());
        }
        //todo fix
        if (campaignDto.getMediaFiles() != null ) {
            existingCampaign.setEndDate(campaignDto.getEndDate());
        }

        if (campaignDto.getType() != null ) {
            existingCampaign.setType(campaignDto.getType());
        }
        if (campaignDto.getLocation() != null || !campaignDto.getLocation().isEmpty()) {
            existingCampaign.setLocation(campaignDto.getLocation());
        }
        if (campaignDto.getPaymentMethods() != null ) {
            existingCampaign.setPaymentMethods(campaignDto.getPaymentMethods());
        }
        campaignRepository.save(existingCampaign);
        return Optional.of(existingCampaign);
    }

    @Override
    public void deleteCampaign(Long campaignId) {
        Campaign existingCampaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new EntityNotFoundException("Campaign not found with id: " + campaignId));

        campaignRepository.deleteById(existingCampaign.getId());
    }


    public Campaign convertDtoToEntity(CampaignDTO campaignDto) {
        return modelMapper.map(campaignDto, Campaign.class);
    }
}
