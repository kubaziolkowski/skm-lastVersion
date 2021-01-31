package pl.edu.pjwstk.simulator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.simulator.security.User;
import pl.edu.pjwstk.simulator.security.UserService;
import pl.edu.pjwstk.simulator.service.CrudService;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@RestController
@RequestMapping("/users")

public class UserController extends CrudController<User> {
    @Autowired
    public UserController(CrudService userService){
        super(userService);
    }


    @Override
    public Function<User, Map<String, Object>> transformToDTO() {

        return user -> {
           var payload = new LinkedHashMap<String , Object >();
           payload.put("id",user.getId());
           payload.put("username",user.getAuthorities().contains(user.login));
           payload.put("password",user.getAuthorities().contains(user.password));
           payload.put("authorities", user.getAuthorities());
           return payload;
        };
    }
}
