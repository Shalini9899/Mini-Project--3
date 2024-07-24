package com.Captone.Bus_Ticket_Booking_Application.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "passenger")
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private int passengerId;

    @NotEmpty
    @Column(name = "passenger_name")
    private String passengerName;

    @NotEmpty
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;

    @NotEmpty
    @Pattern(regexp = "[6-9]{1}[0-9]{9}")
    private String mobile_no;

    @NotEmpty
    private String login_password;
}
