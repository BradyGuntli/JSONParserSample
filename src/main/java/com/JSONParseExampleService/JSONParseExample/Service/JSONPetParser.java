package com.JSONParseExampleService.JSONParseExample.Service;

import com.JSONParseExampleService.JSONParseExample.Domain.Pet;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JSONPetParser {

    private static final Logger logger = LoggerFactory.getLogger(JSONPetParser.class);

    public Pet parsePetFromJSONAndReturnsPet(String JSONAsString) {
        logger.info("Attempting to extract Pet object from JSON Path Variable");
        Pet petFromJsonObject = parseJSONStringAndReturnPet(JSONAsString);
        logger.info("Returning Pet object from data taken from JSON Path Variable");
        return petFromJsonObject;
    }

    private Pet parseJSONStringAndReturnPet(String JSONAsString) {
        JSONObject jsonObject = new JSONObject(JSONAsString);

        String petName = jsonObject.getString("PetName");
        String petType = jsonObject.getString("PetType");
        String petAgeAsString = jsonObject.getString("PetAge");

        int petAge = 0;
        if (null != petAgeAsString & !petAgeAsString.isEmpty()) {
            petAge = Integer.parseInt(petAgeAsString);
        }

        String petBreed = jsonObject.getString("PetBreed");
        String petFavoriteToy = jsonObject.getString("PetFavoriteToy");

        Pet petToBeReturned = new Pet();

        petToBeReturned = creatAndPopulatePet(petName, petType, petAge, petBreed, petFavoriteToy);

        return petToBeReturned;

    }

    private Pet creatAndPopulatePet(String petName, String petType, int petAge, String petBreed, String petFavoriteToy) {
        Pet petForDataPopulation = new Pet();

        if (null != petName & !petName.isEmpty()) {
            petForDataPopulation.setPetName(petName);
            logger.info("petName found in jsonObject, petName = " + petName + ", will set value petName in pet object");
        } else {
            logger.warn("Value petName is not provided, will not set petName");
        }

        if (null != petType & !petType.isEmpty()) {
            petForDataPopulation.setPetType(petType);
            logger.info("petType found in jsonObject, petType = " + petType + ", will set value petType in pet object");
        } else {
            logger.warn("Value petType is not provided, will not set petType");
        }

        if (0 != petAge) {
            petForDataPopulation.setPetAge(petAge);
            logger.info("petAge found in jsonObject, petAge = " + petAge + ", will set value petAge in pet object");
        } else {
            logger.warn("Value petAge is equal to 0, petAge value is inaccurate, will not set petAge");
        }

        if (null != petBreed & !petBreed.isEmpty()) {
            petForDataPopulation.setPetBreed(petBreed);
            logger.info("petBreed found in jsonObject, petBreed = " + petBreed + ", will set value petBreed in pet object");
        } else {
            logger.warn("Value petBreed is not provided, will not set petBreed");
        }

        if (null != petFavoriteToy & !petFavoriteToy.isEmpty()) {
            petForDataPopulation.setPetFavoriteToy(petFavoriteToy);
            logger.info("petFavoriteToy found in jsonObject, petFavoriteToy = " + petFavoriteToy + ", will set value petFavoriteToy in pet object");
        } else {
            logger.warn("Value petFavoriteToy is not provided, will not set petFavoriteToy");
        }

        return petForDataPopulation;

    }
}
