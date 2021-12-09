package gc.garcol.demodicomweb.configuration;

import gc.garcol.demodicomweb.service.model.TagDetail;
import org.dcm4che3.data.Keyword;
import org.dcm4che3.data.Tag;
import org.dcm4che3.util.TagUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author garcol
 */
@Configuration
public class DicomTagConfiguration {

    @Bean
    public TagDetailByTag initTagDetailByTag() {

        Map<Integer,TagDetail> tagDetailMap = Arrays.stream(Tag.class.getDeclaredFields())
                .filter(field -> field.getType() == int.class)
                .map(this::fromField)
                .filter(tagDetail -> tagDetail.getKeywork().equals(Keyword.valueOf(tagDetail.getTag())))
                .collect(Collectors.toMap(TagDetail::getTag, Function.identity()));

        return new TagDetailByTag(tagDetailMap);
    }

    @Bean
    @Deprecated
    TagDetailByDicomTag initTagDetailByDicomTag() {
        Map<String,TagDetail> tagDetailMap = Arrays.stream(Tag.class.getDeclaredFields())
                .filter(field -> field.getType() == int.class)
                .map(this::fromField)
                .filter(tagDetail -> tagDetail.getKeywork().equals(Keyword.valueOf(tagDetail.getTag())))
                .collect(Collectors.toMap(TagDetail::getDicomTag, Function.identity()));

        return new TagDetailByDicomTag(tagDetailMap);
    }

    private TagDetail fromField(Field field) {
        try {
            int tag = field.getInt(null);
            String dicomTag = TagUtils.toHexString(tag);
            String keyword = field.getName();
            return new TagDetail(tag, keyword, dicomTag);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
