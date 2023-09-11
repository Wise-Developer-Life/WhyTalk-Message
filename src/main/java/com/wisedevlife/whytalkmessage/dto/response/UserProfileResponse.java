package com.wisedevlife.whytalkmessage.dto.response;

import com.wisedevlife.whytalkmessage.model.UserModel;

public record UserProfileResponse(String id, String displayName, String profileImageUrl) {
    public static UserProfileResponse of(UserModel userModel) {
        return new UserProfileResponse(
                userModel.getUserId(), userModel.getDisplayName(), userModel.getProfileImageUrl());
    }
}
