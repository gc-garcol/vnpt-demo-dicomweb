package gc.garcol.demodicomweb.service;

import org.dcm4che3.data.Attributes;
import org.weasis.dicom.param.DicomProgress;
import org.weasis.dicom.param.DicomState;
import org.weasis.dicom.web.AbstractStowrs;
import org.weasis.dicom.web.Multipart;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author garcol
 */
public class StowRsService extends AbstractStowrs {

    private final static Map<String, String> headers = new HashMap<>();

    // todo: need to read from application.yml
    private final static String STOWRS_URL =  "http://localhost:8080/dcm4chee-arc/aets/DCM4CHEE/rs/studies";

    static {
        headers.put("accept", "application/dicom+json");
        headers.put("Archive-Node-Aet", "DCM4CHEE");
    }

    /**
     *
     * @return new instance of StowRsService
     */
    public static StowRsService newInstance() {
        return new StowRsService();
    }

    /**
     *
     */
    private StowRsService() {
        super(STOWRS_URL, Multipart.ContentType.DICOM, "garcol", headers);
    }

    /**
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    public DicomState uploadDicom(InputStream inputStream) throws Exception {
        HttpURLConnection httpPost = buildConnection();
        DataOutputStream outputStream = new DataOutputStream(httpPost.getOutputStream());
        writeContentMarkers(outputStream);
        copy(inputStream, outputStream);
        Attributes error = writeEndMarkers(httpPost, outputStream);
        return new DicomState(new DicomProgress());
    }

    /**
     * See in Files: private static long copy(InputStream source, OutputStream sink)
     * @param source
     * @param sink
     * @return
     * @throws IOException
     */
    private  long copy(InputStream source, OutputStream sink)
            throws IOException
    {
        long nread = 0L;
        byte[] buf = new byte[8_192];
        int n;
        while ((n = source.read(buf)) > 0) {
            sink.write(buf, 0, n);
            nread += n;
        }
        return nread;
    }

}
