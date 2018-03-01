package fr.magicsystem.hashcode2018.classes.inmodel;

import lombok.Data;

import java.util.List;
import java.util.Optional;
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
    private Optional<Ride> lastRide = Optional.empty();

    public void submitRide(final Ride ride, final int curTime) {
        if (isBusy.get().apply(curTime)) {
            throw new IllegalStateException("WE ARE BUSY");
        }
        this.rides.add(ride);
        lastRide = Optional.of(ride);
        isBusy.set(time -> time < curTime + ride.getLenRide());
    }

    public boolean isAvailable(final int curTime) {
        return !isBusy.get().apply(curTime);
    }

    public int distanceTo(final int x, final int y) {
        return Utils.distance(this.posX.get(), x, this.posY.get(), y);
    }

    public void moveStep() {
        if (!lastRide.isPresent()) return;

        final Ride lastRide = this.lastRide.get();

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
