package gc.garcol.demodicomweb;

import gc.garcol.demodicomweb.configuration.DicomTagConfiguration;
import gc.garcol.demodicomweb.configuration.TagDetailByTag;
import gc.garcol.demodicomweb.service.TagUtil;
import lombok.extern.slf4j.Slf4j;
import org.dcm4che3.data.Tag;
import org.dcm4che3.util.TagUtils;
import org.junit.jupiter.api.Test;

/**
 * @author garcol
 */
@Slf4j
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

    @Test
    public void testFromHexString() {
        int studyInstanceUID = Tag.StudyInstanceUID;
        String hexStr = TagUtils.toHexString(studyInstanceUID);
        assert studyInstanceUID == TagUtils.intFromHexString(hexStr);
    }

    @Test
    public void testTagService() {
        TagDetailByTag tagDetailByTag = new DicomTagConfiguration().initTagDetailByTag();
        TagUtil tagUtil = new TagUtil(tagDetailByTag);

        log.info(tagUtil.getTagDetailByTagValue(Tag.StudyInstanceUID).toString());
        log.info("===");
        log.info(tagUtil.getTagDetailByDicomTag(TagUtils.toHexString(Tag.StudyInstanceUID)).toString());

    }

    @Test
    public void testFromHexString2() {
        TagDetailByTag tagDetailByTag = new DicomTagConfiguration().initTagDetailByTag();
        TagUtil tagUtil = new TagUtil(tagDetailByTag);
        System.out.println(tagUtil.getTagDetailByDicomTag("00100010"));

        System.out.println(tagUtil.getTagDetailByTagValue(Tag.ReferringPhysicianName));
    }

}
