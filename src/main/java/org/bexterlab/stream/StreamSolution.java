package org.bexterlab.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.bexterlab.stream.Species.CAT;

public class StreamSolution {

    private final List<Animal> animalList;

    public StreamSolution(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public List<String> findAllDogs() {
        return animalList.stream()
                .filter(x -> x.species == Species.DOG)
                .map(x -> x.name)
                .collect(Collectors.toList());
    }

    public String findFirstRat() {
        return animalList.stream()
                .filter(x -> x.species == Species.RAT)
                .map(x -> x.name)
                .findFirst()
                .orElse(null);
    }

    public Animal findRat(String name) {
        return animalList.stream()
                .filter(x -> x.name.equals(name))
                .findFirst()
                .orElseThrow(RatNotFoundException::new);
    }

    public Map<Species, List<String>> collectAnimeBySpecies() {
        return animalList.stream()
                .collect(Collectors.groupingBy(Animal::getSpecies,
                        Collectors.mapping((x) -> x.name, Collectors.toList())));
    }

    public boolean isCat(String catName) {
        return animalList.stream()
                .anyMatch(x -> x.name.equals(catName) && x.species == CAT);
    }

    public boolean isAllCat(String... catsName) {
        return animalList.stream()
                .filter(x -> x.species == CAT)
                .map(x -> x.name)
                .collect(Collectors.toList())
                .containsAll(Arrays.stream(catsName).collect(Collectors.toList()));

    }

    public void fastExecution(Stream<Runnable> runnableStream) {
        runnableStream
                .parallel()
                .peek(x -> System.out.println(Thread.currentThread().getName()))
                .forEach(Runnable::run);
    }

    public boolean isAllHaveASpecies() {
        return animalList.stream()
                .map(Animal::getSpecies)
                .allMatch(Objects::nonNull);
    }

    public boolean hasAnySpecies() {
        return animalList.stream()
                .map(Animal::getSpecies)
                .anyMatch(Objects::nonNull);
    }

    public boolean isAllSpeciesNotNull() {
        return animalList.stream()
                .map(Animal::getSpecies)
                .noneMatch(Objects::isNull);
    }

    public List<String> sortNameAsc() {
        return animalList.stream()
                .map(Animal::getName)
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> sortNameDesc() {
        return animalList.stream()
                .map(Animal::getName)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }

    public long sumCharacterLength() {
        return animalList.stream()
                .map(x -> Long.valueOf(x.name.length()))
                .reduce(0L, Long::sum);
    }

    public String concatNames() {
        return animalList.stream()
                .map(Animal::getName)
                .sorted()
                .collect(Collectors.joining());
    }

    public long countSpecies(Species species) {
        return animalList.stream()
                .map(Animal::getSpecies)
                .filter(x -> species == x)
                .count();
    }

    public List<Species> collectAllSpecies() {
        return animalList.stream()
                .map(Animal::getSpecies)
                .distinct()
                .collect(Collectors.toList());
    }
}
