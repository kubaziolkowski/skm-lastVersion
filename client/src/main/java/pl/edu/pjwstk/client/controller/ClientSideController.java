package pl.edu.pjwstk.client.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ClientSideController {

    private final String trainsList = "http://simulator:9000/trains";
    private final String compartmentList = "http://simulator:9000/compartments";

    @GetMapping("/trains")
    public ResponseEntity getTrains() {
        RestTemplate template = new RestTemplate();
        return template.getForEntity(trainsList, String.class);
    }

    @GetMapping("/trains/{id}")
    public ResponseEntity getTrains(@PathVariable("id") int id) {
        RestTemplate template = new RestTemplate();
        return template.getForEntity(trainsList + '/' + id, String.class);
    }

    @GetMapping("/compartments/{compartment}")
    public ResponseEntity getCompartment(@PathVariable("compartment") int compartment) {
        RestTemplate template = new RestTemplate();
        return template.getForEntity(compartmentList + '/' + compartment, String.class);
    }
}
