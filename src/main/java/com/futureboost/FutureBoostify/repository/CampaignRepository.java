package com.futureboost.FutureBoostify.repository;

import com.futureboost.FutureBoostify.exception.CampaignNotFoundException;
import com.futureboost.FutureBoostify.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    /**
     * Retrieve a campaign by its unique identifier.
     *
     * @param campaignId The ID of the campaign to retrieve.
     * @return The Campaign object corresponding to the ID.
     */
    Optional<Campaign> getCampaignsById(Long campaignId) throws CampaignNotFoundException;
    Optional<Campaign> getCampaignByTitle(String title) throws CampaignNotFoundException;
    boolean existsByTitle(String title);

}
