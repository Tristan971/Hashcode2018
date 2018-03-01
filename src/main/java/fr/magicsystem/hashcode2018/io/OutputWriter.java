package fr.magicsystem.hashcode2018.io;

import fr.magicsystem.hashcode2018.classes.OutputData;
import fr.magicsystem.hashcode2018.classes.inmodel.Ride;
import fr.magicsystem.hashcode2018.classes.inmodel.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OutputWriter {

    public Path writeOutput(final String outputFile, final OutputData outputData) {
        return FileUtils.writeFile(outputFile, formatOutput(outputData));
    }

    private static String formatOutput(final OutputData outputData) {
        return outputData.getVehicles().stream()
                .map(OutputWriter::formatVehicle)
                .collect(Collectors.joining("\n"));
    }

    private static String formatVehicle(final Vehicle vehicle) {
        final int validRides = vehicle.getRides().size();
        final String orderRides = vehicle.getRides().stream()
                .mapToInt(Ride::getId)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));

        return String.format("%d %s", validRides, orderRides);
    }

}
