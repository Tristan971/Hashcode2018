package fr.magicsystem.hashcode2018.classes.inmodel;

import lombok.Data;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

@Data
public class Vehicle {

    private final int vehicleId;

    private final AtomicInteger posX = new AtomicInteger(0);
    private final AtomicInteger posY = new AtomicInteger(0);

    private final AtomicReference<Function<Integer, Boolean>> isBusy = new AtomicReference<>(i -> false);

    private final List<Ride> rides;

    public void submitRide(final Ride ride, final int curTime) {
        if (isBusy.get().apply(curTime)) {
            throw new IllegalStateException("WE ARE BUSY");
        }
        this.rides.add(ride);
        isBusy.set(time -> time < curTime + ride.getLenRide());
    }

    public boolean isAvailable(final int curTime) {
        return !isBusy.get().apply(curTime);
    }

    public void moveStep(final int step) {
        if (!isBusy.get().apply(step)) {
            return;
        }

        final Ride lastRide = rides.get(rides.size() - 1);

        final int endX = lastRide.getFinishX();
        final int endY = lastRide.getFinishY();

        if (this.posX.get() < endX) {
            this.posX.incrementAndGet();
        } else if (this.posX.get() > endX) {
            this.posX.decrementAndGet();
        } else if (this.posY.get() < endY) {
            this.posY.incrementAndGet();
        } else if (this.posY.get() > endY) {
            this.posY.decrementAndGet();
        }
    }
}
