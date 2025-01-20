package com.emberalive.palindromeChecker.rest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    @GetMapping("/")
    public ResponseEntity<Resource> index() {
        Resource home = new ClassPathResource("templates/home.html");
        return ResponseEntity.ok().body(home);
    }
    @PostMapping("/check")
    public ResponseEntity<Map<String, String>> CheckPallindrome(@RequestParam("palindrome")String palindrome) {
        String cleanedString = palindrome.toLowerCase().trim();
        String reversedString = new StringBuilder(cleanedString).reverse().toString();
        
        String message;
        String backgroundColor;
        
        if (palindrome.equals(reversedString)) {
            message = palindrome + "is a palindrome.";
            backgroundColor = "green";
        } else {
            message = palindrome + " is not a palindrome.";
            backgroundColor = "red";
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        response.put("backgroundColor", backgroundColor);
        
        return ResponseEntity.ok(response); // Return JSON response
    }
}
