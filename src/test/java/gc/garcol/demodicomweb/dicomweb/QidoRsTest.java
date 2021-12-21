package gc.garcol.demodicomweb.dicomweb;

import gc.garcol.demodicomweb.service.model.QidoRsQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

/**
 * @author garcol
 */
@Slf4j
public class QidoRsTest {

//    private final static String URL =  "http://localhost:8080/dcm4chee-arc/aets/DCM4CHEE/rs/studies";

    private final static String URL =  "http://localhost:8080/dcm4chee-arc/aets/AS_RECEIVED/rs/studies";

    @Test
    public void testQido() {
        RestTemplate restTemplate = new RestTemplate();
        String url = MessageFormat.format("{0}?{1}", URL, toQuery(null));

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        log.info(response.getBody());
    }

    String toQuery(QidoRsQuery qidoRsQuery) {
        return "00100030=20210608";
    }

}
