package com.Captone.Bus_Ticket_Booking_Application.Controller;

import com.Captone.Bus_Ticket_Booking_Application.Entity.Bus;
import com.Captone.Bus_Ticket_Booking_Application.Entity.Passenger;
import com.Captone.Bus_Ticket_Booking_Application.Repository.BookingRepository;
import com.Captone.Bus_Ticket_Booking_Application.Repository.BusRepository;
import com.Captone.Bus_Ticket_Booking_Application.Repository.PassangerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("passenger")
public class PassengerController {

	@Autowired
	PassangerRepository passangerRepository;

	@Autowired
	BusRepository busRepository;

	@Autowired
	BookingRepository bookingRepository;

	@GetMapping({ "register" })
	public String register(Model model) {
		model.addAttribute("passengerForm", new Passenger());
		return "register";
	}

	@PostMapping({ "register/save" })
	public String postRegister(@ModelAttribute Passenger passengerEntity, Model model) {
		passangerRepository.save(passengerEntity);

		// default spring security login page
		return "redirect:/login";
	}

	@GetMapping({ "welcome/{passengerId}" })
	public String welcome(@PathVariable("passengerId") int passengerId, Model model) {
		String name = passangerRepository.findById(passengerId).get().getPassengerName();
		model.addAttribute("id", passengerId);
		model.addAttribute("name", name);

		List<Bus> buses = busRepository.findAll();
		List<String> fromList = new ArrayList<>();
		List<String> toList = new ArrayList<>();

		for (Bus b : buses) {
			fromList.add(b.getFromLoc());
			toList.add(b.getToLoc());
		}

		model.addAttribute("from", fromList);
		model.addAttribute("to", toList);

		return "welcome";
	}

	@GetMapping({"modifyprofile/{passengerId}" })
	public String getPrescriptionModify(@PathVariable("passengerId") int passengerId, Model model) {
		String name = passangerRepository.findById(passengerId).get().getPassengerName();
		model.addAttribute("id", passengerId);
		model.addAttribute("name", name);

		Passenger passengerEntity = passangerRepository.findById(passengerId).get();
		passengerEntity.setPassengerName(passengerEntity.getPassengerName());
		passengerEntity.setMobile_no(passengerEntity.getMobile_no());
		passengerEntity.setEmail(passengerEntity.getEmail());
		passengerEntity.setLogin_password(passengerEntity.getLogin_password());

		model.addAttribute("modifyPassengerForm", passengerEntity);

		return "profile";
	}

	@PostMapping({ "modifyprofile/save" })
	public String postPrescriptionModify(@ModelAttribute("passengerEntity") Passenger passengerEntity) {

		passangerRepository.save(passengerEntity);

		return "redirect:/passengerEntity/welcome/" + passengerEntity.getPassengerId();
	}
}