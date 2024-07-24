package com.Captone.Bus_Ticket_Booking_Application.Controller;

import com.Captone.Bus_Ticket_Booking_Application.Entity.Booking;
import com.Captone.Bus_Ticket_Booking_Application.Entity.Bus;
import com.Captone.Bus_Ticket_Booking_Application.Entity.Passenger;
import com.Captone.Bus_Ticket_Booking_Application.Repository.BookingRepository;
import com.Captone.Bus_Ticket_Booking_Application.Repository.BusRepository;
import com.Captone.Bus_Ticket_Booking_Application.Repository.PassangerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("passenger")
public class BookingController {

    @Autowired
    PassangerRepository passangerRepository;

    @Autowired
    BusRepository busRepository;

    @Autowired
    BookingRepository bookingRepository;

    @GetMapping({ "{passengerId}/bookingseat/{busId}" })
    public String busSchedules(@PathVariable("passengerId") int passengerId, @PathVariable("busId") int busId,
                               Model model) {
        Passenger p = passangerRepository.findById(passengerId).get();
        String passengerName = p.getPassengerName();

        Bus bus = busRepository.findById(busId).get();
        String busName = bus.getBusName();
        int price = bus.getPrice();
        int seatsAvailable = bus.getAvailableSeats();
        Booking booking = new Booking();
        booking.setPassengerId(passengerId);
        booking.setPassengerName(passengerName);
        booking.setBusId(busId);
        booking.setBusName(busName);
        booking.setPrice(price);
        model.addAttribute("bookingForm", booking);
        model.addAttribute("passengerId", passengerId);
        model.addAttribute("passengerName", passengerName);
        model.addAttribute("seatsAvailable", seatsAvailable);
        return "booking";
    }

    @PostMapping({ "booking/save" })
    public String postRegister(@ModelAttribute Booking booking, Model model) {
        Bus bus = busRepository.findById(booking.getBusId()).get();

        booking.setBookedTime(LocalDateTime.now());

        int seats = booking.getSeatQty();
        int available_seats = bus.getAvailableSeats();

        if (seats <= available_seats) {
            bookingRepository.save(booking);

            bus.setAvailableSeats(available_seats - seats);

            busRepository.save(bus);

            model.addAttribute("message", "Booking Confirmed");
        }

        else {

            model.addAttribute("message", "Booking Failed");
        }

        return "redirect:/passenger/welcome/" + booking.getPassengerId();
    }

    @GetMapping({ "booking/{passengerId}" })
    public String busSchedules(@PathVariable("passengerId") int passengerId, Model model) {

        int size = bookingRepository.findByPassengerId(passengerId).size();

        if (size >= 1) {
            Booking busbooked = bookingRepository.findByPassengerId(passengerId).get(size - 1);
            model.addAttribute("booked", busbooked);
            model.addAttribute("bookings", bookingRepository.findByPassengerId(passengerId));

        } else {
            model.addAttribute("booked", null);

            model.addAttribute("bookings", null);
        }

        return "booking_history";
    }

}
