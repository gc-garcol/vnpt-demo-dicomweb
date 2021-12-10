package gc.garcol.demodicomweb.service;

import gc.garcol.demodicomweb.service.model.QidoRsQuery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author garcol
 */
public interface DicomService {

    void uploadDicomFiles(MultipartFile file);

    List<Map<String, String>> findAllStudies(QidoRsQuery qidoRsQuery);

}
