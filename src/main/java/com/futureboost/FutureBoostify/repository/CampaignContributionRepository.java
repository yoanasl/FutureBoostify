package com.futureboost.FutureBoostify.repository;

import com.futureboost.FutureBoostify.model.CampaignContribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CampaignContributionRepository extends JpaRepository<CampaignContribution, Long> {
}
