package fr.magicsystem.hashcode2018.processing;

import fr.magicsystem.hashcode2018.classes.InputData;
import fr.magicsystem.hashcode2018.classes.OutputData;
import fr.magicsystem.hashcode2018.classes.inmodel.Ride;
import fr.magicsystem.hashcode2018.classes.inmodel.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class Algorithm {

    private final Queue<Ride> ridesLeft = new PriorityQueue<>();
    private final List<Vehicle> vehicles = new ArrayList<>();

    public OutputData applyAlgo(InputData input) {
        ridesLeft.addAll(input.getRides());
        vehicles.addAll(input.getVehicles());

        final AtomicInteger step = new AtomicInteger(0);
        while (ridesLeft.size() != 0 && step.get() <= input.getNbStepsSim()) {
            eliminateTooLate(ridesLeft, step);

            vehicles.stream()
                    .filter(vehicle -> vehicle.isAvailable(step.get()))
                    .forEach(vehicle -> {
                        if (ridesLeft.peek() != null) {
                            vehicle.submitRide(ridesLeft.poll(), step.get());
                        }
                    });
            step.incrementAndGet();
        }

        log.info("Assigned {} rides.", vehicles.stream().map(Vehicle::getRides).mapToInt(List::size).sum());
        return new OutputData(vehicles);
    }

    private void eliminateTooLate(Queue<Ride> ridesLeft, AtomicInteger step) {
        while (ridesLeft.peek() != null && !ridesLeft.peek().isStillDoable(step.get())) {
            ridesLeft.poll();
        }
    }

}
