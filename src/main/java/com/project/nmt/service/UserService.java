package com.project.nmt.service;

import com.project.nmt.model.User;
import com.project.nmt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User getOneById(Long id) {
        return userRepository.findById(id).get();
    }
}
