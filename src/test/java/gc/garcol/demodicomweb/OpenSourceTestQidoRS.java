package gc.garcol.demodicomweb;

import gc.garcol.demodicomweb.service.test.QidoRS;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author garcol
 */
@Slf4j
public class OpenSourceTestQidoRS {

    @Test
    @SneakyThrows
    public void testQidoRS() {
        QidoRS main = new QidoRS();
        main.setRunningModeTest(true);
        main.setUrl("http://localhost:8080/dcm4chee-arc/aets/DCM4CHEE/rs/studies");
        String rs = QidoRS.qido(main, false);
        log.info(rs);
        log.info(main.getResponseAttrs().toString());
    }

}
