package fr.magicsystem.hashcode2018.classes.inmodel;

public class Utils {

    public static int distance(
            final int start1,
            final int start2,
            final int end1,
            final int end2
    ) {
        return Math.abs(start1 - end1) + Math.abs(start2 - end2);
    }

}
