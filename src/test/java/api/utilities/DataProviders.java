package api.utilities;


import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
    @DataProvider(name = "Data")
    public Object[][] getAllData() throws IOException{
        String path=System.getProperty("user.dir")+"//testdata//RAData.xlsx";
        System.out.println("DataProviders inside===="+path);
        XLUtility xl=new XLUtility(path);
        int rownum=xl.getRowCount("Sheet1");
        int colcount=xl.getCellCount("Sheet1",1);
        String apidata[][]=new String[rownum][colcount];
        System.out.println("api data====="+apidata);
        for(int i=1; i<=rownum;i++  ){
            for(int j=0;j<colcount;j++){
apidata[i-1][j]=xl.getCellData("Sheet1",i,j);
            }
        }
        return apidata;
    }
   @DataProvider(name="UserNames")
    public String[] getUserNames() throws IOException{
       String path=System.getProperty("user.dir")+"//testdata//RAData.xlsx";
        XLUtility xl=new XLUtility(path);
     int rownum=xl.getRowCount("Sheet1");
     String apidata[]=new String[rownum];
     for(int i=1;i<=rownum;i++){
         apidata[i-1]=xl.getCellData("Sheet1",i,1);

     }
        return apidata;

    }


}
