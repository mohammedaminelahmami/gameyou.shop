package com.youcode.gameyou.Service;

import com.youcode.gameyou.Entity.UserParent;
import com.youcode.gameyou.Repository.UserParentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserParentRepository userRepository;

    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        UserParent user = userRepository.findByEmail(email);
        if(user == null) throw new UsernameNotFoundException(email);
        return new User(user.getEmail(), user.getHashedPassword(), List.of(new SimpleGrantedAuthority(user.getRole().toString())));
    }

    public UserParent loadUserByEmail(String email) {
        UserParent user = userRepository.findByEmail(email);
        if(user == null) throw new UsernameNotFoundException(email);
        return user;
    }

    public UserParent findUserById(int id) {
        Long idUser = Long.valueOf(id);
        Optional<UserParent> user = userRepository.findById(idUser);
        return user.get();
    }
}