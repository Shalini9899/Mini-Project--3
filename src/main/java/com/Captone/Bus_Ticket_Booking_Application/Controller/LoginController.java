package com.Captone.Bus_Ticket_Booking_Application.Controller;

import com.Captone.Bus_Ticket_Booking_Application.Entity.Passenger;
import com.Captone.Bus_Ticket_Booking_Application.Repository.PassangerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@Autowired
	PassangerRepository passangerRepository;


	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping({ "/" })
	public String loggedIn(@AuthenticationPrincipal User user, Model model) {

		String username = user.getUsername();
		String url = "";
		if (passangerRepository.findByEmail(username) != null) {
			Passenger p = passangerRepository.findByEmail(username);
			int passengerId = p.getPassengerId();
			url = "redirect:/passenger/welcome/" + passengerId;
		}

		return url;
	}

}