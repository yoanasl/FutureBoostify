package com.futureboost.FutureBoostify.controller;

import com.futureboost.FutureBoostify.dto.CampaignDTO;
import com.futureboost.FutureBoostify.model.Campaign;
import com.futureboost.FutureBoostify.service.CampaignService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/campaigns")

public class CampaignController {
    private final CampaignService campaignService;
    private static final Logger log = LoggerFactory.getLogger(CampaignService.class);

    @PostMapping("/create")
    public ResponseEntity<CampaignDTO> createCampaign(@RequestBody @Valid CampaignDTO campaignDTO) {
        try {
            if (campaignDTO != null) {
                log.info("Creating a new campaign: `campaignDTO`");
                CampaignDTO createdCampaign = campaignService.createCampaign(campaignDTO);
                log.info("Campaign created successfully: `createdCampaign`");
                return ResponseEntity.ok(createdCampaign);
            } else {
                log.warn("Received null campaignDTO");
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            log.error("Error creating campaign: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
