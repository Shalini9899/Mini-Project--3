package com.Captone.Bus_Ticket_Booking_Application.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;



@Entity(name = "bus")
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name="Bus.Choose", query = "from bus where fromLoc =:from and toLoc=:to and startDate =:sdate")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    private int busId;

//    @NotEmpty
    @Column(name = "bus_name")
    private String busName;

//    @NotEmpty
    @Column(name = "from_loc")
    private String fromLoc;

//    @NotEmpty
    @Column(name = "to_loc")
    private String toLoc;

//    @NotEmpty
    @Column(name = "start_date")
    private LocalDate startDate;

//    @NotEmpty
    @Column(name = "end_date")
    private LocalDate endDate;

//    @NotEmpty
    @Column(name = "start_time")
    private LocalTime startTime;

//    @NotEmpty
    @Column(name = "end_time")
    private LocalTime endTime;

//    @NotEmpty
    private String route;

//    @NotEmpty
    @Column(name = "total_seats")
    private int totalSeats;

//    @NotEmpty
    @Column(name = "available_seats")
    private int availableSeats;

//    @NotEmpty
    private int price;

}
