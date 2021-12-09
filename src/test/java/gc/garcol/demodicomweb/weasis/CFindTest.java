package gc.garcol.demodicomweb.weasis;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.junit.jupiter.api.Test;
import org.weasis.dicom.op.CFind;
import org.weasis.dicom.param.DicomNode;
import org.weasis.dicom.param.DicomParam;
import org.weasis.dicom.param.DicomState;

import java.util.List;

/**
 * todo: [NOTE] not working, need to research calling DicomNode
 * Refs: weasis-dicom-tools: org/weasis/dicom/CFindNetTest.java
 * @author garcol
 */
public class CFindTest {

    @Test
    public void testProcess() {

        DicomParam[] params = {
                new DicomParam(Tag.PatientID, "PAT001"),
                new DicomParam(Tag.StudyInstanceUID),
                new DicomParam(Tag.NumberOfStudyRelatedSeries)
        };
        DicomNode calling = new DicomNode("DCM4CHEE");
        DicomNode called = new DicomNode("DCM4CHEE", "localhost", 8080);
        DicomState state = CFind.process(calling, called, params);

        assert state != null;

        List<Attributes> items = state.getDicomRSP();
        for (int i = 0; i < items.size(); i++) {
            Attributes item = items.get(i);
            System.out.println("===========================================");
            System.out.println("CFind Item " + (i + 1));
            System.out.println("===========================================");
            System.out.println(item.toString(100, 150));
        }

        System.out.println("DICOM Status:" + state.getStatus());
        System.out.println(state.getMessage());
    }

}
