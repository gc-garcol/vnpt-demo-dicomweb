package gc.garcol.demodicomweb.service;

import gc.garcol.demodicomweb.service.model.QidoRsQuery;
import gc.garcol.demodicomweb.service.model.mapper.AttributeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcm4che3.data.Attributes;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author garcol
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DicomServiceImpl implements DicomService {

    private final QidoRsStudyService qidoRsStudyService;

    private final AttributeMapper attributeMapper;

    @Override
    public void uploadDicomFiles(MultipartFile file) {
        try {
            StowRsService.newInstance().uploadDicom(file.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Map<String, Object>> findAllStudies(QidoRsQuery qidoRsQuery) {
        List<Attributes> attributes = qidoRsStudyService.queryForStudy(qidoRsQuery);
        return attributeMapper.toDTOs(attributes);
    }

}
