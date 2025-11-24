package service;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return  org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail()).password(user.getPassword()).authorities("USER").build();
    }
}
