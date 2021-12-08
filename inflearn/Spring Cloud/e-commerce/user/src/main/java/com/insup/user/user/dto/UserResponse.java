package com.insup.user.user.dto;

import com.insup.user.order.OrderResponse;
import com.insup.user.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String userId;
    private String email;
    private String name;

    private List<OrderResponse> orderResponses;

    public UserResponse(Long id, String userId, String email, String name) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.name = name;
    }

    public static UserResponse of(User user) {
        return new UserResponse(user.getId(), user.getUserId(), user.getEmail(), user.getName());
    }
}
