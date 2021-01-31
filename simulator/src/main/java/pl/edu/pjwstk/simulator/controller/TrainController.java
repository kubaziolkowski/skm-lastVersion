package pl.edu.pjwstk.simulator.controller;

import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.simulator.models.Compartment;
import pl.edu.pjwstk.simulator.models.Train;
import pl.edu.pjwstk.simulator.service.TrainService;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@RestController
@RequestMapping("/trains")
public class TrainController extends CrudController<Train> {

    public TrainController(TrainService service) {
        super(service);
    }

    @Override
    public Function<Train, Map<String, Object>> transformToDTO() {
        return train -> {
            var payload = new LinkedHashMap<String, Object>();
            payload.put("id", train.getId());
            payload.put("station", train.getStation().getName());
            payload.put("direction", train.getDirection());
            payload.put("cooldown", train.getCooldown());
            payload.put("compartments", train.getCompartmentList().stream().map(Compartment::getId));

            return payload;
        };
    }



}
