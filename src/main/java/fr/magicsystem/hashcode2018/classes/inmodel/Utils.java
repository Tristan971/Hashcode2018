package fr.magicsystem.hashcode2018.classes.inmodel;

public class Utils {

    public static int lenRide(
            final int startX,
            final int startY,
            final int endX,
            final int endY
    ) {
        return Math.abs(startX - endX) + Math.abs(startY - endY);
    }

}
