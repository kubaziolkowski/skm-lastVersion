package pl.edu.pjwstk.simulator.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService implements UserDetailsService {
    public PasswordEncoder encoder;
    public UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity userEntity = usersRepository.findUserByLogin(login).orElseThrow(() -> new UsernameNotFoundException( login + "Not found."));

     return new UserDetails() {
         @Override
         public Collection<? extends GrantedAuthority> getAuthorities() {
             return getAuthorities();
         }

         @Override
         public String getPassword() {
             return null;// "return password" się czerwieni, nie wiem dlaczego
         }

         @Override
         public String getUsername() {
             return login;
         }

         @Override
         public boolean isAccountNonExpired() {
             return true;
         }

         @Override
         public boolean isAccountNonLocked() {
             return true;
         }

         @Override
         public boolean isCredentialsNonExpired() {
             return true;
         }

         @Override
         public boolean isEnabled() {
             return true;
         }
     };
    }
    public PasswordEncoder getPasswordEncoder(){
        return encoder;
    }
}
