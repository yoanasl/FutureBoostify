package com.futureboost.FutureBoostify.service;

import com.futureboost.FutureBoostify.dto.SubCampaignDTO;
import com.futureboost.FutureBoostify.model.SubCampaign;
import com.futureboost.FutureBoostify.repository.SubCampaignRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCampaignServiceImpl implements SubCampaignService {
    private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
    private final ModelMapper modelMapper;
    private final SubCampaignRepository subCampaignRepository;
    @Override
    @Transactional
    public SubCampaignDTO createSubCampaign(List<SubCampaignDTO> subCampaignDTO) {
        if(subCampaignDTO == null){
            throw new IllegalArgumentException("SubCampaignDTO cannot be null");
        }
        SubCampaign subCampaign = modelMapper.map(subCampaignDTO, SubCampaign.class);
        try {
            subCampaignRepository.save(subCampaign);
        } catch (Exception e) {
            log.error("Failed to save sub-campaign: " + subCampaign.getTitle(), e);
            throw new RuntimeException("Failed to save sub-campaign", e);
        }
        log.info("New sub-campaign created with ID: {}", subCampaign.getId());
        return modelMapper.map(subCampaign, SubCampaignDTO.class);
    }

    @Override
    public SubCampaignDTO saveSubCampaign(List<SubCampaignDTO> subCampaignDTO) {
        return null;
    }

    @Override
    @Transactional
    public SubCampaign createSubCampaignObject(SubCampaignDTO subCampaignDTO) {
        if(subCampaignDTO == null){
            throw new IllegalArgumentException("SubCampaignDTO cannot be null");
        }
        SubCampaign subCampaign = modelMapper.map(subCampaignDTO, SubCampaign.class);
        try {
              subCampaignRepository.save(subCampaign);
            log.info("New sub-campaign created with ID: {}", subCampaign.getId());
            return subCampaign;
        } catch (Exception e) {
            log.error("Failed to save sub-campaign: " + subCampaign.getTitle(), e);
            throw new RuntimeException("Failed to save sub-campaign", e);
        }
    }
}
