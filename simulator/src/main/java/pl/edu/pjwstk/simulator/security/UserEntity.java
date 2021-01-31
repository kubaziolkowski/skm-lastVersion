package pl.edu.pjwstk.simulator.security;

import javax.persistence.*;

@Entity
@Table(name = "usersTable")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long userId;
    public String login,password,authorities;

    public Long getUserId(){
        return userId;
    }
    public String getLogin() {
        return login;
    }

    public String getPassword(){
        return password;
    }
    public String getAuthorities(){
        return authorities;
    }
}
