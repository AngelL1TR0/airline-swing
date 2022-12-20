package org.iesfm.airline.clients;

import retrofit2.Call;
import org.iesfm.airline.clients.dto.FlightDto;
import retrofit2.http.*;

import java.util.List;

public interface FlightControllerClient {

    @GET("/fligths")
    Call <List<FlightDto>> list(
            @Query("origin") String  origin,
            @Query( "destination") String  destination
    );

    @GET("/flights/{flightId}")
    Call<FlightDto> getFlight(
            @Path("flightId") String flightId
    );

    @POST("/flights")
    Call<Void> add(
            @Body FlightDto flight
    );

    @PUT("/flights/{flightId}")
    Call<Void> updateFlight(
            @Path("flightId") String flightId,
            @Body FlightDto flight
    );

    @DELETE("/flights/{flightId}")
    Call<Void> delete(
            @Query("flightId") String flightId
    );
}
