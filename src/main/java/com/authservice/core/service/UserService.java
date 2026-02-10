package com.authservice.core.service;

import com.authservice.core.dto.UserDTO;
import com.authservice.core.mapper.UserMapper;
import com.authservice.core.repository.UserRepository;
import com.authservice.core.model.User;
import com.authservice.core.util.AppConstants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity getUser(int userId) {
        return new ResponseEntity<>(UserMapper.toDto(userRepository.findById(userId).orElse(new User())), HttpStatus.OK);
    }

    public ResponseEntity getAllUsers() {
        final List<UserDTO> users = userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .peek(dto -> dto.setPassword(AppConstants.PASSWORD_ASTERISKS))
                .toList();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public ResponseEntity<UserDTO> saveUser(UserDTO dto) {
        final User entity = new User();
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        BeanUtils.copyProperties(dto, entity);
        final User savedUser = userRepository.save(entity);
        dto.setPassword(AppConstants.PASSWORD_ASTERISKS);
        dto.setId(savedUser.getId());
        return ResponseEntity.ok(dto);
    }

    public boolean findByUsername(final String username) {
        return Optional.ofNullable(userRepository.findByUsername(username)).isPresent();
    }

    public boolean userExistsByUsername(final String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean userExistsById(final Integer id) {
        return userRepository.existsById(id);
    }

}
