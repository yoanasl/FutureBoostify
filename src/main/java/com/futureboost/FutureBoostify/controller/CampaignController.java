package com.futureboost.FutureBoostify.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.futureboost.FutureBoostify.dto.CampaignDTO;
import com.futureboost.FutureBoostify.dto.JsonRequest;
import com.futureboost.FutureBoostify.model.Campaign;
import com.futureboost.FutureBoostify.service.CampaignService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/*import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class CampaignController {

    private final CampaignService campaignService;
    private final ObjectMapper objectMapper;
    private final Validator validator;

    @Autowired
    public CampaignController(CampaignService campaignService, ObjectMapper objectMapper, Validator validator) {
        this.campaignService = campaignService;
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<?> createCampaign(
            @RequestPart("campaignDTO") String campaignDTOJson,
            @RequestPart("mediaFiles") @NotNull List<MultipartFile> mediaFiles) {
        try {
            // Deserialize JSON to CampaignDTO
            CampaignDTO campaignDTO = objectMapper.readValue(campaignDTOJson, CampaignDTO.class);

            // Validate the deserialized CampaignDTO
            Set<ConstraintViolation<CampaignDTO>> violations = validator.validate(campaignDTO);
            if (!violations.isEmpty()) {
                String errorMessage = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining(", "));
                return ResponseEntity.badRequest().body("Validation failed: " + errorMessage);
            }

            // Check if mediaFiles is not null and not empty
            if (mediaFiles == null || mediaFiles.isEmpty()) {
                return ResponseEntity.badRequest().body("Media files must not be null or empty");
            }

            log.info("Creating a new campaign: {}", campaignDTO);

            // Call the service layer to handle the creation logic
            CampaignDTO createdCampaign = campaignService.createCampaign(campaignDTO, mediaFiles);

            log.info("Campaign created successfully: {}", createdCampaign);
            return ResponseEntity.ok(createdCampaign);

        } catch (Exception e) {
            log.error("Error creating campaign: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the campaign");
        }
    }
}
*/
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/campaigns")

public class CampaignController {
    private final CampaignService campaignService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(CampaignService.class);

    //    @PostMapping("/create")
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createCampaign
    (
            @RequestPart("campaignDTO") String campaignDTOString,
           @RequestPart("mediaFiles")List<MultipartFile> mediaFiles
    ) {

        ResponseEntity<CampaignDTO> validationResponse = validateCampaignDTO(campaignDTOString);
        if (validationResponse == null) {
            return ResponseEntity.badRequest().build();
        }
        CampaignDTO campaignDTO = validationResponse.getBody();


        try {
            if (mediaFiles != null) {
                log.info("Creating a new campaign: `campaignDTO`");
                CampaignDTO createdCampaign = campaignService.createCampaign(campaignDTO,  mediaFiles);
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
    private ResponseEntity<CampaignDTO> validateCampaignDTO(String campaignDTOString) {
        try {
            CampaignDTO campaignDTO = objectMapper.readValue(campaignDTOString, CampaignDTO.class);
            // Additional validation logic can be added here
            return ResponseEntity.ok(campaignDTO);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}

