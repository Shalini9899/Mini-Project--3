package com.Captone.Bus_Ticket_Booking_Application.Controller;

import com.Captone.Bus_Ticket_Booking_Application.Entity.Bus;
import com.Captone.Bus_Ticket_Booking_Application.Repository.BusRepository;
import com.Captone.Bus_Ticket_Booking_Application.Repository.PassangerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("passenger")
public class BusController {

	@Autowired
	PassangerRepository passangerRepository;
	
	@Autowired
	BusRepository busRepo;

	@GetMapping({ "busschedules/{passengerId}" })
	public String busSchedules(@PathVariable("passengerId") int passengerId, Model model) {
		List<Bus> buses = busRepo.findAll();
		String name = passangerRepository.findById(passengerId).get().getPassengerName();
		model.addAttribute("buses", buses);
		model.addAttribute("id", passengerId);
		model.addAttribute("name", name);
		return "bus_schedules";
	}
	
	
	@GetMapping({ "busschedules" })
	public String busSchedulesSearch(@RequestParam("passengerId") int passengerId, @RequestParam("sDate") LocalDate sDate, @RequestParam("fromLoc") String fromLoc, @RequestParam("toLoc") String toLoc,  Model model) {
		List<Bus> buses = busRepo.findByFromLocAndToLocAndStartDate(fromLoc, toLoc, sDate);
		model.addAttribute("buses", buses);
		model.addAttribute("passengerId", passengerId);
		
		return "bus_schedules";
	}

}
