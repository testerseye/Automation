package excelcode;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

    //workbook objects
    private static XSSFWorkbook myWorkBook =null;
    private static XSSFSheet mySheet =null;
    //file output stream. It is required for writing in files.
    FileOutputStream out;
    //Variable for storing the path
    String excelFilePath="";
    //File input stream for writing in existing file.
    FileInputStream inputStream=null;

    //constructor for class
    public ExcelWriter(String excelFilePath,String sheetName){
        this.excelFilePath = excelFilePath;
        try {
            File f = new File(excelFilePath);
            //checking if file already exists. If it is present update that
            if(f.exists() && !f.isDirectory()) {
                inputStream = new FileInputStream(new File(excelFilePath));
                myWorkBook = new XSSFWorkbook(inputStream);
                //checking if sheet exists in work book
                // if sheet does not exist add a new one
                if(myWorkBook.getSheetIndex(sheetName) ==  -1){
                    mySheet = myWorkBook.createSheet(sheetName);
                }//else write in onld file
                else{
                    mySheet = myWorkBook.getSheet(sheetName);
                }
                System.out.println("Updating existing file");
            }
            else{
                //if file does not exist create one and write in that

                //checking if file already exists. If it is present update that
                myWorkBook = new XSSFWorkbook();

                        mySheet = myWorkBook.createSheet(sheetName);

                System.out.println("Creating new file");
                out=new FileOutputStream(excelFilePath);
                myWorkBook.write(out);
                out.close();
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    //writing string value in cell
    public  void excelWrite(int row, int col, String value) {
        XSSFCell myCell = getCell(row, col);
        if(myCell!=null){
            myCell.setCellValue(value);
            write();
        }
    }

    //writing integer value in cell
    public  void excelWrite(int row, int col, double value) {
        XSSFCell myCell = getCell(row, col);
        if(myCell!=null){
            myCell.setCellValue(value);
            write();
        }
    }

    //writing boolean value in cell
    public  void excelWrite(int row, int col, boolean value) {
        XSSFCell myCell = getCell(row, col);
        if(myCell!=null){
            myCell.setCellValue(value);
            write();
        }
    }

    //writing date value in cell
    public  void excelWrite(int row, int col, Date value) {
        XSSFCell myCell = getCell(row, col);
        if(myCell!=null){
            myCell.setCellValue(value);
            write();
        }
    }

    //Getting cell object
    private XSSFCell getCell(int row,int col){

        XSSFRow myRow = mySheet.getRow(row);
        if (myRow == null)
            myRow = mySheet.createRow(row);
        XSSFCell myCell = myRow.createCell(col);
        return myCell;
    }

    //writing a map in a specific row
    //in current scenario we need to pass all data as string. Its type casting is not done
    public void excelWrite(int row, Map<String,String> data){
        //checking if columns exist. If not add them
        addColumnHeader(data.keySet());
        //getting column index
        Map<String,Integer> columInfo = getColumnNoInMap();
        List keys = new ArrayList(columInfo.keySet());
        for (int i = 0; i < keys.size(); i++) {
            Object obj = keys.get(i);
            // do stuff here
        }
        for(String str: columInfo.keySet()){
            getCell(row,columInfo.get(str)).setCellValue(data.get(str));
        }
        for(int str=0;str<columInfo.keySet().size();str++){
            getCell(row,columInfo.get(keys.get(str))).setCellValue(data.get(keys.get(str)));
        }
        write();
    }

    //writing a map in a specific row
    //in current scenario we need to pass all data as string. Its type casting is not done
    public void excelWrite(Map<String,String> data){
        //checking if columns exist. If not add them
        addColumnHeader(data.keySet());
        //getting column index
        Map<String,Integer> columInfo = getColumnNoInMap();
        //adding value
        int lastRow = mySheet.getLastRowNum();
        for(String str: columInfo.keySet()){
            getCell(lastRow+1,columInfo.get(str)).setCellValue(data.get(str));
        }
        write();
    }

    // writing a list of map object in file
    public void excelWrite(List<Map<String,String>> data){
        //first data
        addColumnHeader(data.get(0).keySet());

        //column information
        Map<String,Integer> columInfo = getColumnNoInMap();

        //Last row number
        int lastRow = mySheet.getLastRowNum();
        //adding all items
        for(int i =0;i<data.size();i++){
            for(String str: columInfo.keySet()){
                getCell(i+lastRow + 1,columInfo.get(str)).setCellValue(data.get(i).get(str));
            }
        }
        write();
    }

    //Get index of all the column headers
    private Map<String,Integer> getColumnNoInMap(){
        Map<String,Integer> columnData = new HashMap<String,Integer>();

        XSSFRow myRow = mySheet.getRow(0);
        if(myRow == null){

            System.out.println("row is null");
            return columnData;
        }
        for(int i = 0;i<myRow.getLastCellNum();i++){
            columnData.put(myRow.getCell(i).getStringCellValue(), i);
        }

        return columnData;
    }

    //For writing and updating files, this function should be called. It is called in all functions.
    private void write(){
        try {
            inputStream.close();
        }catch(Exception e){
        }
        try{
            out= new FileOutputStream(excelFilePath);
            myWorkBook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
        }

    }

    //adding column header.
    private void addColumnHeader(Set<String> column){
        XSSFRow myRow = mySheet.getRow(0);
        int index =-1;
        if(myRow == null){
            for(String str : column){
                getCell(0,++index).setCellValue(str);
            }
            write();
        }

    }

}

