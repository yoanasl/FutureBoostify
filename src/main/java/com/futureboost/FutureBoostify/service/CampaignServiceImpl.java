package com.futureboost.FutureBoostify.service;

import com.futureboost.FutureBoostify.auth.AuthenticationService;
import com.futureboost.FutureBoostify.dto.CampaignDTO;
import com.futureboost.FutureBoostify.enums.CampaignStatus;
import com.futureboost.FutureBoostify.model.Campaign;
import com.futureboost.FutureBoostify.repository.CampaignRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Transactional
@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private ModelMapper modelMapper;
    private CampaignRepository campaignRepository;
    private SubCampaignService subCampaignService;
    private AuthenticationService authenticationService;
    private static final Logger log = LoggerFactory.getLogger(CampaignService.class);

    @Override
    @Transactional
    public CampaignDTO createCampaign(CampaignDTO campaignDto) {
        Campaign newCampaign = convertDtoToEntity(campaignDto);

//        if (campaignDto.getUserId() != null) {
//            User user = userRepository.findById(campaignDto.getUserId())
//                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
//            newCampaign.setUser(user);
//        } else {
//            throw new IllegalArgumentException("User ID cannot be null");
//        }

        Campaign savedCampaign = campaignRepository.save(newCampaign);

        if (savedCampaign != null && savedCampaign.getId() != null) {
            log.info("Campaign created successfully with ID: {}", savedCampaign.getId());
            return convertEntityToDto(savedCampaign);
        } else {
            log.error("Failed to create the campaign");
            throw new RuntimeException("Failed to create the campaign");
        }
    }
    private CampaignDTO convertEntityToDto(Campaign campaign) {
        return modelMapper.map(campaign, CampaignDTO.class);
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
//        if (campaignDto.isGroupCampaign()) {
//            existingCampaign.setGroupCampaign(campaignDto.isGroupCampaign());
//        }
//        if (campaignDto.getStatus() != null || !campaignDto.getStatus().isEmpty()) {
//            existingCampaign.setStatus(campaignDto.getStatus());
//        }
//        if (campaignDto.getGoal() != null ) {
//            existingCampaign.setGoal(campaignDto.getGoal());
//        }
//        if (campaignDto.getCurrentAmount() != null ) {
//            existingCampaign.setCurrentAmount(campaignDto.getCurrentAmount());
//        }
        if (campaignDto.getStartDate() != null) {
            existingCampaign.setStartDate(campaignDto.getStartDate());
        }
        if (campaignDto.getEndDate() != null) {
            existingCampaign.setEndDate(campaignDto.getEndDate());
        }
        //todo fix
        if (campaignDto.getMediaFiles() != null) {
            existingCampaign.setEndDate(campaignDto.getEndDate());
        }

        if (campaignDto.getType() != null) {
            existingCampaign.setType(campaignDto.getType());
        }
        if (campaignDto.getLocation() != null || !campaignDto.getLocation().isEmpty()) {
            existingCampaign.setLocation(campaignDto.getLocation());
        }
//        if (campaignDto.getPaymentMethods() != null ) {
//            existingCampaign.setPaymentMethods(campaignDto.getPaymentMethods());
//        }
        campaignRepository.save(existingCampaign);
        return Optional.of(existingCampaign);
    }

    @Override
    public void deleteCampaign(Long campaignId) {
        Campaign existingCampaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new EntityNotFoundException("Campaign not found with id: " + campaignId));

        campaignRepository.deleteById(existingCampaign.getId());
    }


    private Campaign convertDtoToEntity(CampaignDTO campaignDto) {
        Campaign campaign = Campaign.builder()
                .title(campaignDto.getTitle())
                .description(campaignDto.getDescription())
                .user(authenticationService.getCurrentUser())
//                .isGroupCampaign(campaignDto.getIsGroupCampaign())
                .type(campaignDto.getType())
                .status(CampaignStatus.ACTIVE)
                .goal(campaignDto.getGoal())
                .currentAmount(campaignDto.getCurrentAmount())
                .startDate(campaignDto.getStartDate())
                .endDate(campaignDto.getEndDate())
//                .mediaFiles(campaignDto.getMediaFiles())
                .location(campaignDto.getLocation())
//                .paymentMethods(findById)
                .subCampaigns(campaignDto.getSubCampaigns().stream().map(camp
                        -> subCampaignService.createSubCampaignObject(camp)).toList())
                .build();

        return campaign;

    }
}
