package org.iesfm.airline.forms;

import org.iesfm.airline.FlightTableModel;
import org.iesfm.airline.clients.FlightControllerClient;
import org.iesfm.airline.clients.dto.FlightDto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.swing.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ShowFlightsForm {
    public JPanel mainPanel;
    private JTable flightsTable;
    private JButton addButton;
    private JButton modifyButton;
    private JButton deleteButton;

    public ShowFlightsForm() {
        // Crea un retrofit que hace llamadas al localhost:8080
        // En ese puerto estára escuchando airline-rest
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        // Usando el retrofit anterior creamos un cliente para el controlador
        // de vuelos que está eschando en localhost:8080
        FlightControllerClient flightsClient =
                retrofit.create(FlightControllerClient.class);

        // Esta sentencia se ejecuta en el hilo de UI
        // Dame todos los vuelos, da igual el origen y destino
        Call<List<FlightDto>> flightsCall =
                flightsClient.list(
                        null,
                        null
                );

        // Esta sentencia se ejecuta en el hilo de UI
        flightsCall.enqueue(
                new Callback<>() {
                    @Override
                    public void onResponse(
                            Call<List<FlightDto>> call,
                            Response<List<FlightDto>> response) {
                        if(response.code() == 200) {
                            // Esto se vuelve a ejecutar en el hilo de UI
                            List<FlightDto> flights = response.body();
                            flightsTable.setModel(new FlightTableModel(flights));
                        } else {
                            JOptionPane.showMessageDialog(mainPanel, "No se han podido obtener los vuelos " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(
                            Call<List<FlightDto>> call,
                            Throwable throwable
                    ) {
                        JOptionPane.showMessageDialog(
                                mainPanel,
                                "Ha habido un errror al pedir los vuelos: " + throwable.getMessage()
                        );
                    }
                }
        );


    }
}
