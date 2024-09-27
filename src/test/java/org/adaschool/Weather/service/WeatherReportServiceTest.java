package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class WeatherReportServiceTest {

    @Test
    public void testGetWeatherReport() {
        // Mock the RestTemplate
        RestTemplate restTemplate = mock(RestTemplate.class);

        // Mock the response from the API
        WeatherApiResponse response = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(25.0);
        main.setHumidity(60.0);
        response.setMain(main);

        // Stubbing the RestTemplate to return the mock response when called
        when(restTemplate.getForObject(anyString(), Mockito.eq(WeatherApiResponse.class))).thenReturn(response);

        // Call the service
        WeatherReportService weatherReportService = new WeatherReportService();
        WeatherReport weatherReport = weatherReportService.getWeatherReport(37.8267, -122.4233);

        // Verificar que el resultado es el esperado
        assertEquals(25.0, weatherReport.getTemperature());
        assertEquals(60.0, weatherReport.getHumidity());
    }
}