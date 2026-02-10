package com.authservice.core.controller;

import com.authservice.core.config.JwtUtil;
import com.authservice.core.controller.request.AuthRequest;
import com.authservice.core.controller.response.AuthResponse;
import com.authservice.core.dto.UserDTO;
import com.authservice.core.exception.InvalidPayloadException;
import com.authservice.core.exception.UserAlreadyExistException;
import com.authservice.core.service.UserService;
import com.authservice.core.util.ApiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(ApiConstants.AUTH)
public class AuthController {

    @Autowired
    public UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class.getName());

    @PostMapping(ApiConstants.REGISTER)
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDto) {
        if (Objects.isNull(userDto)) {
            throw new InvalidPayloadException("Payload cannot be Null");
        }

        if(userService.userExistsByUsername(userDto.getUsername())){
            throw new UserAlreadyExistException("Username is already taken");
        }

        return userService.saveUser(userDto);
    }

    @PostMapping(ApiConstants.LOGIN)
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest authenticationRequest) throws BadCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (final BadCredentialsException badCredentialsException) {
            LOG.error("Incorrect username or password");
            throw badCredentialsException;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping(ApiConstants.SECURE)
    public ResponseEntity<String> secureResource(){
        return ResponseEntity.ok("Yes, JWT Works... This is secured. ");
    }

}
