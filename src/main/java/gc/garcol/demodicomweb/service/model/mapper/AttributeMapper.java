package gc.garcol.demodicomweb.service.model.mapper;

import org.dcm4che3.data.Attributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author garcol
 */
public interface AttributeMapper {

    Map<String, Object> toDTO(Attributes attributes);

    default List<Map<String, Object>> toDTOs(List<Attributes> attributes) {
        return attributes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
