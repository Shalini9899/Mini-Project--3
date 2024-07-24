package com.Captone.Bus_Ticket_Booking_Application.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Booking")
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int bookingId;

    @Column(name = "passenger_id")
    private int passengerId;

    @Column(name = "passenger_name")
    private String passengerName;

    @Column(name = "bus_id")
    private int busId;

    @Column(name = "bus_name")
    private String busName;

    @Column(name = "seat_qty")
    private int seatQty;

    @Column(name = "booked_price")
    private int price;

    private int amount;

    @Column(name = "booked_time")
    private LocalDateTime bookedTime;
}
