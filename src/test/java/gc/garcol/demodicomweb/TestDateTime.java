package gc.garcol.demodicomweb;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author garcol
 */
public class TestDateTime {

    @Test
    public void testDateTimeFormat() {
        String dicomDatePattern = "yyyyMMdd";
        DateTimeFormatter dicomFormatter = DateTimeFormatter.ofPattern(dicomDatePattern).withZone(ZoneId.systemDefault());
        Instant instant = Instant.ofEpochMilli(1_639_970_747L);
        System.out.println("-------");
        System.out.println(dicomFormatter.format(instant));
    }

    @SneakyThrows
    @Test
    public void testSimpleDateFormat() {
        Instant instant = new SimpleDateFormat("yyyyMMdd").parse("19900101").toInstant();
        System.out.println(instant);
    }

}
