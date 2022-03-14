package com.example.demo.controller;

import java.util.List;

import org.hibernate.boot.registry.classloading.spi.ClassLoaderService.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.demo.model.Workout;
import com.example.demo.service.WorkoutService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WorkoutController {

	@Autowired
	private WorkoutService workoutService;
	@Autowired
	OAuth2AuthorizedClientService oauth2ClientService;

	private String gatway= "http://localhost:8083";
	@Autowired
	WebClient webClient;
	Gson g = new Gson();
	@GetMapping("/workout")
	public String findAllData(Model model, @AuthenticationPrincipal OidcUser principal) {

		String url = gatway + "/workout";

		List<Workout> workout = webClient.get().uri(url).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Workout>>() {
				}).block();
		
		model.addAttribute("workout", workout);
		return "workout";

	}

	@GetMapping("/workout/delete/{id}")
	public String deleteData(Model model, @PathVariable Integer id) {

		String url = gatway + "/workout/delete/" + id;
		System.out.println("Inside get deletemathode : " + url);
		List<Workout> workout = webClient.get().uri(url).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Workout>>() {
				}).block();
		model.addAttribute("workout", workout);

		return "workout";
	}

	@GetMapping("/addWorkout")
	public String addStudent() {
		return "addWorkout";
	}	
	
	
	@GetMapping("/landPage")
	public String landPage() {
		return "landPage";
	}	
	
	@PostMapping("/addWorkout")
	public String addWorkout(@RequestParam String username, @RequestParam String starttime,
			@RequestParam String endtime,@RequestParam String diflevel, Model model) {

		Workout workout = new Workout();
		workout.setUser(username);
		workout.setStart(starttime);
		workout.setEnd(endtime);
		workout.setDifficulty(diflevel);	
		System.out.println("Workout :"+ workout);
		String workoutJ = new Gson().toJson(workout);

		String url = gatway + "/workout/addWorkout";
		
		//String s = webClient.post().uri(url).bodyValue(workout).retrieve().toString();
	   List<Workout> s = webClient.post().uri(url)
	   .body(BodyInserters.fromFormData("workout",workoutJ))
	   .retrieve().bodyToMono(new ParameterizedTypeReference<List<Workout>>() {}).block();

		System.out.println(s);
		
		
	//	webClient.post().uri(url).
//		.bodyValue(workout)
	//	.body(Mono.just(workout), Workout.class);
//		.retrieve()
//		.bodyToMono(Workout.class);	
		
		//workoutService.saveWorkout(workout);
		
		return "redirect:/workout";
	}
	
	
	
	
	
	
	
	
	
}
