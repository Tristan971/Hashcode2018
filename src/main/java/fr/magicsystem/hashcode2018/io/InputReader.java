package fr.magicsystem.hashcode2018.io;

import fr.magicsystem.hashcode2018.classes.InputData;
import fr.magicsystem.hashcode2018.classes.inmodel.Ride;
import fr.magicsystem.hashcode2018.classes.inmodel.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

@Service
public class InputReader {

    public InputData readInput(String inputFileLocation) {
        final List<String> inputLines = FileUtils.readFileLines(inputFileLocation);
        return parseInput(inputLines);
    }

    private static InputData parseInput(final List<String> inputLines) {
        final String[] params = inputLines.remove(0).split(" ");

        int rows = parseInt(params[0]);
        int cols = parseInt(params[1]);

        int nbVehicles = parseInt(params[2]);

        int bonusEarly = parseInt(params[4]);
        int nbStepsSim = parseInt(params[5]);

        final List<Ride> rides = IntStream.range(0, inputLines.size())
                .mapToObj(rideNumber -> {
                    final String[] rideParams = inputLines.get(rideNumber).split(" ");
                    return new Ride(
                            rideNumber,
                            parseInt(rideParams[0]),
                            parseInt(rideParams[1]),
                            parseInt(rideParams[2]),
                            parseInt(rideParams[3]),
                            parseInt(rideParams[4]),
                            parseInt(rideParams[5])
                    );
                }).collect(Collectors.toList());

        final List<Vehicle> vehicles = IntStream.range(0, nbVehicles)
                .mapToObj(Vehicle::new)
                .collect(Collectors.toList());

        return new InputData(rows, cols, bonusEarly, nbStepsSim, rides, vehicles);
    }

}
