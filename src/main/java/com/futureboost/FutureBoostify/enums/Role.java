package com.futureboost.FutureBoostify.enums;
/*todo
Guest: If users are allowed to browse campaigns without signing up or if sign-up
is required but initial access is limited to browsing.

* Donor: If they intend to donate funds, services, or crypto to campaigns.
Creator: If they plan to create and manage campaigns.
Service Provider: If they offer services to campaigns.
Reviewer: If they are responsible for reviewing and approving campaigns.
Admin: If they have administrative privileges to manage the platform.




User:

Every individual who registers on the platform starts with the role of "User."
This role allows basic functionalities such as browsing campaigns, viewing details,
 and interacting through comments and ratings.
Creator:

Users who initiate campaigns to seek funding for their projects or ideas.
They have additional privileges like creating, managing, and editing their campaigns,
uploading media files, and communicating updates to backers.
Supporter/Backer:

Users who contribute financially to support campaigns.
They can browse campaigns, donate funds, choose rewards (if applicable),
and interact with creators through comments and messages.
* */

public enum Role {
    USER("User"),
    CAMPAIGN_CREATOR("Campaign Creator"),
    CONTRIBUTOR("Campaign Supporter"),
    ADMIN("Administrator");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}

