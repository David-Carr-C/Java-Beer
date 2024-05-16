package com.example.config;

import com.example.dto.UserDTO;
import com.example.dto.mapper.UserDTOMapper;
import com.example.entity.UserEntity;
import com.example.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserConfig implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserEntity userEntity = (UserEntity) userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            Collection<? extends GrantedAuthority> authorities = userEntity.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                    .toList();

            return new User(userEntity.getUsername(), userEntity.getPassword(), true,
                    true, true, true, authorities);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
