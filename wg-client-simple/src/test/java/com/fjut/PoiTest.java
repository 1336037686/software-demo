package com.fjut;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
 
/**
 * 测试POI
 * 
 * @author Loren
 *
 */
public class PoiTest {
	@Test
	public void Poi1Test() throws IOException {
		// 创建工作簿
		XSSFWorkbook wb = new XSSFWorkbook();
		// 工作表
		XSSFSheet sheet = wb.createSheet("学生信息表");
		// 标头行，代表第一行
		XSSFRow header = sheet.createRow(0);
		// 创建单元格，0代表第一行第一列
		XSSFCell cell = header.createCell(0);
		cell.setCellValue("学号");
		header.createCell(1).setCellValue("姓名");
		header.createCell(2).setCellValue("专业");
		header.createCell(3).setCellValue("班级");
		header.createCell(4).setCellValue("身份证号");
		header.createCell(5).setCellValue("宿舍号");
		header.createCell(6).setCellValue("报道日期");
		// 设置列的宽度
		// getPhysicalNumberOfCells()代表这行有多少包含数据的列
//		for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
//			// POI设置列宽度时比较特殊，它的基本单位是1/255个字符大小，
//			// 因此我们要想让列能够盛的下20个字符的话，就需要用255*20	
//			sheet.setColumnWidth(i, 255 * 20);
//		}
		// 设置行高，30像素
//		header.setHeightInPoints(30);
		//输出文件要么是 \\要么/否则会报错
		FileOutputStream fos = new FileOutputStream("C:/Users/LGX/Desktop/新建文件夹/PoiDemo.xlsx");
		// 向指定文件写入内容
		wb.write(fos);
		//关闭流
		fos.close();
	}
}