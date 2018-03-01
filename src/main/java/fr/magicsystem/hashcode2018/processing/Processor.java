package fr.magicsystem.hashcode2018.processing;

import fr.magicsystem.hashcode2018.classes.InputData;
import fr.magicsystem.hashcode2018.classes.OutputData;
import fr.magicsystem.hashcode2018.io.InputReader;
import fr.magicsystem.hashcode2018.io.OutputWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Processor {

    private final InputReader reader;
    private final OutputWriter writer;
    private final Algorithm algorithm;

    public final void processInput(final String inputFile, final String outputFile) {
        final long begin = System.currentTimeMillis();

        final InputData input = reader.readInput(inputFile);

        final OutputData output = algorithm.applyAlgo(input);

        writer.writeOutput(outputFile, output);

        final long end = System.currentTimeMillis();

        log.info("Finished [in : {}] -> [out : {}] ({} ms)",
                inputFile,
                outputFile,
                end - begin
        );
    }

}
