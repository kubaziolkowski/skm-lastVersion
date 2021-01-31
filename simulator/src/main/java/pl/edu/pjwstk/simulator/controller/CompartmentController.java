package pl.edu.pjwstk.simulator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.simulator.models.Compartment;
import pl.edu.pjwstk.simulator.models.Passenger;
import pl.edu.pjwstk.simulator.service.CompartmentService;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@RestController
@RequestMapping("/compartments")
public class CompartmentController extends CrudController<Compartment> {

    public CompartmentController(CompartmentService service) {
        super(service);
    }

    @Override
    public Function<Compartment, Map<String, Object>> transformToDTO() {
        return compartment -> {
            var payload = new LinkedHashMap<String, Object>();
            payload.put("id", compartment.getId());
            payload.put("size", compartment.getSize());
            payload.put("train_id", compartment.getTrain().getId());
            payload.put("passengers", compartment.getPassengerList().stream().map(Passenger::toString));

            return payload;
        };
    }

}
