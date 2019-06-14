package com.demo.ws.client.api;

import com.demo.ws.client.WsClientApplication;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.jayway.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WsClientApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryApiIT {

    @LocalServerPort
    private int port;

    @Rule
    public WireMockRule backendService = new WireMockRule(4545);

    @Before
    public void setup() {
        RestAssured.basePath = "/countries";
        RestAssured.port = port;
    }


    @Test
    public void testCountry() {
        backendService.stubFor(post(urlEqualTo("/ws/countries"))
                .willReturn(
                        aResponse()
                                .withStatus(200)
                                .withHeader("content-type", "text/xml")
                                .withBodyFile("CountrySuccessResponse.xml")
                )

        );

        RestAssured
                .get("/{name}", "spain")
                .then()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString("spain"));
    }
}