package com.wisedevlife.whytalkmessage.service.implement;

import com.wisedevlife.whytalkmessage.model.UserModel;
import com.wisedevlife.whytalkmessage.service.UserService;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserServiceImpl implements UserService {
    private final WebClient userServiceClient;

    public UserServiceImpl(WebClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public Optional<UserModel> getUserByUserId(String userId) {
        return userServiceClient
                .get()
                .uri(String.format("/user_profile/%s", userId))
                .retrieve()
                .bodyToMono(UserModel.class)
                .blockOptional();
    }
}
