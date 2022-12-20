package org.iesfm.airline.clients;

import org.iesfm.airline.clients.dto.FlightPassengerDto;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface PassengerControllerClient {

    @GET("/flights/{flightId}/passengers")
    Call <List<FlightPassengerDto>> listFlightPassengers(
            @Query("flightId") String flightId
    );

    @POST("/flights/{flightId}/passengers")
    Call <Void> addPassenger(
            @Path("flightId") String flightId,
            @Body FlightPassengerDto passenger
    );

    @PUT("/flights/{flightId}/passengers/{nif}")
    Call <Void> updatePassenger(
            @Query("flightId") String flightId,
            @Query("nif") String nif,
            @Body FlightPassengerDto passenger
    );

    @DELETE("/flights/{flightId}/passengers/{nif}")
    Call<Void> updatePassenger(
            @Query("flightId") String flightId,
            @Query("nif") String nif
    );
}
