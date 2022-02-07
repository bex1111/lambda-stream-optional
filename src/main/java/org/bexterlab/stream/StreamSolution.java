package org.bexterlab.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
                .map(x -> x.name).findFirst()
                .orElse(null);
    }

    public Animal findRat(String name) {
        return animalList.stream()
                .filter(x -> x.name.equals(name))
                .findFirst()
                .orElseThrow(RatNotFoundException::new);
    }

    public Map<Species, List<String>> collectAnimeBySpecies() {
        return animalList.stream().collect(Collectors.groupingBy(Animal::getSpecies, Collectors.mapping((x) -> x.name, Collectors.toList())));
    }

    public boolean isCat(String catName) {
        return animalList.stream().anyMatch(x -> x.name.equals(catName) && x.species == CAT);
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
                .forEach(Runnable::run);
    }
}
