package gc.garcol.demodicomweb;

import org.dcm4che3.data.Tag;
import org.dcm4che3.util.TagUtils;
import org.junit.jupiter.api.Test;

/**
 * @author garcol
 */
public class TestDicomCoreTagUtil {

    @Test
    public void testToString() {
        int studyInstanceUID = Tag.StudyInstanceUID;
        System.out.println(TagUtils.toString(studyInstanceUID));
    }

    @Test
    public void testToHexString() {
        int studyInstanceUID = Tag.StudyInstanceUID;
        System.out.println(TagUtils.toHexString(studyInstanceUID));
    }

}
