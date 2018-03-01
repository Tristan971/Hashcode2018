package fr.magicsystem.hashcode2018.processing;

import fr.magicsystem.hashcode2018.classes.InputData;
import fr.magicsystem.hashcode2018.classes.OutputData;
import fr.magicsystem.hashcode2018.classes.inmodel.Ride;
import fr.magicsystem.hashcode2018.classes.inmodel.Vehicle;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public final class Algorithm {

    private final Queue<Ride> ridesLeft = new PriorityQueue<>();
    private final List<Vehicle> vehicles = new ArrayList<>();

    public OutputData applyAlgo(InputData input) {
        ridesLeft.addAll(input.getRides());
        vehicles.addAll(input.getVehicles());

        final AtomicInteger step = new AtomicInteger(1);
        while (ridesLeft.size() != 0 && step.get() <= input.getNbStepsSim()) {
            vehicles.stream()
                    .filter(vehicle -> vehicle.isAvailable(step.get()))
                    .forEach(vehicle -> vehicle.submitRide(ridesLeft.poll(), step.get()));
            step.incrementAndGet();
        }

        return new OutputData(vehicles);
    }

}
