package DemoTestNG;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import java.util.Arrays;

public class DataProviderExcelTests {

    @Test(dataProviderClass = DataProviderExcel.class, dataProvider = "get-data-excel")
    public void testPrintData(Object[] data) {

        Object cellValue = null;
        for (Object dataArr : data) {
            cellValue = Arrays.toString(new Object[]{dataArr});

            if(cellValue.toString().toLowerCase().contains("null")){
                throw new SkipException("Skipping this exception"); // Skip the tests that contains string "null"
            }

            System.out.print(cellValue);
        }

        Assert.assertTrue((cellValue.toString().toLowerCase()).contains("true"), "Is not true!");
        System.out.println();
    }
}
