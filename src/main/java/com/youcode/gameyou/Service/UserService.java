package com.youcode.gameyou.Service;

import com.youcode.gameyou.Entity.UserParent;
import com.youcode.gameyou.Repository.UserParentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserParentRepository userRepository;

    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        UserParent user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return new User(user.getEmail(), user.getHashedPassword(), new ArrayList<>());
    }
    public UserParent loadUserByEmail(String email) {
        UserParent user = userRepository.findByEmail(email).get();
        return user;
    }

    public UserParent findUserById(int id) {
        Long idUser = Long.valueOf(id);
        Optional<UserParent> user = userRepository.findById(idUser);
        return user.get();
    }
}
