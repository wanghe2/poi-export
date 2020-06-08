package com.wang.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import freemarker.template.TemplateException;
/**
 * excel的预览
 * @author wanghe
 *
 */
@RestController
public class ExcelPreviewWeb {
	
	@RequestMapping("/previrewPDF")
	public void previrewPDF(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setHeader("Content-Type","application/pdf");  
		response.addHeader("Content-Disposition","inline;filename=" + new String("filepreview.pdf".getBytes(),"utf-8"));  
		File file=new File("C:\\Users\\wanghe\\Desktop\\新开普合同模板--批注版-反馈--pdf.pdf");
		FileInputStream in=new FileInputStream(file);
		OutputStream out=response.getOutputStream();
		try {
			byte[] bytes=new byte[(int)file.length()];
			while((in.read(bytes))!=-1) {
			}
			out.write(bytes);
			
		}finally {
			in.close();
			out.flush();
			out.close();
		}
	}
	

	@RequestMapping("/previrewJPG")
	public void previewImage(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setHeader("Content-Type","image/jpeg");  
		response.addHeader("Content-Disposition","inline;filename=" + new String("my.jpg".getBytes(),"utf-8"));  
		File file=new File("C:\\Users\\wanghe\\Desktop\\ToPDF2.jpg");
		FileInputStream in=new FileInputStream(file);
		OutputStream out=response.getOutputStream();
		try {
			byte[] bytes=new byte[(int)file.length()];
			while((in.read(bytes))!=-1) {
			}
			out.write(bytes);
			
		}finally {
			in.close();
			out.flush();
			out.close();
		}
	}
	
	
	@RequestMapping("traslanteImage")
    public void traslanteImage() throws FileNotFoundException {
		System.err.println("****good*****");
		 //加载Excel文档
	  com.spire.xls.Workbook wb = new com.spire.xls.Workbook();
      File file=new File("C:\\Users\\wanghe\\Desktop\\newcape.xls");
      wb.loadFromStream(new FileInputStream(file));

      //获取第1个工作表
      com.spire.xls.Worksheet sheet = wb.getWorksheets().get(0);

      //调用方法保存为PDF格式
      sheet.saveToImage("C:/Users/wanghe/Desktop/ToPDF2.jpg");
      System.err.println("--------success-------");
	}
	
	@RequestMapping("traslantePdf")
    public void traslantePdf() throws FileNotFoundException {
		System.err.println("****good*****");
		 //加载Excel文档
		com.spire.xls.Workbook wb = new com.spire.xls.Workbook();
      File file=new File("C:\\Users\\wanghe\\Desktop\\newcape.xls");
      wb.loadFromStream(new FileInputStream(file));

      //获取第1个工作表
      com.spire.xls.Worksheet sheet = wb.getWorksheets().get(0);

      //调用方法保存为PDF格式
      sheet.saveToPdf("C:/Users/wanghe/Desktop/ToPDF2.pdf");
      System.err.println("--------success-------");
	}
	
	
	@RequestMapping("/preview")
	public void excelExprotEsle(HttpServletRequest request,HttpServletResponse response) throws IOException, TemplateException {
      
		String fileName = "商业伙伴财务报表.xlsx";
		response.setHeader("Content-Disposition", "attachment;filename="+  URLEncoder.encode(fileName, "UTF-8"));//设置文件名
		  
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	    response.setHeader("Cache-Control", "no-cache");
	    response.setDateHeader("Expires", 0);
        FileInputStream in=new FileInputStream("D:\\wh\\code\\workspace\\my_workspace\\wordExport\\src\\main\\java\\xml\\cgtemp.xlsx");
        OutputStream out= response.getOutputStream();
        XSSFWorkbook excelBook=new XSSFWorkbook(in);
        /**
         * 进行模板的克隆(接下来的操作都是针对克隆后的sheet)
         */
        XSSFSheet sheet0=excelBook.cloneSheet(0);
        excelBook.setSheetName(0, "sheet-0"); 
       
     // 替换单元格内容(注意获取的cell的下标是合并之前的下标)
        replaceCellValue(sheet0.getRow(1).getCell(6), "2020.06.02");
        replaceCellValue(sheet0.getRow(2).getCell(0), "树维合同-wh");
        replaceCellValue(sheet0.getRow(13).getCell(0), "收货人及电话：王贺 17843081319");
        replaceCellValue(sheet0.getRow(14).getCell(0), "收货地址：湖东景园南区6栋");
        
     // 动态插入数据-增加行
        List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
        for (int i = 0; i < 5; i++) {
            Map<String,String> data = new HashMap<String, String>();
            data.put("index", String.valueOf(i));
            data.put("mc", "产品" + i);
            data.put("num", "5");
            data.put("price", "15");
            data.put("total", "75");
            data.put("id", "SW-C"+i);
            data.put("other", "无");
            data.put("remark", "好的");
            datas.add(data);
        }
        // 插入行
        sheet0.shiftRows(4, 15 + datas.size(), datas.size(), true, false);// 第1个参数是指要开始插入的行，第2个参数是结尾行数,第三个参数表示动态添加的行数
        for (int i = 0; i < datas.size(); i++) {
            XSSFRow creRow = sheet0.createRow(4 + i);
            creRow.setRowStyle(sheet0.getRow(4).getRowStyle());
            
            
            XSSFCell cell0=creRow.createCell(0);
            cell0.setCellValue(datas.get(i).get("index"));
            setReportStyle(excelBook,cell0);
            XSSFCell cell1=creRow.createCell(1);
            cell1.setCellValue(datas.get(i).get("mc"));
            setReportStyle(excelBook, cell1);
            
            
            
            XSSFCell cell2=creRow.createCell(2);
            cell2.setCellValue(datas.get(i).get("num"));
            setReportStyle(excelBook,cell2);
            XSSFCell cell3=creRow.createCell(3);
            cell3.setCellValue(datas.get(i).get("price"));
            setReportStyle(excelBook, cell3);
            
            
            XSSFCell cell4=creRow.createCell(4);
            cell4.setCellValue(datas.get(i).get("total"));
            setReportStyle(excelBook,cell4);
            XSSFCell cell5=creRow.createCell(5);
            cell5.setCellValue(datas.get(i).get("id"));
            setReportStyle(excelBook, cell5);
            
            XSSFCell cell6=creRow.createCell(6);
            cell6.setCellValue(datas.get(i).get("other"));
            setReportStyle(excelBook,cell6);
            XSSFCell cell7=creRow.createCell(7);
            cell7.setCellValue(datas.get(i).get("remark"));
            setReportStyle(excelBook, cell7);
        }
        
        XSSFCell nameCell = sheet0.getRow(9).getCell(1);
        System.out.println(nameCell.getStringCellValue());

        
        excelBook.removeSheetAt(0); // 移除workbook中的原模板sheet
        excelBook.write(out);
        in.close();
        out.flush();
        out.close();
        excelBook.close();
	}
	
	/**
     * 替换单元格的内容，单元格的获取位置是合并单元格之前的位置，也就是下标都是合并之前的下表
     * 
     * @param cell
     *            单元格
     * @param value
     *            需要设置的值
     */
    private  void replaceCellValue(Cell cell, Object value) {
        String val = value != null ? String.valueOf(value) : "";
        cell.setCellValue(val);
    }
    
    
    /**
     * 设置边框
     * @param workbook
     * @param cell
     * @param zyjz
     * @param sxjz
     * @param bw
     * @param color
     * @param fh
     * @param fontName
     */
    private  void setReportStyle(XSSFWorkbook excelBook, Cell cell) {
        XSSFCellStyle cellStyle = (XSSFCellStyle) excelBook.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN); // 下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);// 左边框
        cellStyle.setBorderTop(BorderStyle.THIN);// 上边框
        cellStyle.setBorderRight(BorderStyle.THIN);// 右边框
        cell.setCellStyle(cellStyle);
    }

    
    
   public static void main(String[] args) {}
    
}
