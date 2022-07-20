package com.fitnessapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.fitnessapp.response.Response;
import com.fitnessapp.service.ExerciseService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/api/exercise")

public class ExerciseController {
    @Autowired
    ExerciseService exerciseService;

    @PostMapping(value = "/save", headers = "Content-Type= multipart/form-data")
    public Response save(@RequestParam("image") MultipartFile multipartFile,
                         @RequestParam("categoryId") Long categoryId,
                         @RequestParam("name") String name,
                         @RequestParam("description") String description) throws IOException {
        return new Response(HttpStatus.OK.value(), exerciseService.save(multipartFile, categoryId, name, description));
    }

    @GetMapping(value = "/list")
    public Response findAllCategories() {
        return new Response(HttpStatus.OK.value(), exerciseService.getAllExercises());
    }

    @GetMapping(value = "/{id}")
    public Response findById(@PathVariable Long id) {
        return new Response(HttpStatus.OK.value(), exerciseService.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    public Response deleteById(@PathVariable Long id) {
        exerciseService.deleteById(id);
        return new Response(HttpStatus.NO_CONTENT.value(), "Successfully deleted");
    }

    @GetMapping(value = "/admin")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }
}


