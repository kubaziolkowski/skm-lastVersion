package pl.edu.pjwstk.simulator;


import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.HashMap;
import java.util.Set;

@SpringBootTest
@WithMockUser(authorities = {"ROLE_USER"})
public class CommonUserAuthorizationTest extends GeneralAuthorization {

    @BeforeAll
    public void getUnuseduniformResourceIdentifiers(){
        HashMap<String, Set<String>> uniformResourceIdentifier = new HashMap<>();

        uniformResourceIdentifier.put("/compartments" , Set.of("POST"));
        uniformResourceIdentifier.put("/trains", Set.of("GET"));
    }


}
