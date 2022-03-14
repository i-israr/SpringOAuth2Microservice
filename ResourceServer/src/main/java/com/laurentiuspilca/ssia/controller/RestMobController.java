package com.laurentiuspilca.ssia.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class RestMobController {
	
    @GetMapping("/ship/list")
    public String getAllData(@RequestParam String username,@RequestHeader("Authorization") String token) {
    		System.out.println("Username:"+username);
    		System.out.println("TOken:"+token);
//        Optional<User> user=userRepository.findByUsername(username);
//        Long id=user.get().getId();
//        Shipment shipment= (Shipment) shipmentRespository.findAll();
//        shipment.getDriver();
//        if (id.equals(shipment.getDriver()))
//        {
//            return  shipmentRespository.findAll();
//        }

        return "";
    }	
}
