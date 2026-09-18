package oom.test;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OomDemoTest {

    @Test
    void dumpHeapOnOom() throws Exception {
        Path dumpPath = Path.of("oomdump.hprof").toAbsolutePath();
        Files.deleteIfExists(dumpPath);

        ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-Xmx256m",
                "-XX:+HeapDumpOnOutOfMemoryError",
                "-XX:HeapDumpPath=" + dumpPath,
                "-cp", "target/classes",
                "oom.test.OOMDemo"
        );
        pb.redirectErrorStream(true);

        Process process = pb.start();
        String output = new String(process.getInputStream().readAllBytes());
        boolean finished = process.waitFor(60, TimeUnit.SECONDS);

        System.out.println(output);
        assertTrue(finished, "OOMDemo process did not finish in time");
        assertTrue(Files.exists(dumpPath), "Heap dump not created:\n" + output);
        System.out.println("Heap dump: " + dumpPath + " (" + Files.size(dumpPath) + " bytes)");
    }
}
