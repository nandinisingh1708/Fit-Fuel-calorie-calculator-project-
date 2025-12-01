package com.calorie.calculator.service;

import com.calorie.calculator.model.User;
import com.calorie.calculator.payload.request.SignupRequest;
import com.calorie.calculator.repository.UserRepository;
import com.calorie.calculator.payload.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(SignupRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setAge(request.getAge());
        user.setWeight(request.getWeight());
        user.setHeight(request.getHeight());
        user.setGender(request.getGender());
        user.setActivityLevel(request.getActivityLevel());
        return userRepository.save(user);
    }

    public UserResponse toUserResponse(User user) {
        UserResponse resp = new UserResponse();
        resp.setId(user.getId());
        resp.setUsername(user.getUsername());
        resp.setEmail(user.getEmail());
        resp.setFullName(user.getFullName());
        resp.setAge(user.getAge());
        resp.setHeight(user.getHeight());
        resp.setWeight(user.getWeight());
        resp.setGender(user.getGender());
        resp.setActivityLevel(user.getActivityLevel());
        resp.setCreatedAt(user.getCreatedAt());
        resp.setUpdatedAt(user.getUpdatedAt());
        return resp;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public java.util.List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
