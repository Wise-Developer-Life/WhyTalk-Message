package com.wisedevlife.whytalkmessage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private String userId;
    String displayName;
    String profileImageUrl;
}
