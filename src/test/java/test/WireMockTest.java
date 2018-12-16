
package test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.sb.config.ApplicationConfig;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationConfig.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WireMockTest {

	private static final int REMOTE_SERVER_PORT = 8081;

    @LocalServerPort
    private int port;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(REMOTE_SERVER_PORT));

	@Test
	public void callWireMock() throws Exception {

	    // givent this remote service
		stubFor(get(urlEqualTo("/ping"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json;charset=UTF-8")
						.withBody("{\"content\":\"pong\"}")));

		when()
				.post("http://localhost:" + port + "/ping-remote")
        .then()
				.statusCode(200)
				.body("content", is("pong"));
	}

}
