package org.bexterlab.optional;

import org.bexterlab.stream.Animal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.bexterlab.stream.Species.CAT;

class OptionalExamTest {

    OptionalExam optionalExam;

    @BeforeEach
    void setUp() {
        optionalExam = new OptionalExam();
    }

    @Test
    void optionalTest1() {
        Assertions.assertEquals("Jhon", optionalExam.finAnimalName(new Animal().setName("Jhon").setSpecies(CAT)));
        Assertions.assertThrows(NoSuchElementException.class, () -> optionalExam.finAnimalName(new Animal().setName(null).setSpecies(CAT)));
        Assertions.assertThrows(NullPointerException.class, () -> optionalExam.finAnimalName(null));
    }

    @Test
    void optionalTest2() {
        Assertions.assertEquals(CAT, optionalExam.findSpecies(new Animal().setName("Jhon").setSpecies(CAT)));
        Assertions.assertNull(optionalExam.findSpecies(new Animal().setName("Jhon").setSpecies(null)));
        Assertions.assertNull(optionalExam.findSpecies(null));
    }

    @Test
    void optionalTest3() {
        Assertions.assertTrue(optionalExam.hasName(new Animal().setName("Jhon").setSpecies(CAT)));
        Assertions.assertFalse(optionalExam.hasName(new Animal().setName(null).setSpecies(CAT)));
        Assertions.assertFalse(optionalExam.hasName(null));
    }

    @Test
    void optionalTest4() {
        Assertions.assertEquals(CAT, optionalExam.findSpecies2(new Animal().setName("Jhon").setSpecies(CAT)));
        Assertions.assertThrows(AssertionError.class, () -> optionalExam.findSpecies2(new Animal().setName("Jhon").setSpecies(null)));

    }
}