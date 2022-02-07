package org.bexterlab.stream;

import java.util.Objects;

public class Animal {

    public Species species;
    public String name;

    public Species getSpecies() {
        return species;
    }

    public String getName() {
        return name;
    }

    public Animal setName(String name) {
        this.name = name;
        return this;
    }

    public Animal setSpecies(Species species) {
        this.species = species;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Animal animal = (Animal) o;
        return species == animal.species && Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(species, name);
    }
}
