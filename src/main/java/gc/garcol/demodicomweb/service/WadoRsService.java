package gc.garcol.demodicomweb.service;

import gc.garcol.demodicomweb.service.model.WadoRsQry;
import lombok.RequiredArgsConstructor;
import org.dcm4che3.mime.MultipartParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.function.Consumer;

/**
 * @author garcol
 */
@Service
@RequiredArgsConstructor
public class WadoRsService {

    private final RestTemplate restTemplate;

    public void retrieveStudy(String url, WadoRsQry wadoRsQuery, Consumer<InputStream> callback) {
        String aet = getAet(wadoRsQuery.getAet());
        String requestPath = String.format("/aets%s/rs/studies/%s?%s", aet, wadoRsQuery.getStudyInstanceUID(), toQuery(wadoRsQuery));

        RequestCallback requestCallback = request -> request.getHeaders().add("Accept", "*");
        ResponseExtractor<Void> responseExtractor = getResponseExtractor(callback);
        restTemplate.execute(url + requestPath, HttpMethod.GET, requestCallback, responseExtractor);
    }

    private String toQuery(Object o) {
        return WebUtil.INSTANCE.toQuery(o);
    }

    private String getAet(String aet) {
        return (aet != null && !"".equals(aet)) ? "/" + aet : "";
    }

    private ResponseExtractor<Void> getResponseExtractor(Consumer<InputStream> callback) {
        return response -> {
            HttpHeaders httpHeaders = response.getHeaders();
            String boundary = findValueInContentType(httpHeaders.getContentType().toString(), "boundary");
            new MultipartParser(boundary).parse(new BufferedInputStream(response.getBody()), (partNumber, multipartInputStream) -> {
                multipartInputStream.readHeaderParams();
                callback.accept(multipartInputStream);
            });
            return null;
        };
    }

    private String findValueInContentType(String contentType, String key) {
        String[] strings = contentType.split(";");
        for (String s : strings) {
            if (s.contains(key)) {
                return getValueHeaderContentType(s);
            }
        }
        return "";
    }

    private String getValueHeaderContentType(String contentType) {
        return contentType.substring(contentType.indexOf('=') + 1).replaceAll("\"", "");
    }

}
