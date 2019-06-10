package com.demo.ws.client.api;

import com.demo.ws.client.client.CountryWSClient;
import com.demo.ws.client.model.Country;
import com.demo.ws.countries.GetCountryRequest;
import com.demo.ws.countries.GetCountryResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryApi {

    private final CountryWSClient countryWSClient;

    public CountryApi(CountryWSClient countryWSClient) {
        this.countryWSClient = countryWSClient;
    }

    @GetMapping(value = "/countries/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Country> getCountryDetails(@PathVariable("name") String countryName) {
        GetCountryResponse countryResponse = countryWSClient.getCountryDetails(countryRequest(countryName));
        return ResponseEntity.ok(mapToCountryResponse(countryResponse));
    }

    private GetCountryRequest countryRequest(String countryName) {
        GetCountryRequest countryRequest = new GetCountryRequest();
        countryRequest.setName(countryName);
        return countryRequest;
    }

    private Country mapToCountryResponse(GetCountryResponse countryResponse) {
        return new Country(
                countryResponse.getCountry().getName(),
                countryResponse.getCountry().getCapital(),
                countryResponse.getCountry().getCurrency().value(),
                countryResponse.getCountry().getPopulation()
        );
    }

}
