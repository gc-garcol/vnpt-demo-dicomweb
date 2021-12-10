package gc.garcol.demodicomweb.service.model.mapper;

import gc.garcol.demodicomweb.service.TagUtil;
import lombok.RequiredArgsConstructor;
import org.dcm4che3.data.Attributes;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author garcol
 */
@Component
@RequiredArgsConstructor
public class CustomAttributeMapper implements AttributeMapper {

    private final TagUtil tagUtil;

    @Override
    public Map<String, Object> toDTO(Attributes attributes) {
        Map<String, Object> dto = new HashMap<>();
        IntStream.range(0, attributes.size())
                .forEach(i -> {
                    int tag = attributes.tags()[i];
                    Object value = attributes.getValue(tag);
                    String key = tagUtil.getTagDetailByTagValue(tag).getKeywork();
                    dto.put(key, value);
                });
        return dto;
    }

}
