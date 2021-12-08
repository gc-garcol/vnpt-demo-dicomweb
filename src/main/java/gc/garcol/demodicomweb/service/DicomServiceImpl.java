package gc.garcol.demodicomweb.service;

import com.fasterxml.jackson.databind.JsonNode;
import gc.garcol.demodicomweb.service.model.QidoRsQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author garcol
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DicomServiceImpl implements DicomService {

    private final QidoRsStudyService qidoRsStudyService;

    @Override
    public void uploadDicomFiles(MultipartFile file) {
        try {
            StowRsService.newInstance().uploadDicom(file.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonNode findAllStudies(QidoRsQuery qidoRsQuery) {
        return qidoRsStudyService.queryForStudy(qidoRsQuery);
    }

}
