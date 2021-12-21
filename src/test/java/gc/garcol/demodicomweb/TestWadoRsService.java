package gc.garcol.demodicomweb;

import gc.garcol.demodicomweb.service.StowRsService;
import gc.garcol.demodicomweb.service.WadoRsService;
import gc.garcol.demodicomweb.service.model.WadoRsQry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.function.Consumer;

/**
 * @author garcol
 */
@Slf4j
public class TestWadoRsService {

    @Test
    public void testRetrieveStudies() {

        RestTemplate restTemplate = new RestTemplate();
        WadoRsService wadoRsService = new WadoRsService(restTemplate);

        String url = "http://127.0.0.1:8080/dcm4chee-arc";
        String studyInstanceUID = "1.3.12.2.1107.5.4.3.4975316777216.19951114.94101.16";
        WadoRsQry wadoRsQry = new WadoRsQry()
                            .setAet("DCM4CHEE")
                            .setStudyInstanceUID(studyInstanceUID)
                ;

        Consumer<InputStream> callback = (inputStream) -> {
            System.out.println("=------------- on callback");
        };
        wadoRsService.retrieveStudy(url, wadoRsQry, callback);

    }

    @Test
    public void testRetrieveStudiesV2() {
        RestTemplate restTemplate = new RestTemplate();
        WadoRsService wadoRsService = new WadoRsService(restTemplate);

        String url = "http://127.0.0.1:8080/dcm4chee-arc";
        String studyInstanceUID = "1.3.12.2.1107.5.4.3.4975316777216.19951114.94101.16";
        WadoRsQry wadoRsQry = new WadoRsQry()
                .setAet("DCM4CHEE")
                .setStudyInstanceUID(studyInstanceUID)
                ;

        Consumer<InputStream> callback = (inputStream) -> {
            try {
                System.out.println("=------------- on callback");
                StowRsService.newInstance().uploadDicom(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        wadoRsService.retrieveStudy(url, wadoRsQry, callback);
    }

}
