package excelcode;

import com.google.common.collect.Table;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.apache.poi.ss.util.SheetUtil.getCell;

public class ExcelWriterNew {
    String excelpath = null;
    FileInputStream in;
    FileOutputStream out;
    Workbook wk;
    Sheet sh;
    Row rw;
    Cell cell;

    public ExcelWriterNew(String excelpath, String sheetname) throws FileNotFoundException {
        this.excelpath = excelpath;
        try {
            File f = new File(excelpath);
            in = new FileInputStream(f);
            if (!f.exists()) {
                in = new FileInputStream(new File(excelpath));
            }
            wk = new XSSFWorkbook(in);
            sh = wk.getSheet(sheetname);
            if (sh == null) {
                wk.createSheet();
                System.out.println("New sheet created");
            } else {
                System.out.println("Sheet already exists");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private Cell getCell(int row, int col) {
        sh.getRow(row);
        if(rw==null){
            sh.createRow(row);
        }
        cell = rw.createCell(col);

        return cell;
    }

    public void writeStringData(int row, int col, String data) {
        XSSFCell cell = (XSSFCell) getCell(row, col);
        if (cell != null) {
            cell.setCellValue(data);
        }
    }

    private Map<String,Integer>getColumnNoInMap(){
        Map<String,Integer>columnData = new HashMap<>();
        rw = sh.getRow(0);
        if(rw==null){
            System.out.println("Row is null");
            return columnData;
        }
        for(int i=0;i<rw.getLastCellNum();i++){
            columnData.put(rw.getCell(i).getStringCellValue(),i);
        }
        return columnData;
    }
    private void write(){
        try {
            in.close();
        }catch(Exception e){
        }
        try{
            out= new FileOutputStream(excelpath);
            wk.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
        }
    }

    public void writeRow(int row, Map<String,String> data){
        addColumnHeader(data.keySet());
        Map<String,Integer>colinfo = getColumnNoInMap();
        for(String str: colinfo.keySet()){
           getCell(row,colinfo.get(str));
        }
        write();
    }

    private void addColumnHeader(Set<String> column) {
        rw = sh.getRow(0);
        int index = -1;
        if(rw==null){
            for(String str:column)
                getCell(0,++index).setCellValue(str);
        }
        write();
    }

}





