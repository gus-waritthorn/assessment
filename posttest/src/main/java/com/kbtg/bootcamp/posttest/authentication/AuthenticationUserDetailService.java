package com.kbtg.bootcamp.posttest.authentication;

import com.kbtg.bootcamp.posttest.security.CustomUserDetail;
import com.kbtg.bootcamp.posttest.user.User;
import com.kbtg.bootcamp.posttest.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class AuthenticationUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    public AuthenticationUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("No user was found.");
        }
        User user = optionalUser.get();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // right now every body will have the same password 'password' because we didn't store password on our database
        CustomUserDetail customUserDetail = new CustomUserDetail(user.getUsername(), encoder.encode("password"));
        if(user.getRole().equals("ADMIN")) {
            customUserDetail.setRoles(List.of("ADMIN"));
        }
        return customUserDetail;
    }
}
