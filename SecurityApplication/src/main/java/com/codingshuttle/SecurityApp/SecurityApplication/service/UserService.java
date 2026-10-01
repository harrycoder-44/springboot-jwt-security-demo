package com.codingshuttle.SecurityApp.SecurityApplication.service;

import com.codingshuttle.SecurityApp.SecurityApplication.dto.LoginDTO;
import com.codingshuttle.SecurityApp.SecurityApplication.dto.SignUpDTO;
import com.codingshuttle.SecurityApp.SecurityApplication.dto.UserDTO;
import com.codingshuttle.SecurityApp.SecurityApplication.entity.User;
import com.codingshuttle.SecurityApp.SecurityApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("User not found: " + username));
    }


    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("id is not valid"));
    }


    public UserDTO signUp(SignUpDTO signUpDTO) {

        Optional<User> user = userRepository.findByEmail(signUpDTO.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("user with email already exists");
        }


        User toCreate = modelMapper.map(signUpDTO,User.class);
        toCreate.setPassword(passwordEncoder.encode(toCreate.getPassword()));
        User savedUser = userRepository.save(toCreate);
        return modelMapper.map(savedUser,UserDTO.class);



    }


}