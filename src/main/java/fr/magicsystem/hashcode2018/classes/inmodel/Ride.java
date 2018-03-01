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

    public Ride(int id, int startX, int startY, int finishX, int finishY, int earliestStart, int finish) {
        this.id = id;
        this.startX = startX;
        this.startY = startY;
        this.finishX = finishX;
        this.finishY = finishY;
        this.earliestStart = earliestStart;
        this.latestFinish = finish;
        this.lenRide = lenRide(startX, startY, finishX, finishY);
    }


    @Override
    public int compareTo(Ride o) {
        return this.lenRide < o.lenRide ? 1 : -1;
    }
}
