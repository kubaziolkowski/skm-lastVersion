package pl.edu.pjwstk.simulator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootTest
@WithMockUser(authorities = {"ROLE_ADMIN"})

public class AdminTest extends GeneralAuthorization {

    @BeforeAll
    static void setAccessibleIdentities(){
        Map<String, Set<String>> uniformResourceIdentifier = new HashMap();
        uniformResourceIdentifier.put("/login", Set.of("POST"));
        uniformResourceIdentifier.put("/compartments" , Set.of("GET", "POST", "PUT"));
        uniformResourceIdentifier.put("/move", Set.of("POST"));

    }

}
