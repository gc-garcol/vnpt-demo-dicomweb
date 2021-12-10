package gc.garcol.demodicomweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import gc.garcol.demodicomweb.configuration.DicomTagConfiguration;
import gc.garcol.demodicomweb.service.QidoRsStudyService;
import gc.garcol.demodicomweb.service.TagUtil;
import gc.garcol.demodicomweb.service.model.mapper.CustomAttributeMapper;
import gc.garcol.demodicomweb.service.model.mapper.DefaultAttributeMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.dcm4che3.data.Attributes;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

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
    public void testToDtoDefault() {
        DefaultAttributeMapper mapper = new DefaultAttributeMapper();

        RestTemplate restTemplate = new RestTemplate();
        QidoRsStudyService qidoRsStudyService = new QidoRsStudyService(restTemplate);
        List<Attributes> attributes = qidoRsStudyService.queryForStudy(null);

        List<Map<String, Object>> rs = mapper.toDTOs(attributes);

        ObjectMapper objectMapper = new ObjectMapper();
        log.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rs));
    }

    @Test
    @SneakyThrows
    public void testToDtoCustom() {
        DicomTagConfiguration tagConfiguration = new DicomTagConfiguration();
        TagUtil tagUtil = new TagUtil(tagConfiguration.initTagDetailByTag());
        CustomAttributeMapper mapper = new CustomAttributeMapper(tagUtil);

        RestTemplate restTemplate = new RestTemplate();
        QidoRsStudyService qidoRsStudyService = new QidoRsStudyService(restTemplate);
        List<Attributes> attributes = qidoRsStudyService.queryForStudy(null);

        List<Map<String, Object>> rs = mapper.toDTOs(attributes);

        ObjectMapper objectMapper = new ObjectMapper();
        log.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rs));
    }

}
