package com.laurentiuspilca.ssia.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.laurentiuspilca.ssia.entities.Workout;
import com.laurentiuspilca.ssia.service.WorkoutService;

@RestController
@RequestMapping("/workout")
public class RestWorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping("/")
    public void add(@RequestBody Workout workout) {
        workoutService.saveWorkout(workout);
    }

    @GetMapping
    public List<Workout> findAll() {
    	
//    	Workout workout = new Workout();
//    	workout.setId(1);
//    	workout.setUser("israr");
//    	workout.setStart(null);
//    	workout.setEnd(null);
//    	workout.setDifficulty(4);
        return workoutService.findWorkouts();
    }

    
    
    @GetMapping("/delete/{id}")
    public List<Workout> delete(@PathVariable Integer id) {
    	//System.out.println("Inside delete : "+id);
        workoutService.deleteWorkout(id);
        return workoutService.findWorkouts();
    }
    
	@PostMapping("/addWorkout")
	public void addWorkout(HttpServletRequest request) {
		System.out.println("In Resource server: "+ request.getParameter("workout"));
		Gson g = new Gson();
		Workout workout = g.fromJson(request.getParameter("workout"), Workout.class);
		System.out.println("Java object" + workout);
		
		workoutService.saveWorkout(workout);
	}
 
    @GetMapping("/androidCheck")
    public List<Workout> androidCheck() {
    	//System.out.println("Inside delete : "+id);
        return workoutService.findWorkouts();
    }
	
	
}
