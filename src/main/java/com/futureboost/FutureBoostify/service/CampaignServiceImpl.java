package com.futureboost.FutureBoostify.service;

import com.futureboost.FutureBoostify.auth.AuthenticationService;
import com.futureboost.FutureBoostify.dto.CampaignDTO;
import com.futureboost.FutureBoostify.enums.CampaignStatus;
import com.futureboost.FutureBoostify.exception.FileStorageException;
import com.futureboost.FutureBoostify.model.Campaign;
import com.futureboost.FutureBoostify.model.SubCampaign;
import com.futureboost.FutureBoostify.model.User;
import com.futureboost.FutureBoostify.repository.CampaignRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Transactional
@Service
@AllArgsConstructor

public class CampaignServiceImpl implements CampaignService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private  CampaignRepository campaignRepository;
    @Autowired
    private SubCampaignService subCampaignService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private FileStorageService fileStorageService;
    private UserDetailsService userDetailsService;

    private static final Logger log = LoggerFactory.getLogger(CampaignService.class);

    //todo check if campaign exist
    @Override
    @Transactional
    public CampaignDTO createCampaign(CampaignDTO campaignDTO, List<MultipartFile> mediaFiles) {
        if (campaignRepository.existsByTitle(campaignDTO.getTitle())) {
            throw new IllegalArgumentException("Campaign with this title already exists.");
        }

        Campaign newCampaign = Campaign.builder()
                .title(campaignDTO.getTitle())
                .description(campaignDTO.getDescription())
                .user(authenticationService.getCurrentUser())
                .type(campaignDTO.getType())
                .status(CampaignStatus.ACTIVE) //todo if your want it can be inactive ...
                .goal(campaignDTO.getGoal())
                .currentAmount(BigDecimal.ZERO)
                .startDate(campaignDTO.getStartDate())
                .endDate(campaignDTO.getEndDate())
                .location(campaignDTO.getLocation())
                .build();

        if (mediaFiles != null && !mediaFiles.isEmpty()) {
            List<String> mediaFilePaths = mediaFiles.stream()
                    .map(fileStorageService::storeFile)
                    .collect(Collectors.toList());
            newCampaign.setMediaFiles(mediaFilePaths);
        }
// todo when payment methods exit
//        if (campaignDTO.getPaymentMethodIds() != null && !campaignDTO.getPaymentMethodIds().isEmpty()) {
//            List<PaymentMethod> paymentMethods = paymentMethodRepository.findAllById(campaignDTO.getPaymentMethodIds());
//            newCampaign.setPaymentMethods(paymentMethods);
//        }

        if (campaignDTO.getSubCampaigns() != null && !campaignDTO.getSubCampaigns().isEmpty()) {
            List<SubCampaign> subCampaigns = campaignDTO.getSubCampaigns().stream()
                    .map(subCampaignDTO -> SubCampaign.builder()
                            .title(subCampaignDTO.getTitle())
                            .description(subCampaignDTO.getDescription())
                            .goal(subCampaignDTO.getGoal())
                            .currentAmount(BigDecimal.ZERO)
                            .campaign(newCampaign)
                            .build())
                    .collect(Collectors.toList());
            newCampaign.setSubCampaigns(subCampaigns);
        }

        Campaign savedCampaign = campaignRepository.save(newCampaign);

        return convertEntityToDto(savedCampaign);
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
//        if (campaignDto.getMediaFiles() != null) {
//            existingCampaign.setEndDate(campaignDto.getEndDate());
//        }

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
//                .currentAmount(campaignDto.getCurrentAmount())
                .startDate(campaignDto.getStartDate())
                .endDate(campaignDto.getEndDate())
                .location(campaignDto.getLocation())
//                .paymentMethods(findById)

                .build();


        return campaign;

    }
}
