package gc.garcol.demodicomweb.endpoints;

import gc.garcol.demodicomweb.service.DicomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author garcol
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DicomController {

    private final DicomService dicomService;

    @RequestMapping(method = RequestMethod.POST, path = "/dicom")
    public ResponseEntity<String> uploadDicom(@RequestPart(value = "file") MultipartFile file) {
        dicomService.uploadDicomFiles(file);

        return ResponseEntity.ok("uploaded");
    }

}
