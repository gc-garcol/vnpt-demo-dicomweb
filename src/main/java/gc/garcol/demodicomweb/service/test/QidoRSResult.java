package gc.garcol.demodicomweb.service.test;

import java.util.List;

import gc.garcol.demodicomweb.service.test.TestResult;
import org.dcm4che3.data.Attributes;

/**
 * @author Hesham elbadawi <bsdreko@gmail.com>
 */
public class QidoRSResult implements TestResult {

    private final String testDescription;
    private int expectedResult = Integer.MIN_VALUE;
    private final int numMatches;
    private final long time;
    private final List<Attributes> queryResponse;
    private final long timeFirst;

    public QidoRSResult(String testDescription, int expectedResult,
                        int numMatches, long time, long timeFirst, List<Attributes> dataList) {
        super();
        this.testDescription = testDescription;
        this.expectedResult = expectedResult;
        this.numMatches = numMatches;
        this.time = time;
        this.timeFirst = timeFirst;
        this.queryResponse = dataList;
    }

    public String getTestDescription() {
        return testDescription;
    }
    public int getExpectedResult() {
        return expectedResult;
    }
    public int getNumMatches() {
        return numMatches;
    }
    public long getTime() {
        return time;
    }
    public List<Attributes> getQueryResponse() {
        return queryResponse;
    }
    public long getTimeFirst() {
        return timeFirst;
    }
}

