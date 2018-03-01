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

        log.info("We have {} cars and {} rides.", vehicles.size(), ridesLeft.size());

        final AtomicInteger step = new AtomicInteger(0);
        while (ridesLeft.size() != 0 && step.get() <= input.getNbStepsSim()) {

            fillCars(step);
            fillCars(step);
            fillCars(step);

            step.getAndIncrement();
        }

        log.info("Assigned {} rides.", vehicles.stream().map(Vehicle::getRides).mapToInt(List::size).sum());
        return new OutputData(vehicles);
    }

    private boolean allCarsFull(AtomicInteger step) {
        return vehicles.stream().noneMatch(vehicle -> vehicle.isAvailable(step.get()));
    }

    private void fillCars(AtomicInteger step) {
        vehicles.stream()
                .filter(vehicle -> vehicle.isAvailable(step.get()))
                .sorted((v1, v2) -> {
                    if (ridesLeft.peek() != null) {
                        return nearest(v1, v2, ridesLeft.peek());
                    }
                    return 1;
                })
                .forEach(vehicle -> {
                    tryAssignRide(step, vehicle);
                    vehicle.moveStep();
                });
    }

    private void tryAssignRide(AtomicInteger step, Vehicle vehicle) {
        final Ride curRide = ridesLeft.poll();
        if (curRide != null) {
            if (wouldntCompromise(curRide, vehicle, step.get())) {
                vehicle.submitRide(curRide, step.get());
            } else {
                ridesLeft.add(curRide);
            }
        }
    }

    private boolean wouldntCompromise(final Ride ride, final Vehicle vehicle, final int step) {
        final int timeleft = ride.getLatestFinish() - step;
        return vehicle.distanceTo(ride.getStartX(), ride.getStartY()) <= timeleft;
    }

    private static int nearest(final Vehicle v1, final Vehicle v2, final Ride ride) {
        final int dist1 = v1.distanceTo(ride.getStartX(), ride.getStartY());
        final int dist2 = v2.distanceTo(ride.getStartX(), ride.getStartY());
        return Integer.compare(dist1, dist2);
    }

}
