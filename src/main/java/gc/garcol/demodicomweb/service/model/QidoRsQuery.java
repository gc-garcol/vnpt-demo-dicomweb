package gc.garcol.demodicomweb.service.model;

import gc.garcol.demodicomweb.service.annotation.IngnoreParseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * @author garcol
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QidoRsQuery {

    @IngnoreParseQuery
    private String aet;

    @IngnoreParseQuery
    private String studyUID;

    @IngnoreParseQuery
    private String seriesUID;

    private String accept;

    // {attributeID}={value}; {attributeID} = {dicomTag} | {dicomKeyword} | {dicomTag}.{attributeID} | {dicomKeyword}.{attributeID}
    private HashMap<String, String> filter;

    // includefield={attributeID} | all
    private String[] includefield;

    private Boolean includedefaults;

    // orderby=[-]{attributeID}
    private String[] orderby;

    private Boolean withoutstudies;

    private String patientVerificationStatus;

    private Boolean fuzzymatching;

    private Integer offset;

    private Integer limit;

    private Boolean returnempty;

    private Boolean incomplete;

    private Boolean retrievefailed;

    private Boolean storageVerificationFailed;

    private Boolean compressionfailed;

    private Boolean metadataUpdateFailed;

    private String SendingApplicationEntityTitleOfSeries;

    private String StudyReceiveDateTime;

    private String StudyAccessDateTime;

    private String StudySizeInKB;

    private String ExternalRetrieveAET;

    private String ExpirationDate;

    private Boolean allOfModalitiesInStudy;

    private String storageID;

    private Boolean storageClustered;

    private Boolean storageExported;

    private String ExpirationState;

}
