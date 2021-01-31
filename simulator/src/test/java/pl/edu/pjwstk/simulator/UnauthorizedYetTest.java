package pl.edu.pjwstk.simulator;


import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;

import java.util.HashMap;
import java.util.Set;

@SpringBootTest
@WithAnonymousUser
public class UnauthorizedYetTest extends GeneralAuthorization {
    @BeforeAll
    static void givePermission(){
        HashMap<String, Set<String>> uniformResourceIdentifier = new HashMap<>();
        uniformResourceIdentifier.put("/login", Set.of("POST"));
    }
}
