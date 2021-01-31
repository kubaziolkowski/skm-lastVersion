package pl.edu.pjwstk.simulator.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.edu.pjwstk.simulator.service.DbEntity;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class User implements DbEntity {
    public String login;
    public String password;
    public Collection<GrantedAuthority> grantedAuthorities;

    public User(){

    }

    public Collection<?extends GrantedAuthority> getAuthorities(){
        return grantedAuthorities;
    }

    public User(UserEntity user){

    this.login = user.getLogin();
    this.password = user.getPassword();
    this.grantedAuthorities = Arrays.stream(user.getAuthorities().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    }

    @Override
    public Long getId() {
        return null;
    }
}
