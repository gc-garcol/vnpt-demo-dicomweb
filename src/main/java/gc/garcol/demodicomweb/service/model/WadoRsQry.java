package gc.garcol.demodicomweb.service.model;

import gc.garcol.demodicomweb.service.annotation.IngnoreParseQuery;

public class WadoRsQry {

    @IngnoreParseQuery
    private String aet;

    @IngnoreParseQuery
    private String studyInstanceUID;

    private String seriesUID;

    private String sopUID;

    private String frameList;

    private String attributePath;

    private String accept;

    private String includefields;

    public String getAet() {
        return aet;
    }

    public WadoRsQry setAet(String aet) {
        this.aet = aet;
        return this;
    }

    public String getStudyInstanceUID() {
        return studyInstanceUID;
    }

    public WadoRsQry setStudyInstanceUID(String studyInstanceUID) {
        this.studyInstanceUID = studyInstanceUID;
        return this;
    }

    public String getSeriesUID() {
        return seriesUID;
    }

    public WadoRsQry setSeriesUID(String seriesUID) {
        this.seriesUID = seriesUID;
        return this;
    }

    public String getSopUID() {
        return sopUID;
    }

    public WadoRsQry setSopUID(String sopUID) {
        this.sopUID = sopUID;
        return this;
    }

    public String getFrameList() {
        return frameList;
    }

    public WadoRsQry setFrameList(String frameList) {
        this.frameList = frameList;
        return this;
    }

    public String getAttributePath() {
        return attributePath;
    }

    public WadoRsQry setAttributePath(String attributePath) {
        this.attributePath = attributePath;
        return this;
    }

    public String getAccept() {
        return accept;
    }

    public WadoRsQry setAccept(String accept) {
        this.accept = accept;
        return this;
    }

    public String getIncludefields() {
        return includefields;
    }

    public WadoRsQry setIncludefields(String includefields) {
        this.includefields = includefields;
        return this;
    }

    @Override
    public String toString() {
        return "WadoRsQry{" +
            "aet='" + aet + '\'' +
            ", studyUID='" + studyInstanceUID + '\'' +
            ", seriesUID='" + seriesUID + '\'' +
            ", sopUID='" + sopUID + '\'' +
            ", frameList='" + frameList + '\'' +
            ", attributePath='" + attributePath + '\'' +
            ", accept='" + accept + '\'' +
            ", includefields='" + includefields + '\'' +
            '}';
    }
}
