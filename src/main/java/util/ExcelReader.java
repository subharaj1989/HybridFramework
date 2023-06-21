package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;  

public class ExcelReader {
	
	
	
	
	  public static Sheet getSheetName(String Filepath,String Sheetname) throws
	  EncryptedDocumentException, IOException { Sheet
	  sheet=getWorkBook(Filepath).getSheet(Sheetname);
	  
	  return sheet; }
	  
	  public static Workbook getWorkBook(String FilePath) throws
	  EncryptedDocumentException, IOException { return WorkbookFactory.create(new
	  File(FilePath)); }
	 
	
	
  
  public static  List<Map<String, String>> read(String Filepath,String sheetname)
  {
	  
	  Workbook wb;
	  List<Map<String,String>> l=new ArrayList<Map<String,String>>();
	  List<String> keylist=new ArrayList<String>();
	try {
		FileInputStream fis=new FileInputStream(new File(Filepath));
		wb = WorkbookFactory.create(fis);
	
     
    //get the sheet which needs read operation
        Sheet sheet = wb.getSheet(sheetname);
	 
	  for(int i=0;i<=sheet.getRow(0).getLastCellNum()-1;i++)
	  {
	  String key =sheet.getRow(0).getCell(i).getStringCellValue();

	  keylist.add(key);
	  }
	  
	  for(int i=1;i<=sheet.getLastRowNum();i++)
	  {
		  Map<String,String> map=new HashMap<String,String>();
		 
		for(int j=0;j<keylist.size();j++)
			  
		  {
			  String value;
			 
			if(sheet.getRow(i).getCell(j).getCellType()==CellType.NUMERIC)
			  {
				 // NumberToTextConverter.toText(cell.getNumericCellValue())
				   value= NumberToTextConverter.toText(sheet.getRow(i).getCell(j).getNumericCellValue());
			  }
			  else
			  {
				   value=sheet.getRow(i).getCell(j).getStringCellValue();
			  }
			 
			  map.put(keylist.get(j), value);
		  }
		 l.add(map);
	  }
	fis.close();
	}
	catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return l; 
  }
  
  public static void write(String Filepath,String sheetname,Map<String,String> map)
  {
	  try {
		  FileInputStream inputStream = new FileInputStream(new File(Filepath));
          Workbook wb = WorkbookFactory.create(inputStream);
      Sheet sheet=  wb.getSheet(sheetname);
      if(sheet==null)
      {
		 sheet=wb.createSheet(sheetname);
      }
		int rownumber=sheet.getLastRowNum();
		int columnnumber=0;
		Row row = null;
		Cell cell;
		if (rownumber<0)
		{
			rownumber=0;
			row=sheet.createRow(rownumber++);
			
			for(Map.Entry<String,String> entry:map.entrySet())
			{
				 cell=row.createCell(columnnumber++);
				cell.setCellValue(entry.getKey());
			}
			rownumber=sheet.getLastRowNum();
			columnnumber=0;
		}
		
		row=sheet.createRow(++rownumber);
		for(Map.Entry<String,String> entry:map.entrySet())
		{
			 cell=row.createCell(columnnumber++);
			cell.setCellValue(entry.getValue());
		}
		FileOutputStream fos=new FileOutputStream(Filepath);
		wb.write(fos);
		fos.close();
		
		
		
	} catch (EncryptedDocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
   
  
   
}
