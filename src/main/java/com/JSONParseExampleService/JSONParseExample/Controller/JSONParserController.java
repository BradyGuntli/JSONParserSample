package com.JSONParseExampleService.JSONParseExample.Controller;

import com.JSONParseExampleService.JSONParseExample.Domain.Pet;
import com.JSONParseExampleService.JSONParseExample.Service.JSONPetParser;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
public class JSONParserController {

    @Autowired
    private JSONPetParser jsonPetParser;

    private static final Logger logger = LoggerFactory.getLogger(JSONParserController.class);

    @GetMapping("/petInfo/{JSONAsString}")
    public ResponseEntity<String> parseJSONData(@PathVariable String JSONAsString) {
        logger.info("Path Variable detected, Path Variable is = " + JSONAsString);
        Pet pet = new Pet();
        pet = jsonPetParser.parsePetFromJSONAndReturnsPet(JSONAsString);
        if (null != pet.getPetName() & null != pet.getPetType() & pet.getPetAge() != 0 & null != pet.getPetBreed() & null != pet.getPetFavoriteToy()) {
            logger.info("pet object to be returned is equal to = " + pet.toString() + ", returning ResponseEntity with HTTP status of 200 containing pet object");
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Pet data parsed inside service is equal to: " + pet.toString(), HttpStatus.OK);
            return responseEntity;
        } else {
            logger.warn("pet object lacks name, returning ResponseEntity with HTTP Status 400");
            ResponseEntity<String> responseEntity = new ResponseEntity<>("JSON sent into service lacked required data", HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
    }
}
