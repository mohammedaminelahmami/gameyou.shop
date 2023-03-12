package com.youcode.gameyou.Service;

import com.youcode.gameyou.Repository.UserParentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserParentRepository userParentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userParentRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}