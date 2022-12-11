package com.zipcodewilmington.streams.anthropoid;

import com.zipcodewilmington.streams.tools.ReflectionUtils;
import com.zipcodewilmington.streams.tools.logging.LoggerHandler;
import com.zipcodewilmington.streams.tools.logging.LoggerWarehouse;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 5/29/17.
 * The warehouse is responsible for storing, retrieving, and filtering personSequence
 *
 * @ATTENTION_TO_STUDENTS You are FORBIDDEN from using loops of any sort within the definition of this class.
 */
public final class PersonWarehouse implements Iterable<Person> {
    private final LoggerHandler loggerHandler = LoggerWarehouse.getLogger(PersonWarehouse.class);
    private final List<Person> people = new ArrayList<>();

    /**
     * @param person the Person object to add to the warehouse
     * @ATTENTION_TO_STUDENTS You are FORBIDDEN from modifying this method
     */
    public void addPerson(Person person) {
        loggerHandler.disbalePrinting();
        loggerHandler.info("Registering a new person object to the person warehouse...");
        loggerHandler.info(ReflectionUtils.getFieldMap(person).toString());
        people.add(person);
    }

    /**
     * @return list of names of Person objects
     */ // TODO
    public List<String> getNames() {
        return people.stream().map(Person::getName).collect(Collectors.toList());
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor){
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
    //as explained below, we need this predicate to filter. The predicate is basically the condition that the filter abides by.
    /**
     * @return list of uniquely named Person objects
     */ //TODO
    public Stream<Person> getUniquelyNamedPeople() {
        List <Person> distinctElements = people.stream().filter(distinctByKey(Person::getName)).collect(Collectors.toList());
        return distinctElements.stream();
    }
    //What was done above is that I am making a list of distinct elements using filter. Filter has to take
    //in a predicate, which to my inderstanding is a method that does something for each
    //thing in a stream, but you can't do it directly so we do it this way.

    /**
     * @param character starting character of Person objects' name
     * @return a Stream of respective
     */ //TODO
    //a very long line but this is what I know how to do. You make a list that is equal to
    //a stream of people
    //you filter out the people whose name doesn't start with character
    //then you return that list as a stream
    public Stream<Person> getUniquelyNamedPeopleStartingWith(Character character) {
        List <Person> list = people.stream().filter(Person -> !Person.getName().startsWith(character.toString())).collect(Collectors.toList());
        return list.stream();
    }

    //creating a predicate to filter out people whose name does not start with character
    //OOP NEVERMIND i dont even have to make a separate predicate function...???

    /**
     * @param n first `n` Person objects
     * @return a Stream of respective
     */ //TODO
    public Stream<Person> getFirstNUniquelyNamedPeople(int n) {
        //i dont get what this is asking for
        //is it asking for a person at a specific index?
        //is it asking for everyone with that name???
        //im confused
        return null;
    }

    /**
     * @return a mapping of Person Id to the respective Person name
     */ // TODO
    public Map<Long, String> getIdToNameMap() {
        //somehow use get personal Id...and get name...then use Collectors.toMap to turn it into a map
        //Map<Person -> Person::getPersonalId, Person -> Person::getName>
        //oh wow I did it!!!! I think
        return people.stream().collect(Collectors.toMap(Person::getPersonalId, Person::getName));
    }


    /**
     * @return Stream of Stream of Aliases
     */ // TODO
    public Stream<Stream<String>> getNestedAliases() {

        return null;
    }


    /**
     * @return Stream of all Aliases
     */ // TODO
    public Stream<String> getAllAliases() {
        return null;
    }

    // DO NOT MODIFY
    public Boolean contains(Person p) {
        return people.contains(p);
    }

    // DO NOT MODIFY
    public void clear() {
        people.clear();
    }

    // DO NOT MODIFY
    public int size() {
        return people.size();
    }

    @Override // DO NOT MODIFY
    public Iterator<Person> iterator() {
        return people.iterator();
    }
}
