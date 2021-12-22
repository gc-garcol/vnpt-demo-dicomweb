package gc.garcol.demodicomweb.service.model;

/**
 * @author garcol
 */
public class DicomByteArray {

    public String boundary;

    public byte[] data;

    public DicomByteArray() {
    }

    public DicomByteArray(String boundary, byte[] data) {
        this.boundary = boundary;
        this.data = data;
    }
}
