package com.futureboost.FutureBoostify.repository;

import com.futureboost.FutureBoostify.model.CampaignContribution;
import com.futureboost.FutureBoostify.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignContributionRepository extends JpaRepository<CampaignContribution, Long> {
}
