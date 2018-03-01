package fr.magicsystem.hashcode2018.classes.inmodel;

import lombok.Data;

import static fr.magicsystem.hashcode2018.classes.inmodel.Utils.lenRide;

@Data
public class Ride implements Comparable<Ride> {

    private final int id;

    private final int startX;
    private final int startY;

    private final int finishX;
    private final int finishY;

    private final int earliestStart;
    private final int latestFinish;

    private final int lenRide;
    private final int latestStart;

    public Ride(int id, int startX, int startY, int finishX, int finishY, int earliestStart, int finish) {
        this.id = id;
        this.startX = startX;
        this.startY = startY;
        this.finishX = finishX;
        this.finishY = finishY;
        this.earliestStart = earliestStart;
        this.latestFinish = finish;
        this.lenRide = lenRide(startX, startY, finishX, finishY);
        this.latestStart = latestFinish - lenRide;
    }

    public boolean isStillDoable(final int step) {
        return step + lenRide <= latestFinish;
    }

    @Override
    public int compareTo(Ride o) {
        final int deltaStart = this.latestStart - o.latestStart;

        // we need to go first
        if (deltaStart < 0) {
            return 1;
        } else if (deltaStart == 0) {
            return Integer.compare(o.lenRide, this.lenRide);
        } else {
            return -1;
        }
    }
}
