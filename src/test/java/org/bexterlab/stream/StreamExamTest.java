package org.bexterlab.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.bexterlab.stream.Species.*;

class StreamExamTest {

    public static final List<Animal> ANIMAL_LIST =
            List.of(new Animal().setName("Cirmi").setSpecies(CAT),
                    new Animal().setName("Noel").setSpecies(CAT),
                    new Animal().setName("Obama").setSpecies(CAT),
                    new Animal().setName("Nick").setSpecies(RAT),
                    new Animal().setName("Jhon").setSpecies(RAT),
                    new Animal().setName("Bark").setSpecies(DOG),
                    new Animal().setName("Buksi").setSpecies(DOG),
                    new Animal().setName("Tapancs").setSpecies(DOG),
                    new Animal().setName("Szimat").setSpecies(DOG));
    private StreamExam streamExam;
    private StreamSolution streamSolution;

    @BeforeEach
    void setUp() {
        streamExam = new StreamExam();
        streamSolution = new StreamSolution(ANIMAL_LIST);
    }

    @Test
    void streamTest1() {
        Assertions.assertEquals(List.of("Bark", "Buksi", "Tapancs", "Szimat"), streamSolution.findAllDogs());
    }

    @Test
    void streamTest2() {
        Assertions.assertEquals("Nick", streamSolution.findFirstRat());
    }

    @Test
    void streamTest3() {
        Assertions.assertEquals(new Animal().setName("Jhon").setSpecies(RAT), streamSolution.findRat("Jhon"));
    }

    @Test
    void streamTest4() {
        Assertions.assertThrows(RatNotFoundException.class, () -> streamSolution.findRat("Not Jhon"));
    }

    @Test
    void streamTest5() {
        Assertions.assertEquals(Map.of(DOG, List.of("Bark", "Buksi", "Tapancs", "Szimat")
                , CAT, List.of("Cirmi", "Noel", "Obama"), RAT, List.of("Nick",
                        "Jhon")), streamSolution.collectAnimeBySpecies());
    }

    @Test
    void streamTest6() {
        Assertions.assertTrue(streamSolution.isCat("Obama"));
        Assertions.assertFalse(streamSolution.isCat("Buksi"));
        Assertions.assertFalse(streamSolution.isCat("Not cat"));
    }

    @Test
    void streamTest7() {
        Assertions.assertTrue(streamSolution.isAllCat("Obama", "Cirmi"));
        Assertions.assertFalse(streamSolution.isAllCat("Buksi", "Nick"));
        Assertions.assertFalse(streamSolution.isAllCat("Not a cat", "Cat a not"));
    }

    @Test
    void streamTest8() {
        Assertions.assertTimeout(Duration.of(2, ChronoUnit.SECONDS),
                () -> streamSolution.fastExecution(Stream.of(threadSleep(), threadSleep(),
                        threadSleep(), threadSleep(), threadSleep())));
    }

    private Runnable threadSleep() {
        return () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new AssertionError("Exception occured", e);
            }
        };
    }


}