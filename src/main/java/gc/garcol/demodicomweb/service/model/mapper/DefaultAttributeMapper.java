package gc.garcol.demodicomweb.service.model.mapper;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.ElementDictionary;
import org.dcm4che3.util.TagUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author garcol
 */
@Component
public class DefaultAttributeMapper implements AttributeMapper {

    public Map<String, Object> toDTO(Attributes attributes) {

        Map<String, Object> dto = new HashMap<>();
        IntStream.range(0, attributes.size())
        .forEach(i -> {
            int tag = attributes.tags()[i];
            Object value = attributes.getValue(tag);
            String key = getKey(tag, attributes);
            dto.put(key, value);
        });
        return dto;
    }

    public String getKey(int tag, Attributes attributes) {
        int creatorTag = 0;
        String privateCreator = null;
        if (TagUtils.isPrivateTag(tag)) {
            int tmp = TagUtils.creatorTagOf(tag);
            if (creatorTag != tmp) {
                creatorTag = tmp;
                privateCreator = attributes.getString(creatorTag, null);
            }
        }
        return ElementDictionary.keywordOf(tag, privateCreator);
    }

}
