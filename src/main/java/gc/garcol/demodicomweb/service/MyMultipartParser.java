package gc.garcol.demodicomweb.service;

import org.dcm4che3.mime.MultipartInputStream;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;

/**
 * ref: org/dcm4che3/mime/MultipartParser.java
 */
public class MyMultipartParser {

    private final String boundary;

    public MyMultipartParser(String boundary) {
        this.boundary = boundary;
    }

    MultipartInputStream newMultipartInputStreamInstance(InputStream in, String boundary) throws Exception {
        Constructor<MultipartInputStream> constructor = MultipartInputStream.class.getDeclaredConstructor(InputStream.class, String.class);
        constructor.setAccessible(true);
        return constructor.newInstance(in, boundary);
    }

    @SuppressWarnings("resource")
    public MultipartInputStream parse(InputStream in) {
        try {
            newMultipartInputStreamInstance(in, "--" + boundary).skipAll(); // skip preamble
            int ch1 = in.read();
            int ch2 = in.read();
            if ((ch1 | ch2) < 0)
                throw new EOFException();

            if (ch1 == '-' && ch2 == '-')
                throw new RuntimeException("parse to null result");

            if (ch1 != '\r' || ch2 != '\n')
                throw new IOException("missing CR/LF after boundary");

            return newMultipartInputStreamInstance(in, "\r\n--" + boundary);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
