package com.futureboost.FutureBoostify.exception;

public class CampaignNotFoundException extends RuntimeException {
    CampaignNotFoundException(String message){
        super(message);
    }
}
