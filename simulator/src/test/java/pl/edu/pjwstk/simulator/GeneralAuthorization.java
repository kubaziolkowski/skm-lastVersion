package pl.edu.pjwstk.simulator;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
abstract class GeneralAuthorization {

    static HashMap<String, Set<String>> accessibleURIs;

    @Autowired
    MockMvc mock;

    @Test
    void canAuthorityLoginTest() throws Exception {

        String uniformResourceIdentifier = "/login" , request = "POST", content = """
                    {"login": "login",
                    "password": "password"}""";

        if (accessibleURIs.containsKey(uniformResourceIdentifier) && accessibleURIs.get(uniformResourceIdentifier).contains(request)) {
            mock.perform(post(uniformResourceIdentifier).contentType(MediaType.APPLICATION_JSON).content(content))
                    .andExpect(status().isOk());
        } else {
            mock.perform(post(uniformResourceIdentifier).contentType(MediaType.APPLICATION_JSON).content(content))
                    .andExpect(status().isForbidden());
        }
    }

    @Test
    void roleHaveAccessToPostMove() throws Exception {
        String uniformResourceIdentifier = "/move",request = "POST";;


        if (accessibleURIs.containsKey(uniformResourceIdentifier) && accessibleURIs.get(uniformResourceIdentifier).contains(request)) {
            mock.perform(post(uniformResourceIdentifier))
                    .andExpect(status().isOk());
        } else {
            mock.perform(post(uniformResourceIdentifier))
                    .andExpect(status().isForbidden());
        }
    }

    @Test
    @Order(1)
    void roleHaveAccessToGetTrainsList() throws Exception {
        String uniformResourceIdentifier = "/trains",request = "GET";;


        if (accessibleURIs.containsKey(uniformResourceIdentifier) && accessibleURIs.get(uniformResourceIdentifier).contains(request)) {
            mock.perform(get(uniformResourceIdentifier))
                    .andExpect(status().isOk());
        } else {
            mock.perform(get(uniformResourceIdentifier))
                    .andExpect(status().isForbidden());
        }
    }



    @Test
    @Order(5)
    void roleHaveAccessToDeleteTrain() throws Exception {
        String uniformResourceIdentifier = "/trains",request = "DELETE",id = "1";;

        if (accessibleURIs.containsKey(uniformResourceIdentifier) && accessibleURIs.get(uniformResourceIdentifier).contains(request)) {
            mock.perform(delete(uniformResourceIdentifier + id))
                    .andExpect(status().isAccepted());
        } else {
            mock.perform(delete(uniformResourceIdentifier + id))
                    .andExpect(status().isForbidden());
        }
    }


    @Test
    @Order(1)
    void roleHaveAccessToGetCompartmentsList() throws Exception {
        String uniformResourceIdentifier = "/compartments",request = "GET";;

        if (accessibleURIs.containsKey(uniformResourceIdentifier) && accessibleURIs.get(uniformResourceIdentifier).contains(request)) {
            mock.perform(get(uniformResourceIdentifier))
                    .andExpect(status().isOk());
        } else {
            mock.perform(get(uniformResourceIdentifier))
                    .andExpect(status().isForbidden());
        }
    }


    @Test
    @Order(4)
    void roleHaveAccessToGetUserInfo() throws Exception {
        String uniformResourceIdentifier = "/users",request = "GET",id = "69";

        if (accessibleURIs.containsKey(uniformResourceIdentifier) && accessibleURIs.get(uniformResourceIdentifier).contains(request)) {
            mock.perform(get(uniformResourceIdentifier + id))
                    .andExpect(status().isOk());
        } else {
            mock.perform(get(uniformResourceIdentifier + id))
                    .andExpect(status().isForbidden());
        }
    }

    @Test
    @Order(5)
    void roleHaveAccessToDeleteUser() throws Exception {
        String uniformResourceIdentifier = "/users",request = "DELETE",id = "420";

        if (accessibleURIs.containsKey(uniformResourceIdentifier) && accessibleURIs.get(uniformResourceIdentifier).contains(request)) {
            mock.perform(delete(uniformResourceIdentifier + id))
                    .andExpect(status().isAccepted());
        } else {
            mock.perform(delete(uniformResourceIdentifier + id))
                    .andExpect(status().isForbidden());
        }
    }
}