package org.bexterlab.optional;

import org.bexterlab.stream.Animal;
import org.bexterlab.stream.Species;

import java.util.Optional;

public class OptionalSolution {

    public String finAnimalName(Animal animal) {
        return Optional.of(animal)
                .map(Animal::getName)
                .orElseThrow();
    }

    public Species findSpecies(Animal animal) {
        return Optional.ofNullable(animal)
                .map(Animal::getSpecies)
                .orElse(null);
    }

    public boolean hasName(Animal setSpecies) {
        return Optional.ofNullable(setSpecies)
                .map(Animal::getName)
                .isPresent();
    }

    public Species findSpecies2(Animal animal) {
        return Optional.ofNullable(animal)
                .map(Animal::getSpecies)
                .orElseThrow(AssertionError::new);
    }
}
