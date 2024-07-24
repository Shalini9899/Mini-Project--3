package com.Captone.Bus_Ticket_Booking_Application.Repository;

import com.Captone.Bus_Ticket_Booking_Application.Entity.Passenger;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@Tag(name = "Passenger_Entity - Rest API Controllers", description = "Passenger_Entity API")
@RepositoryRestResource(collectionResourceRel = "passenger", path="passenger")
public interface PassangerRepository extends JpaRepository<Passenger,Integer> {
    public Passenger findByEmail(@Param("email") String email);

}
