package com.youcode.gameyou.Service;

import com.youcode.gameyou.Entity.UserParent;
import com.youcode.gameyou.Repository.UserParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserParentRepository userParentRepository;
    public User findUser(String email) throws UsernameNotFoundException
    {
        UserParent userParent = userParentRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(userParent.getEmail(), userParent.getHashedpassword(), userParent.getAuthorities());
    }
}