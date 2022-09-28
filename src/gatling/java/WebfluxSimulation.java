import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class WebfluxSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8082/");

    ScenarioBuilder scn = scenario("WebClient")
            .exec(http("request_1")
                    .get("/voucher-webclient/0"));

    {
        setUp( // 11
                scn.injectOpen(rampUsers(1000).during(60))
        ).protocols(httpProtocol);
    }
}