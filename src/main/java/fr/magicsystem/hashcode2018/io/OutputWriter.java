package fr.magicsystem.hashcode2018.io;

import fr.magicsystem.hashcode2018.classes.OutputData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class OutputWriter {

    public Path writeOutput(final String outputFile, final OutputData outputData) {
        return FileUtils.writeFile(outputFile, formatOutput(outputData));
    }

    private static String formatOutput(final OutputData outputData) {
        return "";
    }
}
