package gc.garcol.demodicomweb.service;

import com.fasterxml.jackson.databind.JsonNode;
import gc.garcol.demodicomweb.service.model.QidoRsQuery;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author garcol
 */
public interface DicomService {

    void uploadDicomFiles(MultipartFile file);

    JsonNode findAllStudies(QidoRsQuery qidoRsQuery);

}
