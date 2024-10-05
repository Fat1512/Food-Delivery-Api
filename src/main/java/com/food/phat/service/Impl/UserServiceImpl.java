package com.food.phat.service.Impl;

import com.food.phat.entity.User;
import com.food.phat.repository.ChatRoomUserRepository;
import com.food.phat.repository.UserRepository;
import com.food.phat.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final ChatRoomUserRepository chatRoomUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new Error("user not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Transactional
    public User getUserById(int userId) {
        return userRepository.findById(userId).get();
    }

    @Transactional
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
}
