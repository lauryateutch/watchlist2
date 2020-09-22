package com.openclassrooms.watchlist.service;

import jdk.nashorn.internal.ir.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import java.nio.file.*;

@Service

public class MovieRatingService {

  String apiUrl = "http://www.omdbapi.com/?apikey=yourapikey&t=";

    public String getMovieRating(String title) {
        try {
            RestTemplate template = new RestTemplate();

            ResponseEntity<ObjectNode> response;
            response = template.getForEntity( apiUrl + title, ObjectNode.class);
            ObjectNode jsonObject = response.getBody();

            return jsonObject.path("imdbRating").asText();
        } catch (Exception e) {
            System.out.println("Something went wrong " +
                    "while calling OMDB API" + e.getMessage());
            return null;
        }
    }
}

