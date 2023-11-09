package excelcode;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

    public class ExcelReader{
        String excelFilePath; // specifies the file location.
        FileInputStream input;
        Workbook workbook;
        Sheet newSheet;
        // Reading excel file by sheetname.
        public ExcelReader(String excelFilePath,String sheetName){
            this.excelFilePath = excelFilePath;
            try{
                input = new FileInputStream(new File(excelFilePath));  //refers the filepath and helps to read the file.
                workbook=new XSSFWorkbook(input);  // getting workbook from the file.
                int index = workbook.getSheetIndex(sheetName); // getting the sheet from the workbook by the name of the sheet.
                if (index==-1)
                    System.out.println("sheetname"+" "+sheetName+" "+"not found in workbook");
                newSheet = workbook.getSheetAt(index);

            } catch (IOException e) {
                System.out.println("unable to create excel object");
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        //Reading Excel file by sheet number. By default sheet number starts with 0.
        public ExcelReader(String excelFilePath,int sheetNum) throws IOException {
            this.excelFilePath = excelFilePath;
            input = new FileInputStream(new File(excelFilePath));
            workbook = new XSSFWorkbook(input);
            newSheet = workbook.getSheetAt(sheetNum); //getting the sheet from the workbook by the index of the sheet.
        }
        //////Printing data using For loop using rows and columns./////
        public void printDataForLoop(){
            int rows = newSheet.getLastRowNum(); //gives the number of rows present in the sheet.

            System.out.println("Num of Rows are"+rows);

            for(int r=0;r<=rows;r++){               // reads each row from the beginning of the sheet.
                Row row = newSheet.getRow(r);
                int cols = newSheet.getRow(r).getLastCellNum();// gives number of cells in a row i.e, number of columns.
                System.out.println("Num of Columns in row "+r+" are "+cols);
                for(int c=0;c<cols;c++){          // reads each cell of the corresponding row in the outerloop.
                    Cell cell = row.getCell(c);
                    switch(cell.getCellType()) {   // returns the type of the cell.
                        case STRING: System.out.println(cell.getStringCellValue());
                            break;
                        case NUMERIC: System.out.println(cell.getNumericCellValue());
                            break;
                        case BOOLEAN: System.out.println(cell.getBooleanCellValue());
                            break;
                        default:
                            Boolean isdate = DateUtil.isCellDateFormatted(cell);
                            if (isdate) {
                                System.out.println("Date cell value is "+cell.getDateCellValue());
                            }
                            else{
                                System.out.println("Numeric cell value is "+cell.getNumericCellValue());
                            }
                    }
                }
                System.out.println();
            }
        }

        /////Printing data using Iterator method//////
        public void printDataIterator(){
            Iterator<Row> iterator = newSheet.iterator(); //return all the rows in the sheet and iterate them one by one.
            while(iterator.hasNext()){     //checks if there is next row present in the iterator.
                Row nextrow = iterator.next(); // returns the first row.
                Iterator<Cell>cellIterator = nextrow.cellIterator(); //returns all the cells in the row.
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next(); // returns the cell.
                    switch(cell.getCellType()) {
                        case STRING: System.out.println(cell.getStringCellValue());
                            break;
                        case NUMERIC: System.out.println(cell.getNumericCellValue());
                            break;
                        case BOOLEAN: System.out.println(cell.getBooleanCellValue());
                            break;

                    }
                }
                System.out.println();
            }
        }

    }


