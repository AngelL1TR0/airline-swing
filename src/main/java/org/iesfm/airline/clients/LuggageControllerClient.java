package org.iesfm.airline.clients;

import org.iesfm.airline.clients.dto.LuggageDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;
import java.util.stream.Collectors;

public interface LuggageControllerClient {

    @GET("/flights/{flightId}/passengers/{nif}/luggage")
    Call<List<LuggageDto>> list(
            @Query("flightId") String flightId,
            @Query("nif") String nif
    );
    @POST("/flights/{flightId}/passengers/{nif}/luggage")
    Call<Void> add(
            @Query("flightId") String flightId,
            @Query("nif") String nif,
            @Body LuggageDto luggage
    );
}
