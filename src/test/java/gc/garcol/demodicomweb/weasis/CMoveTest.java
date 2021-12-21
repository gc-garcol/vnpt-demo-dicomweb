package gc.garcol.demodicomweb.weasis;

import org.dcm4che3.data.Tag;
import org.dcm4che3.net.QueryOption;
import org.dcm4che3.net.Status;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.weasis.dicom.op.CMove;
import org.weasis.dicom.param.*;

import java.util.EnumSet;

/**
 * ref: weasis-dicom-tools -> CMoveNetTest.java
 * @author garcol
 */
public class CMoveTest {

    @Test
    public void testCMove() {
        DicomProgress progress = new DicomProgress();
        progress.addProgressListener(
                progress1 -> {
                    System.out.println(
                            "Remaining operations:" + progress1.getNumberOfRemainingSuboperations());
                    // if (progress.getNumberOfRemainingSuboperations() == 100) {
                    // progress.cancel();
                    // }
                });

        /** The following parameters must be changed to get a successful test. */

        // Move study
        DicomParam[] params = {
                new DicomParam(
                        Tag.StudyInstanceUID, "1.3.12.2.1107.5.4.3.4975316777216.19951114.94101.16")
        };
//        DicomNode calling = new DicomNode("STORESCP", "localhost", 104);
        DicomNode calling = new DicomNode("DCM4CHEE", "localhost", 11112);
        DicomNode called = new DicomNode("DCM4CHEE", "localhost", 11112);
        AdvancedParams options = new AdvancedParams();
        options.setQueryOptions(
                EnumSet.of(QueryOption.RELATIONAL)); // Required for QueryRetrieveLevel other than study
        DicomState state = CMove.process(options, calling, called, "STORESCP", progress, params);

        // Should never happen
        assert state != null;

        System.out.println("DICOM Status:" + state.getStatus());
        System.out.println(state.getMessage());
        System.out.println(
                "NumberOfRemainingSuboperations:" + progress.getNumberOfRemainingSuboperations());
        System.out.println(
                "NumberOfCompletedSuboperations:" + progress.getNumberOfCompletedSuboperations());
        System.out.println("NumberOfFailedSuboperations:" + progress.getNumberOfFailedSuboperations());
        System.out.println(
                "NumberOfWarningSuboperations:" + progress.getNumberOfWarningSuboperations());

        // see org.dcm4che3.net.Status
        // See server log at http://dicomserver.co.uk/logs/
        MatcherAssert.assertThat(
                state.getMessage(), state.getStatus(), IsEqual.equalTo(Status.Success));
        // Assert.assertFalse("No DICOM RSP Object", state.getDicomRSP().isEmpty());
    }

}
