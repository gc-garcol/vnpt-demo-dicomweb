package gc.garcol.demodicomweb;

import gc.garcol.demodicomweb.service.WebUtil;
import gc.garcol.demodicomweb.service.model.QidoRsQuery;
import org.dcm4che3.data.Keyword;
import org.dcm4che3.data.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author garcol
 */
public class TestWebUtil {

    @Test
    public void testWebUtil() {

        QidoRsQuery qidoRsQuery = QidoRsQuery.builder()
                .accept("mei")
                .aet("DCM4CHE-ARC")
                .allOfModalitiesInStudy(true)
                .filter(new HashMap<String, String>() {
                    {
                        put(Keyword.valueOf(Tag.StudyInstanceUID), "123123123");
                        put(Keyword.valueOf(Tag.PatientID), "12312123");
                    }
                })
                .ExpirationDate("123231")
                .includefield(new String[] {"i1", "i2"})
                .build();

        String queries = WebUtil.INSTANCE.toQuery(qidoRsQuery);
        System.out.println(queries);
    }

}
