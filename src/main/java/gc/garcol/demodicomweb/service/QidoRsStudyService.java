package gc.garcol.demodicomweb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gc.garcol.demodicomweb.service.model.QidoRsQuery;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

/**
 * @author garcol
 */
@Slf4j
@Service
@AllArgsConstructor
public class QidoRsStudyService {

    private final static String URL =  "http://localhost:8080/dcm4chee-arc/aets/DCM4CHEE/rs/studies";
    private final static String DICOM_JSON = "application/dicom+json";
    private final static String DICOM_XML = "multipart/related;type=application/dicom+xml";

    private final RestTemplate restTemplate;

    public JsonNode queryForStudy(QidoRsQuery query) {
        String reqUrl = MessageFormat.format("{0}?{1}", URL, toQuery(query));
        return execute(reqUrl);
    }

    @SneakyThrows
    JsonNode execute(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(response.getBody());
    }

    String toQuery(QidoRsQuery qidoRsQuery) {
        return "00100030=20210608";
    }

}
