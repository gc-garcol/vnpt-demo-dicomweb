package gc.garcol.demodicomweb;

import gc.garcol.demodicomweb.service.MyMultipartParser;
import gc.garcol.demodicomweb.service.StowRsService;
import gc.garcol.demodicomweb.service.WadoRsService;
import gc.garcol.demodicomweb.service.model.DicomByteArray;
import gc.garcol.demodicomweb.service.model.WadoRsQry;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.dcm4che3.mime.MultipartInputStream;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
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
//                StowRsService.newInstance().uploadDicom(inputStream);
                StowRsService.uploadSingleDicom(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        wadoRsService.retrieveStudy(url, wadoRsQry, callback);
    }

    @Test
    @SneakyThrows
    public void testRetrieveStudies_3() {
        RestTemplate restTemplate = new RestTemplate();
        WadoRsService wadoRsService = new WadoRsService(restTemplate);

        String url = "http://127.0.0.1:8080/dcm4chee-arc";
        String studyInstanceUID = "1.3.12.2.1107.5.4.3.4975316777216.19951114.94101.16";
        WadoRsQry wadoRsQry = new WadoRsQry()
                .setAet("DCM4CHEE")
                .setStudyInstanceUID(studyInstanceUID)
                ;
        InputStream inputStream = wadoRsService.retrieveStudy(url, wadoRsQry);

        try {
            System.out.println("=------------- on callback");
//            StowRsService.newInstance().uploadDicom(inputStream);
            StowRsService.uploadSingleDicom(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // todo note: fail
    @Test
    @SneakyThrows
    public void testRetrieveStudiesWithByteArray() {
        RestTemplate restTemplate = new RestTemplate();
        WadoRsService wadoRsService = new WadoRsService(restTemplate);

        String url = "http://127.0.0.1:8080/dcm4chee-arc";
        String studyInstanceUID = "1.3.12.2.1107.5.4.3.4975316777216.19951114.94101.16";
        WadoRsQry wadoRsQry = new WadoRsQry()
                .setAet("DCM4CHEE")
                .setStudyInstanceUID(studyInstanceUID)
                ;
        byte[] res = wadoRsService.retrieveStudyAsByteArray(url, wadoRsQry);
        InputStream myInputStream = new ByteArrayInputStream(res);

        StowRsService.uploadSingleDicom(myInputStream);
    }

    @Test
    @SneakyThrows
    public void testretrieveStudyAsDicomByteArray() {
        RestTemplate restTemplate = new RestTemplate();
        WadoRsService wadoRsService = new WadoRsService(restTemplate);

        String url = "http://127.0.0.1:8080/dcm4chee-arc";
        String studyInstanceUID = "1.3.12.2.1107.5.4.3.4975316777216.19951114.94101.16";
        WadoRsQry wadoRsQry = new WadoRsQry()
                .setAet("DCM4CHEE")
                .setStudyInstanceUID(studyInstanceUID)
                ;

        DicomByteArray res = wadoRsService.retrieveStudyAsDicomByteArray(url, wadoRsQry);

        assert res != null;

        InputStream inputStream = new ByteArrayInputStream(res.data);
        MultipartInputStream data = new MyMultipartParser(res.boundary).parse(new BufferedInputStream(inputStream));
        data.readHeaderParams();
        StowRsService.uploadSingleDicom(data);
    }

}
