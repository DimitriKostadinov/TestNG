package DemoTestNG;

import org.testng.annotations.DataProvider;

public class DataProviderOnly {

    @DataProvider(name = "input-provider")
    public static Object[][] inputData(){
        Object[][] data = new Object[2][11];
        data[0][0] = "Dimitri";
        data[0][1] = "dimitri@gmail.com";
        data[0][2] = "password123!";
        data[0][3] = "Company 1";
        data[0][4] = "https://www.company1.org";
        data[0][5] = "Bulgaria";
        data[0][6] = "Sofia";
        data[0][7] = "Address 1";
        data[0][8] = "Address 2";
        data[0][9] = "Sofia state";
        data[0][10] = 1000;

        data[1][0] = "Jane";
        data[1][1] = "jane@gmail.com";
        data[1][2] = "password456!";
        data[1][3] = "Company 2";
        data[1][4] = "https://www.company2.com";
        data[1][5] = "Canada";
        data[1][6] = "Toronto";
        data[1][7] = "Address 1";
        data[1][8] = "Address 2";
        data[1][9] = "Toronto state";
        data[1][10] = 2000;

        return data;
    }
}
