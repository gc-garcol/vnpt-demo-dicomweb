package gc.garcol.demodicomweb.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author garcol
 */
@Data
@AllArgsConstructor
public class TagDetail {

    private int tag;

    @EqualsAndHashCode.Exclude
    private String keywork;

    @EqualsAndHashCode.Exclude
    private String dicomTag;

}
