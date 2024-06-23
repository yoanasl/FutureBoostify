package com.futureboost.FutureBoostify.enums;

public enum CampaignType {
    NEW_IDEA("New Business Idea"),
    EXISTING_BUSINESS("Existing Business Support");

    private final String typeName;

    CampaignType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
