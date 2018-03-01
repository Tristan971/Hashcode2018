package fr.magicsystem.hashcode2018.classes;

import fr.magicsystem.hashcode2018.classes.inmodel.Ride;
import fr.magicsystem.hashcode2018.classes.inmodel.Vehicle;
import lombok.Data;

import java.util.List;

@Data
public class InputData {

    private final int rows;
    private final int cols;
    private final int bonusEarly;
    private final int nbStepsSim;

    private final List<Ride> rides;
    private final List<Vehicle> vehicles;

}
