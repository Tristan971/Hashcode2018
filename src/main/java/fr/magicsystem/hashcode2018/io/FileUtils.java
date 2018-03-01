package fr.magicsystem.hashcode2018.io;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@Slf4j
public final class FileUtils {

    public static List<String> readFileLines(final String file) {
        log.info("Reading file at : [{}]", file);
        try {
            final List<String> lines = Files.readAllLines(Paths.get(file));
            log.info("Read {} lines from file at : [{}]", lines.size(), file);
            return lines;
        } catch (IOException e) {
            log.error("Could not open file !", e);
            return Collections.emptyList();
        }
    }

    public static String readFile(final String file) {
        return readFileLines(file).stream().collect(Collectors.joining("\n"));
    }

    public static Path writeFile(final String file, final String content) {
        log.info("Writing to file at : [{}]", file);
        final Path outputPath = Paths.get(file);

        try {
            Files.write(outputPath, content.getBytes(), CREATE, TRUNCATE_EXISTING);
        } catch (IOException e) {
            log.error("Could not write to file !", e);
            return null;
        }

        log.info("Wrote to file at : [{}]", outputPath.toAbsolutePath().toString());
        return outputPath;
    }

}
