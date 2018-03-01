package fr.magicsystem.hashcode2018.io;

import fr.magicsystem.hashcode2018.classes.InputData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InputReader {

    public InputData readInput(String inputFileLocation) {
        final List<String> inputLines = FileUtils.readFileLines(inputFileLocation);
        return parseInput(inputLines);
    }

    private static InputData parseInput(final List<String> inputLines) {
        return null;
    }

}
