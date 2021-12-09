package gc.garcol.demodicomweb.service;

import gc.garcol.demodicomweb.configuration.TagDetailByTag;
import gc.garcol.demodicomweb.service.model.TagDetail;
import lombok.RequiredArgsConstructor;
import org.dcm4che3.util.TagUtils;
import org.springframework.stereotype.Service;

/**
 * @author garcol
 */
@Service
@RequiredArgsConstructor
public class TagService {

    private final TagDetailByTag tagDetailByTag;

    public TagDetail getTagDetailByTagValue(int tag) {
        return tagDetailByTag.getTagDetailByTag().get(tag);
    }

    public TagDetail getTagDetailByDicomTag(String dicomTag) {
        return tagDetailByTag.getTagDetailByTag().get(TagUtils.intFromHexString(dicomTag));
    }

}
