# [6.7 QIDO-RS Request/Response](https://dicom.nema.org/medical/dicom/2017b/output/chtml/part18/sect_6.7.html#table_6.7.1-1)

- SearchForStudies
- SearchForSeries
- SearchForInstances

## 6.7.1 QIDO-RS - Search
### 6.7.1.1 Request

1. SearchForStudies

```
[GET] {+SERVICE}/studies{?query*,fuzzymatching,limit,offset}
```
query:
- {attributeID}={value}
- includefield={attributeID} | all

#### 6.7.1.1.1 {attributeID} encoding rules
>  {attributeID} = {dicomTag} = {dicomKeyword} = {dicomTag}.{attributeID} = {dicomKeyword}.{attributeID}

- Valid values for {attributeID}:
```
0020000D
StudyInstanceUID

00101002.00100020
00101002.PatientID
OtherPatientIDsSequence.00100020
OtherPatientIDsSequence.PatientID

00101002.00100024.00400032
OtherPatientIDsSequence.IssuerOfPatientIDQualifiersSequence.UniversalEntityID
```

- Valid QIDO-RS URLs:
```
http://dicomrs/studies​?PatientID=11235813

http://dicomrs/studies​?PatientID=11235813​&StudyDate=20130509

http://dicomrs/studies​?00100010=SMITH*​&00101002.00100020=11235813​&limit=25

http://dicomrs/studies​?00100010=SMITH*​&OtherPatientIDsSequence.00100020=11235813

http://dicomrs/studies​?PatientID=11235813​&includefield=00081048​&includefield=00081049​&includefield=00081060

http://dicomrs/studies​?PatientID=11235813​&StudyDate=20130509-20130510

http://dicomrs/studies​?StudyInstanceUID=1.2.392.200036.9116.2.2.2.2162893313.1029997326.94587​%2c1.2.392.200036.9116.2.2.2.2162893313.1029997326.94583
```

#### Demo:

- Call API
```
http://localhost:8080/dcm4chee-arc/aets/DCM4CHEE/rs/studies?limit=1&offset=1&fuzzymatching=true&includefield=all
```

- Response
```
[
   {
      "00080020":{
         "vr":"DA",
         "Value":[
            "20210608"
         ]
      },
      "00080030":{
         "vr":"TM",
         "Value":[
            "134558.517"
         ]
      },
      "00080050":{
         "vr":"SH"
      },
      "00080054":{
         "vr":"AE",
         "Value":[
            "DCM4CHEE"
         ]
      },
      "00080056":{
         "vr":"CS",
         "Value":[
            "ONLINE"
         ]
      },
      "00080061":{
         "vr":"CS",
         "Value":[
            "MR"
         ]
      },
      "00080062":{
         "vr":"UI",
         "Value":[
            "1.2.840.10008.5.1.4.1.1.4"
         ]
      },
      "00080090":{
         "vr":"PN"
      },
      "00081190":{
         "vr":"UR",
         "Value":[
            "http://localhost:8080/dcm4chee-arc/aets/DCM4CHEE/rs/studies/1.2.392.200036.9123.100.12.12.16350.90210608134551408750347097"
         ]
      },
      "00100010":{
         "vr":"PN",
         "Value":[
            {
               "Alphabetic":"NGHIEM THI MINH ANH 1987"
            }
         ]
      },
      "00100020":{
         "vr":"LO",
         "Value":[
            "20210608-31"
         ]
      },
      "00100030":{
         "vr":"DA",
         "Value":[
            "20210608"
         ]
      },
      "00100032":{
         "vr":"TM",
         "Value":[
            "000000"
         ]
      },
      "00100040":{
         "vr":"CS",
         "Value":[
            "F"
         ]
      },
      "00101010":{
         "vr":"AS",
         "Value":[
            "000D"
         ]
      },
      "00101030":{
         "vr":"DS",
         "Value":[
            "60"
         ]
      },
      "00101060":{
         "vr":"PN"
      },
      "0020000D":{
         "vr":"UI",
         "Value":[
            "1.2.392.200036.9123.100.12.12.16350.90210608134551408750347097"
         ]
      },
      "00200010":{
         "vr":"SH",
         "Value":[
            "20210608-31"
         ]
      },
      "00201200":{
         "vr":"IS",
         "Value":[
            "1"
         ]
      },
      "00201206":{
         "vr":"IS",
         "Value":[
            "1"
         ]
      },
      "00201208":{
         "vr":"IS",
         "Value":[
            "1"
         ]
      },
      "77770010":{
         "vr":"LO",
         "Value":[
            "DCM4CHEE Archive 5"
         ]
      },
      "77771010":{
         "vr":"DT",
         "Value":[
            "20211029091525.667+0000"
         ]
      },
      "77771011":{
         "vr":"DT",
         "Value":[
            "20211029091525.716+0000"
         ]
      },
      "77771020":{
         "vr":"DT",
         "Value":[
            "20211029091525.685+0000"
         ]
      },
      "77771021":{
         "vr":"DT",
         "Value":[
            "20211029091525.685+0000"
         ]
      },
      "77771022":{
         "vr":"DT",
         "Value":[
            "20211029091525.685+0000"
         ]
      },
      "77771024":{
         "vr":"CS",
         "Value":[
            "NONE"
         ]
      },
      "77771025":{
         "vr":"CS",
         "Value":[
            "COMPLETE"
         ]
      },
      "77771028":{
         "vr":"LO",
         "Value":[
            "fs1"
         ]
      },
      "77771029":{
         "vr":"UL",
         "Value":[
            65
         ]
      },
      "7777102A":{
         "vr":"US",
         "Value":[
            774
         ]
      },
      "7777102B":{
         "vr":"CS",
         "Value":[
            "UPDATEABLE"
         ]
      }
   }
]
```

- Using [DICOM data elements](https://dicom.nema.org/medical/dicom/2017b/output/chtml/part06/chapter_6.html) 
to map **DicomTag** to **DicomName**

    - 00081190 => 0008,1190 => "Retrieve URL"



