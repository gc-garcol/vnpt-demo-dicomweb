package gc.garcol.demodicomweb.service;

import gc.garcol.demodicomweb.service.model.QidoRsQuery;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.json.JSONReader;
import org.dcm4che3.util.SafeClose;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import javax.json.Json;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QidoRsService {

    private final static String DICOM_JSON = "application/dicom+json";
    private final static String DICOM_XML = "multipart/related;type=application/dicom+xml";

    private final RestTemplate restTemplate;

    private WebUtil webUtil = WebUtil.INSTANCE;

    public List<Attributes> queryForStudy(String url, QidoRsQuery query) {
        String reqUrl = MessageFormat.format("{0}/aets/{1}?{2}", url, query.getAet(), webUtil.toQuery(query));
        return execute(reqUrl);
    }

    @SneakyThrows
    private List<Attributes> execute(String url) {
        RequestCallback requestCallback = request -> request.getHeaders().add("Accept", DICOM_JSON + ", " + DICOM_XML);
        ResponseExtractor<List<Attributes>> responseExtractor = response -> convertJson(response.getBody());
        return restTemplate.execute(url, HttpMethod.GET, requestCallback, responseExtractor);
    }

    @SuppressWarnings("Duplicates")
    private List<Attributes> convertJson(InputStream in) {
        try {
            JSONReader reader = new JSONReader(
                    Json.createParser(new InputStreamReader(in, StandardCharsets.UTF_8)));
            final List<Attributes> attrs = new ArrayList<>();
            reader.readDatasets((fmi, dataset) -> attrs.add(dataset));
            return attrs;
        } finally {
            SafeClose.close(in);
        }
    }

}
