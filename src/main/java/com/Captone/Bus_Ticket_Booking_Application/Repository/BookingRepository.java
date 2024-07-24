package com.Captone.Bus_Ticket_Booking_Application.Repository;

import com.Captone.Bus_Ticket_Booking_Application.Entity.Booking;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@Tag(name = "Booking - Rest API Controllers", description = "Booking API")
@RepositoryRestResource(collectionResourceRel = "booking", path="booking")
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    List<Booking> findByPassengerId(@Param("passengerId") int passengerId);

}
