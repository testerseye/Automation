package excelcode;

import java.io.FileNotFoundException;

public class MainMethod {


    public static void main(String[] args) throws FileNotFoundException {
        String projDir = System.getProperty("user.dir");
      //  ExcelReader read = new ExcelReader("src\\main\\resources\\testdata1.xlsx","Sheet1");
       // read.printDataForLoop();
       //read.printDataIterator();
      //  ExcelWriter write = new ExcelWriter("testdata2.xlsx","testwrite");
        ExcelWriterNew writenew = new ExcelWriterNew("src/main/resources/testdata2_write.xlsx","Sheet1");




    }
}