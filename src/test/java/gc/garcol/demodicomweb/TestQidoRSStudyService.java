package gc.garcol.demodicomweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import gc.garcol.demodicomweb.service.QidoRsStudyService;
import gc.garcol.demodicomweb.service.model.mapper.AttributeMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.dcm4che3.data.Attributes;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author garcol
 */
@Slf4j
public class TestQidoRSStudyService {

    @Test
    public void test() {
        RestTemplate restTemplate = new RestTemplate();
        QidoRsStudyService qidoRsStudyService = new QidoRsStudyService(restTemplate);
        List<Attributes> attributes = qidoRsStudyService.queryForStudy(null);
        String rs = attributes.get(0).toString((int)1E9, (int)1E9);
        log.info(rs);
    }

    @Test
    @SneakyThrows
    public void testToDto() {
        AttributeMapper mapper = new AttributeMapper();

        RestTemplate restTemplate = new RestTemplate();
        QidoRsStudyService qidoRsStudyService = new QidoRsStudyService(restTemplate);
        List<Attributes> attributes = qidoRsStudyService.queryForStudy(null);

        List<Map<String, Object>> rs = attributes.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());


        ObjectMapper objectMapper = new ObjectMapper();
        log.info(objectMapper.writeValueAsString(rs));
    }

}
