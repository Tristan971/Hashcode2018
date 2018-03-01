package fr.magicsystem.hashcode2018.classes.inmodel;

import lombok.Data;

import static fr.magicsystem.hashcode2018.classes.inmodel.Utils.distance;

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
        this.lenRide = distance(startX, startY, finishX, finishY);
        this.latestStart = latestFinish - lenRide;
    }

    public boolean isStillDoable(final int step) {
        return step + lenRide <= latestFinish;
    }

    @Override
    public int compareTo(Ride o) {
        if (this.latestStart == o.latestStart) {
            return Integer.compare(o.lenRide, this.lenRide);
        } else {
            return this.latestStart < o.latestStart ? -1 : 1;
        }
    }

    public int score(final int step, final int end, int bonus) {
        int delay = latestStart - step;
        int scorePotential = lenRide + bonus;
        return delay == 0 ? 0 : scorePotential/delay;
    }

}
