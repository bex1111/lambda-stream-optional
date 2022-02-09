package org.bexterlab.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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

    @BeforeEach
    void setUp() {
        streamExam = new StreamExam(ANIMAL_LIST);
    }

    @Test
    void streamTest1() {
        Assertions.assertEquals(List.of("Bark", "Buksi", "Tapancs", "Szimat"), streamExam.findAllDogs());
    }

    @Test
    void streamTest2() {
        Assertions.assertEquals("Nick", streamExam.findFirstRat());
    }

    @Test
    void streamTest3() {
        Assertions.assertEquals(new Animal().setName("Jhon").setSpecies(RAT), streamExam.findRat("Jhon"));
    }

    @Test
    void streamTest4() {
        Assertions.assertThrows(RatNotFoundException.class, () -> streamExam.findRat("Not Jhon"));
    }

    @Test
    void streamTest5() {
        Assertions.assertEquals(Map.of(DOG, List.of("Bark", "Buksi", "Tapancs", "Szimat")
                , CAT, List.of("Cirmi", "Noel", "Obama"), RAT, List.of("Nick",
                        "Jhon")), streamExam.collectAnimalBySpecies());
    }

    @Test
    void streamTest6() {
        Assertions.assertTrue(streamExam.isCat("Obama"));
        Assertions.assertFalse(streamExam.isCat("Buksi"));
        Assertions.assertFalse(streamExam.isCat("Not cat"));
    }

    @Test
    void streamTest7() {
        Assertions.assertTrue(streamExam.isAllCat("Obama", "Cirmi"));
        Assertions.assertFalse(streamExam.isAllCat("Buksi", "Nick"));
        Assertions.assertFalse(streamExam.isAllCat("Not a cat", "Cat a not"));
    }

    @Test
    void streamTest8() {
        Assertions.assertTimeout(Duration.of(3, ChronoUnit.SECONDS),
                () -> streamExam.fastExecution(Stream.of(threadSleep(), threadSleep(),
                        threadSleep(), threadSleep(), threadSleep())));
    }

    @Test
    void streamTest9() {
        Assertions.assertTrue(streamExam.isAllHaveASpecies());
        streamExam = new StreamExam(Stream.concat(ANIMAL_LIST.stream(),
                        Stream.of(new Animal().setName("Null species").setSpecies(null)))
                .collect(Collectors.toList()));
        Assertions.assertFalse(streamExam.isAllHaveASpecies());
    }

    @Test
    void streamTest10() {
        Assertions.assertTrue(streamExam.hasAnySpecies());
        streamExam = new StreamExam(Stream.concat(ANIMAL_LIST.stream(),
                        Stream.of(new Animal().setName("Null species").setSpecies(null)))
                .collect(Collectors.toList()));
        Assertions.assertTrue(streamExam.hasAnySpecies());
        streamExam = new StreamExam(
                List.of(new Animal().setName("Null species").setSpecies(null),
                        new Animal().setName("Null species1").setSpecies(null),
                        new Animal().setName("Null species2").setSpecies(null)));
        Assertions.assertFalse(streamExam.hasAnySpecies());
    }

    @Test
    void streamTest11() {
        Assertions.assertTrue(streamExam.isAllSpeciesNotNull());
        streamExam = new StreamExam(Stream.concat(ANIMAL_LIST.stream(),
                        Stream.of(new Animal().setName("Null species").setSpecies(null)))
                .collect(Collectors.toList()));
        Assertions.assertFalse(streamExam.isAllSpeciesNotNull());
    }

    @Test
    void streamTest12() {
        Assertions.assertEquals(List.of("Bark", "Buksi", "Cirmi", "Jhon", "Nick", "Noel", "Obama", "Szimat", "Tapancs"), streamExam.sortNameAsc());
    }

    @Test
    void streamTest13() {
        Assertions.assertEquals(44, streamExam.sumCharacterLength());
    }

    @Test
    void streamTest14() {
        Assertions.assertEquals("BarkBuksiCirmiJhonNickNoelObamaSzimatTapancs", streamExam.sortAndConcatNames());
    }

    @Test
    void streamTest15() {
        Assertions.assertEquals(2L, streamExam.countSpecies(RAT));
        Assertions.assertEquals(3L, streamExam.countSpecies(CAT));
        Assertions.assertEquals(4L, streamExam.countSpecies(DOG));
    }

    @Test
    void streamTest16() {
        Assertions.assertEquals(List.of(CAT, RAT, DOG), streamExam.collectAllSpecies());
    }

    @Test
    void streamTest17() {
        Assertions.assertEquals(List.of("Tapancs", "Szimat", "Obama", "Noel", "Nick", "Jhon", "Cirmi", "Buksi", "Bark"), streamExam.sortNameDesc());
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