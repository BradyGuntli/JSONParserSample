package com.JSONParseExampleService.JSONParseExample.Service;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import com.JSONParseExampleService.JSONParseExample.Service.JSONPetParser;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.JSONParseExampleService.JSONParseExample.Domain.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class JSONPetParserTest {

    private String jsonPetStringWithAllFields = "{\"PetName\":\"Alfredo\",\"PetType\":\"Cat\",\"PetAge\":\"7\",\"PetBreed\":\"Bengal\",\"PetFavoriteToy\":\"Yarn\"}";
    private String jsonPetStringWithMissingPetNameField = "{\"PetName\":\"\",\"PetType\":\"Cat\",\"PetAge\":\"7\",\"PetBreed\":\"Bengal\",\"PetFavoriteToy\":\"Yarn\"}";
    private String jsonPetStringWithMissingPetTypeField = "{\"PetName\":\"Alfredo\",\"PetType\":\"\",\"PetAge\":\"7\",\"PetBreed\":\"Bengal\",\"PetFavoriteToy\":\"Yarn\"}";
    private String jsonPetStringWithMissingPetAgeField = "{\"PetName\":\"Alfredo\",\"PetType\":\"Cat\",\"PetAge\":\"\",\"PetBreed\":\"Bengal\",\"PetFavoriteToy\":\"Yarn\"}";
    private String jsonPetStringWithMissingPetBreedField = "{\"PetName\":\"Alfredo\",\"PetType\":\"Cat\",\"PetAge\":\"7\",\"PetBreed\":\"\",\"PetFavoriteToy\":\"Yarn\"}";
    private String jsonPetStringWithMissingPetFavoriteToyField = "{\"PetName\":\"Alfredo\",\"PetType\":\"Cat\",\"PetAge\":\"7\",\"PetBreed\":\"Bengal\",\"PetFavoriteToy\":\"\"}";

    @Test
    public void testThatJSONPetParserCanSuccessfullyParseAllFieldsIfAllFieldsAreProvided(){

        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();

        Logger logger = (Logger) LoggerFactory.getLogger(JSONPetParser.class);
        ((ch.qos.logback.classic.Logger) logger).addAppender(listAppender); // Explicit casting

        JSONPetParser jsonPetParser = new JSONPetParser();

        Pet petForTesting = new Pet();

        petForTesting = jsonPetParser.parsePetFromJSONAndReturnsPet(jsonPetStringWithAllFields);

        listAppender.stop();

        assertEquals("Alfredo", petForTesting.getPetName(), "Tests that petName from pet object is equal to petName from jsonPetStringWithAllFields");
        assertEquals("Cat", petForTesting.getPetType(), "Tests that petType from pet object is equal to petType from jsonPetStringWithAllFields");
        assertEquals(7, petForTesting.getPetAge(), "Tests that petAge from pet object is equal to petAge from jsonPetStringWithAllFields");
        assertEquals("Bengal", petForTesting.getPetBreed(), "Tests that petBreed from pet object is equal to petBreed from jsonPetStringWithAllFields");
        assertEquals("Yarn", petForTesting.getPetFavoriteToy(), "Tests that petFavoriteToy from pet object is equal to petFavoriteToy from jsonPetStringWithAllFields");

        List<ILoggingEvent> logs = listAppender.list;
        assertTrue(!logs.isEmpty(), "Expected more than 0 log statements");

        for (ILoggingEvent log : logs){
            assertEquals(Level.INFO, log.getLevel());
        }

    }

    @Test
    public void testThatJSONPetParserSuccessfullyWarnsIfPetNameIsNotProvided(){

        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();

        Logger logger = (Logger) LoggerFactory.getLogger(JSONPetParser.class);
        ((ch.qos.logback.classic.Logger) logger).addAppender(listAppender); // Explicit casting

        JSONPetParser jsonPetParser = new JSONPetParser();

        Pet petForTesting = new Pet();

        petForTesting = jsonPetParser.parsePetFromJSONAndReturnsPet(jsonPetStringWithMissingPetNameField);

        listAppender.stop();

        assertEquals(null, petForTesting.getPetName(), "Tests that petName from pet object is null when not provided");
        assertEquals("Cat", petForTesting.getPetType(), "Tests that petType from pet object is equal to petType from jsonPetStringWithAllFields");
        assertEquals(7, petForTesting.getPetAge(), "Tests that petAge from pet object is equal to petAge from jsonPetStringWithAllFields");
        assertEquals("Bengal", petForTesting.getPetBreed(), "Tests that petBreed from pet object is equal to petBreed from jsonPetStringWithAllFields");
        assertEquals("Yarn", petForTesting.getPetFavoriteToy(), "Tests that petFavoriteToy from pet object is equal to petFavoriteToy from jsonPetStringWithAllFields");

        List<ILoggingEvent> logs = listAppender.list;
        assertTrue(!logs.isEmpty(), "Expected more than 0 log statements");

        assertEquals(Level.WARN, logs.get(1).getLevel());

    }

    @Test
    public void testThatJSONPetParserSuccessfullyWarnsIfPetTypeIsNotProvided(){

        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();

        Logger logger = (Logger) LoggerFactory.getLogger(JSONPetParser.class);
        ((ch.qos.logback.classic.Logger) logger).addAppender(listAppender); // Explicit casting

        JSONPetParser jsonPetParser = new JSONPetParser();

        Pet petForTesting = new Pet();

        petForTesting = jsonPetParser.parsePetFromJSONAndReturnsPet(jsonPetStringWithMissingPetTypeField);

        listAppender.stop();

        assertEquals("Alfredo", petForTesting.getPetName(), "Tests that petName from pet object is equal to petName from jsonPetStringWithAllFields");
        assertEquals(null, petForTesting.getPetType(), "Tests that petType from pet object is null when not provided");
        assertEquals(7, petForTesting.getPetAge(), "Tests that petAge from pet object is equal to petAge from jsonPetStringWithAllFields");
        assertEquals("Bengal", petForTesting.getPetBreed(), "Tests that petBreed from pet object is equal to petBreed from jsonPetStringWithAllFields");
        assertEquals("Yarn", petForTesting.getPetFavoriteToy(), "Tests that petFavoriteToy from pet object is equal to petFavoriteToy from jsonPetStringWithAllFields");

        List<ILoggingEvent> logs = listAppender.list;
        assertTrue(!logs.isEmpty(), "Expected more than 0 log statements");

        assertEquals(Level.WARN, logs.get(2).getLevel());

    }

    @Test
    public void testThatJSONPetParserSuccessfullyWarnsIfPetAgeIsNotProvided(){

        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();

        Logger logger = (Logger) LoggerFactory.getLogger(JSONPetParser.class);
        ((ch.qos.logback.classic.Logger) logger).addAppender(listAppender); // Explicit casting

        JSONPetParser jsonPetParser = new JSONPetParser();

        Pet petForTesting = new Pet();

        petForTesting = jsonPetParser.parsePetFromJSONAndReturnsPet(jsonPetStringWithMissingPetAgeField);

        listAppender.stop();

        assertEquals("Alfredo", petForTesting.getPetName(), "Tests that petName from pet object is equal to petName from jsonPetStringWithAllFields");
        assertEquals("Cat", petForTesting.getPetType(), "Tests that petType from pet object is equal to petType from jsonPetStringWithAllFields");
        assertEquals(0, petForTesting.getPetAge(), "Tests that petAge from pet object is equal to default value of 0 when not provided");
        assertEquals("Bengal", petForTesting.getPetBreed(), "Tests that petBreed from pet object is equal to petBreed from jsonPetStringWithAllFields");
        assertEquals("Yarn", petForTesting.getPetFavoriteToy(), "Tests that petFavoriteToy from pet object is equal to petFavoriteToy from jsonPetStringWithAllFields");

        List<ILoggingEvent> logs = listAppender.list;
        assertTrue(!logs.isEmpty(), "Expected more than 0 log statements");

        assertEquals(Level.WARN, logs.get(3).getLevel());

    }

    @Test
    public void testThatJSONPetParserCanSuccessfullyParseAllFieldsIfPetBreedIsNotProvided(){

        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();

        Logger logger = (Logger) LoggerFactory.getLogger(JSONPetParser.class);
        ((ch.qos.logback.classic.Logger) logger).addAppender(listAppender); // Explicit casting

        JSONPetParser jsonPetParser = new JSONPetParser();

        Pet petForTesting = new Pet();

        petForTesting = jsonPetParser.parsePetFromJSONAndReturnsPet(jsonPetStringWithMissingPetBreedField);

        listAppender.stop();

        assertEquals("Alfredo", petForTesting.getPetName(), "Tests that petName from pet object is equal to petName from jsonPetStringWithAllFields");
        assertEquals("Cat", petForTesting.getPetType(), "Tests that petType from pet object is equal to petType from jsonPetStringWithAllFields");
        assertEquals(7, petForTesting.getPetAge(), "Tests that petAge from pet object is equal to petAge from jsonPetStringWithAllFields");
        assertEquals(null, petForTesting.getPetBreed(), "Tests that petBreed from pet object is equal null when not provided");
        assertEquals("Yarn", petForTesting.getPetFavoriteToy(), "Tests that petFavoriteToy from pet object is equal to petFavoriteToy from jsonPetStringWithAllFields");

        List<ILoggingEvent> logs = listAppender.list;
        assertTrue(!logs.isEmpty(), "Expected more than 0 log statements");

        assertEquals(Level.WARN, logs.get(4).getLevel());

    }

    @Test
    public void testThatJSONPetParserCanSuccessfullyParseAllFieldsIfPetFavoriteToyIsNotProvided(){

        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();

        Logger logger = (Logger) LoggerFactory.getLogger(JSONPetParser.class);
        ((ch.qos.logback.classic.Logger) logger).addAppender(listAppender); // Explicit casting

        JSONPetParser jsonPetParser = new JSONPetParser();

        Pet petForTesting = new Pet();

        petForTesting = jsonPetParser.parsePetFromJSONAndReturnsPet(jsonPetStringWithMissingPetFavoriteToyField);

        listAppender.stop();

        assertEquals("Alfredo", petForTesting.getPetName(), "Tests that petName from pet object is equal to petName from jsonPetStringWithAllFields");
        assertEquals("Cat", petForTesting.getPetType(), "Tests that petType from pet object is equal to petType from jsonPetStringWithAllFields");
        assertEquals(7, petForTesting.getPetAge(), "Tests that petAge from pet object is equal to petAge from jsonPetStringWithAllFields");
        assertEquals("Bengal", petForTesting.getPetBreed(), "Tests that petBreed from pet object is equal to petBreed from jsonPetStringWithAllFields");
        assertEquals(null, petForTesting.getPetFavoriteToy(), "Tests that petFavoriteToy from pet object is equal to null when not provided");

        List<ILoggingEvent> logs = listAppender.list;
        assertTrue(!logs.isEmpty(), "Expected more than 0 log statements");

        assertEquals(Level.WARN, logs.get(5).getLevel());

    }





}