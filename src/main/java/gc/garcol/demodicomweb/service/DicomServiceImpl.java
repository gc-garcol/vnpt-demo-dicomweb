package gc.garcol.demodicomweb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author garcol
 */
@Service
@Slf4j
public class DicomServiceImpl implements DicomService {

    @Override
    public void uploadDicomFiles(MultipartFile file) {
        try {
            StowRsService.newInstance().uploadDicom(file.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
