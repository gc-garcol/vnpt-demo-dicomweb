package gc.garcol.demodicomweb.weasis;

import com.fasterxml.jackson.databind.ObjectMapper;
import gc.garcol.demodicomweb.service.model.mapper.DefaultAttributeMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.junit.jupiter.api.Test;
import org.weasis.dicom.op.CFind;
import org.weasis.dicom.param.DicomNode;
import org.weasis.dicom.param.DicomParam;
import org.weasis.dicom.param.DicomState;

import java.util.List;

/**
 * Refs: weasis-dicom-tools: org/weasis/dicom/CFindNetTest.java
 * @author garcol
 */
@Slf4j
public class CFindTest {

    @Test
    public void originalTest() {
        DicomParam[] params = {
                new DicomParam(Tag.PatientID, "PAT001"),
                new DicomParam(Tag.StudyInstanceUID),
                new DicomParam(Tag.NumberOfStudyRelatedSeries)
        };
        DicomNode calling = new DicomNode("WEASIS-SCU");

        log.info("calling dicomNode {}", calling.toString());

        DicomNode called = new DicomNode("DICOMSERVER", "dicomserver.co.uk", 11112);
        DicomState state = CFind.process(calling, called, params);
        // Should never happen
        assert state != null;
    }

    // todo note: ok
    @Test
    @SneakyThrows
    public void testProcess() {

        DicomParam[] params = {
//                new DicomParam(Tag.StudyInstanceUID, "1.3.12.2.1107.5.4.3.4975316777216.19951114.94101.16")
                new DicomParam(Tag.ModalitiesInStudy),
                CFind.PatientID,
                CFind.IssuerOfPatientID,
                CFind.PatientName,
                CFind.PatientBirthDate,
                CFind.PatientSex,
                CFind.StudyInstanceUID,
                CFind.AccessionNumber,
                CFind.IssuerOfAccessionNumberSequence,
                CFind.StudyID,
                CFind.ReferringPhysicianName,
                CFind.StudyDescription,
                CFind.StudyDate,
                CFind.StudyTime,
                CFind.StudyDescription,
                CFind.Modality,

        };
//        DicomNode calling = new DicomNode("AS_RECEIVED", "localhost", 8080);
//        DicomNode called = new DicomNode("DCM4CHEE", "localhost", 8080);

        DicomNode calling = new DicomNode("DCM4CHEE");
        DicomNode called = new DicomNode("AS_RECEIVED", "localhost", 11112);

//        DicomNode calling = new DicomNode("DCM4CHEE", "localhost", 11112);
//        DicomNode called = new DicomNode("AS_RECEIVED", "localhost", 11112);

        DicomState state = CFind.process(calling, called, params);

        log.info("==========");
        System.out.println(state.getStatus());
        System.out.println(state.getMessage());
        DefaultAttributeMapper mapper = new DefaultAttributeMapper();
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(mapper.toDTOs(state.getDicomRSP())));
        log.info("//==========");

        assert state != null;
    }

    @Test
    @SneakyThrows
    public void testCFind_02() {
        DicomParam[] params = {
//                new DicomParam(Tag.StudyInstanceUID, "1.3.12.2.1107.5.4.3.4975316777216.19951114.94101.16")
        };
//        DicomNode calling = new DicomNode("DCM4CHEE", "localhost", 11112);
//        DicomNode called = new DicomNode("AS_RECEIVED", "10.194.11.94", 11112);

        DicomNode called = new DicomNode("DCM4CHEE", "localhost", 11112);
        DicomNode calling = new DicomNode("AS_RECEIVED", "10.194.11.94", 11112);

        DicomState state = CFind.process(calling, called, params);

        log.info("==========");
//        System.out.println(state.getDicomRSP().get(0));
//        log.info("//==========");
    }

    @Test
    public void testCmoveFromStagingToDev() {
        DicomParam[] params = {
//                new DicomParam(Tag.StudyInstanceUID, "1.3.12.2.1107.5.4.3.4975316777216.19951114.94101.16")
        };
        DicomNode calling = new DicomNode("DCM4CHEE", "localhost", 11112);
//        DicomNode calling = new DicomNode("AS_RECEIVED", "10.194.11.94", 11112);
        // http://10.194.11.94:32080/dcm4chee-arc/ui2/#/device/aelist
        DicomNode called = new DicomNode("IMS_NODE1", "10.194.11.94", 11112);
//        DicomNode called = new DicomNode("DCM4CHEE", "localhost", 11112);
//        DicomNode calling = new DicomNode("IMS_NODE1", "10.194.11.94", 11112);

        DicomState state = CFind.process(calling, called, params);
        System.out.println(state);
    }

}
