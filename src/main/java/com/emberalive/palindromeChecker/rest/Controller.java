package com.emberalive.palindromeChecker.rest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    @GetMapping("/")
    public ResponseEntity<Resource> index() {
        logger.info("Accessed the home page");
        Resource home = new ClassPathResource("templates/home.html");
        return ResponseEntity.ok().body(home);
    }
    @PostMapping("/check")
    public ResponseEntity<Map<String, String>> CheckPallindrome(@RequestParam("palindrome")String palindrome) {
        logger.debug("recieved input" + palindrome);
        String cleanedString = palindrome.toLowerCase().trim();
        String reversedString = new StringBuilder(cleanedString).reverse().toString();
        
        String message;
        String backgroundColor;
        
        if (palindrome.equals(reversedString)) {
            message = palindrome + "is a palindrome.";
            backgroundColor = "green";
            logger.info("Input is a palindrome: {}", palindrome);
        } else {
            message = palindrome + " is not a palindrome.";
            backgroundColor = "red";
            logger.info("Input is not a palindrome: {}", palindrome);
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        response.put("backgroundColor", backgroundColor);
        
        return ResponseEntity.ok(response); // Return JSON response
    }
}
