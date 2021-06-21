package hovanvydut.shoplaptop.security;

import hovanvydut.shoplaptop.exception.UserNotFoundException;
import hovanvydut.shoplaptop.model.User;
import hovanvydut.shoplaptop.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author hovanvydut
 * Created on 6/8/21
 */

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("User email = " + email + " not found");
        }

        return new MyUserDetails(user);
    }
}
