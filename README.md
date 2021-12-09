# DICOM demo

[Dicom sources](https://www.dicomlibrary.com/)

## I. PREREQUIRE
1.  Dcm4chee archive is running

## II. UPLOAD DICOM FILE TO DCM4CHEE ARCHIVE 5

### In postman

1.  In headers, set <b>Content-type</b> to <b>multipart/form-data</b>
2.  In body, click form-data and upload DICOM file
![DICOM](./_docs_/requestBody.png)

### Result
![RESULT](./_docs_/result.png)

## III. VIEW
1.  [Embedded Viewer](https://docs.ohif.org/deployment/recipes/embedded-viewer.html)

## IV. DOCS
- [X] [setup dcm4chee-arc](https://github.com/dcm4che-dockerfiles/dcm4chee-arc-psql) 
- [X] [API](https://petstore.swagger.io/index.html?url=https://raw.githubusercontent.com/dcm4che/dcm4chee-arc-light/master/dcm4chee-arc-ui2/src/swagger/openapi.json#/QIDO-RS/SearchForStudies)
- [X] [VR - value representation](https://dicom.nema.org/dicom/2013/output/chtml/part05/sect_6.2.html)
- [X] [dicom attribute, tag, keyword](https://dicom.nema.org/medical/dicom/2017b/output/chtml/part06/chapter_6.html)
- [X] [DICOM standard browser](https://dicom.innolitics.com/ciods/rt-dose/patient/00101002)
- [X] [dicom wiki](https://github.com/dcm4che/dcm4chee-arc-light/wiki)
- [X] [dcm4chee-arc-cs.readthedocs.io](https://dcm4chee-arc-cs.readthedocs.io/en/latest/networking/specs/qido-rs/qido-rs.html)
- [X] [weasis-dicom-tools](https://github.com/nroduit/weasis-dicom-tools)
- [X] [weasis github](https://github.com/nroduit/Weasis) 

### [QIDO-rs search](https://dicom.nema.org/medical/dicom/2017b/output/chtml/part18/sect_6.7.html)
- [X] [dicom attribute, tag, keyword](https://dicom.nema.org/medical/dicom/2017b/output/chtml/part06/chapter_6.html)
- [X] [DICOM standard browser](https://dicom.innolitics.com/ciods/rt-dose/patient/00101002)

#### 1. {attributeID} encoding rules

> {attributeID} = {dicomTag} = {dicomKeyword} = {dicomTag}.{attributeID} = {dicomKeyword}.{attributeID}

{attributeID} can be one of the following:

- {dicomTag}

- {dicomKeyword}

- {dicomTag}.{attributeID}, where {attributeID} is an element of the sequence specified by {dicomTag}

- {dicomKeyword}.{attributeID}, where {attributeID} is an element of the sequence specified by {dicomKeyword}

{dicomTag} is the eight character hexadecimal string corresponding to the Tag of a DICOM Attribute (see [Chapter 6 in PS3.6](https://dicom.nema.org/medical/dicom/2017b/output/chtml/part06/chapter_6.html) ).

{dicomKeyword} is the Keyword of a DICOM Attribute (see [Chapter 6 in PS3.6](https://dicom.nema.org/medical/dicom/2017b/output/chtml/part06/chapter_6.html) ).

#### 2. Examples of valid values for {attributeID} (note: all equal):

```
##
0020000D

StudyInstanceUID


##
00101002.00100020

OtherPatientIDsSequence.00100020

OtherPatientIDsSequence.PatientID


##
00101002.00100024.00400032

OtherPatientIDsSequence.IssuerOfPatientIDQualifiersSequence.UniversalEntityID
```


#### 3. Examples of valid QIDO-RS URLs:

```
http://dicomrs/studies​?PatientID=11235813

http://dicomrs/studies​?PatientID=11235813​&StudyDate=20130509

http://dicomrs/studies​?00100010=SMITH*​&00101002.00100020=11235813​&limit=25

http://dicomrs/studies​?00100010=SMITH*​&OtherPatientIDsSequence.00100020=11235813

http://dicomrs/studies​?PatientID=11235813​&includefield=00081048​&includefield=00081049​&includefield=00081060

http://dicomrs/studies​?PatientID=11235813​&StudyDate=20130509-20130510

http://dicomrs/studies​?StudyInstanceUID=1.2.392.200036.9116.2.2.2.2162893313.1029997326.94587​%2c1.2.392.200036.9116.2.2.2.2162893313.1029997326.94583
```