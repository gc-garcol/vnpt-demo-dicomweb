package gc.garcol.demodicomweb.configuration;

import gc.garcol.demodicomweb.service.model.TagDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * @author garcol
 */
@Getter
@AllArgsConstructor
public class TagDetailByTag {

    private Map<Integer, TagDetail> tagDetailByTag;

}
