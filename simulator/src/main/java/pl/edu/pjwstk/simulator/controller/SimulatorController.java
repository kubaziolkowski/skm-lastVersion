package pl.edu.pjwstk.simulator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.simulator.service.SimulatorService;


@RestController
@RequestMapping("/move")
public class SimulatorController {
    @Autowired
    private final SimulatorService service;

    public SimulatorController(SimulatorService service) {
        this.service = service;
    }

    @PostMapping
    public void move() {
        service.move();
    }
}
