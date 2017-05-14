
package test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;
public class WireMockTest {

	private static final int PORT = 8089;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(PORT));

	@Test
	public void callWireMock() throws Exception {

		stubFor(get(urlEqualTo("/hello"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json;charset=UTF-8")
						.withBody("{\"content\":\"Hello, World!\"}")));

		when()
				.get("http://localhost:8089/hello")
				.then()
				.statusCode(200)
				.body("content", is("Hello, World!"));
	}

}
