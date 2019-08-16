package feedbacksystem.com.demo.config;

import feedbacksystem.com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {

        feedbacksystem.com.demo.model.users.User user = userRepository.findByLogin(username);

        if(user.getLogin().equals(username)){
//            SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new CustomUser(user.getLogin(), user.getPassword(), new ArrayList<>(), user.getId(), user.getAccessType().getType());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}
