package com.insup.user.user.service;

import com.insup.user.user.dto.UserRequest;
import com.insup.user.user.dto.UserResponse;
import com.insup.user.user.domain.User;
import com.insup.user.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserRequest userRequest) {
        User user = new User(
                userRequest.getUserId(),
                userRequest.getEmail(),
                userRequest.getName(),
                passwordEncoder.encode(userRequest.getPassword())
        );
        User savedUser = userRepository.save(user);

        return UserResponse.of(savedUser);
    }

    public List<UserResponse> findAllUser() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(UserResponse::of)
                .collect(Collectors.toList());
    }

    public UserResponse findUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("찾는 사용자가 없습니다"));

        return UserResponse.of(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
