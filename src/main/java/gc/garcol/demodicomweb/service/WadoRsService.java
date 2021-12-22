package gc.garcol.demodicomweb.service;

import gc.garcol.demodicomweb.service.model.WadoRsQry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcm4che3.mime.MultipartInputStream;
import org.dcm4che3.mime.MultipartParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.function.Consumer;

/**
 * @author garcol
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WadoRsService {

    private final RestTemplate restTemplate;

    public InputStream retrieveStudy(String url, WadoRsQry wadoRsQuery) {
        String aet = getAet(wadoRsQuery.getAet());
        String requestPath = String.format("/aets%s/rs/studies/%s?%s", aet, wadoRsQuery.getStudyInstanceUID(), toQuery(wadoRsQuery));

        RequestCallback requestCallback = request -> request.getHeaders().add("Accept", "*");
        ResponseExtractor<InputStream> responseExtractor = responseExtractor();
        return restTemplate.execute(url + requestPath, HttpMethod.GET, requestCallback, responseExtractor);
    }

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
                if (callback != null) callback.accept(multipartInputStream);
            });
            return null;
        };
    }

    private ResponseExtractor<InputStream> responseExtractor() {
        return response -> {
            HttpHeaders httpHeaders = response.getHeaders();
            String boundary = findValueInContentType(httpHeaders.getContentType().toString(), "boundary");
            InputStream byteArrayInputStream = toByteArrayInputStream(response.getBody());
            MultipartInputStream inputStream = new MyMultipartParser(boundary).parse(new BufferedInputStream(byteArrayInputStream));
            inputStream.readHeaderParams();
            return inputStream;
        };
    }

    private InputStream toByteArrayInputStream(InputStream in) {
        try {
            byte[] buff = new byte[in.available()];
            int bytesRead;
            ByteArrayOutputStream bao = new ByteArrayOutputStream();

            while((bytesRead = in.read(buff)) != -1) {
                bao.write(buff, 0, bytesRead);
            }

            byte[] data = bao.toByteArray();
            return new ByteArrayInputStream(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
