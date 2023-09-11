package com.wisedevlife.whytalkmessage.service;

import com.wisedevlife.whytalkmessage.model.UserModel;
import java.util.Optional;

public interface UserService {
    Optional<UserModel> getUserByUserId(String userId);
}
