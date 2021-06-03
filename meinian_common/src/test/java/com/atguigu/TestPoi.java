package com.atguigu;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;

/**
 * TestPoi
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-05-29
 * @Description:
 */
public class TestPoi {

    /**
     * 往excel表格里面写数据
     */
    @Test
    public void test02() throws Exception{
        // 创建excel对象
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建页签对象
        XSSFSheet sheet = workbook.createSheet("硅谷");
        // 先在第一行写数据
        XSSFRow row = sheet.createRow(0);
        // 在第一行的第一列数据
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("年龄");
        // 在写第二行数据
        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("001");
        row1.createCell(1).setCellValue("张三");
        row1.createCell(2).setCellValue(18);

        // 在写第二行数据
        XSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("002");
        row2.createCell(1).setCellValue("李四");
        row2.createCell(2).setCellValue(28);

        // 使用io读写数据
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\guigu.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        workbook.close();
    }



    /**
     * 往excel表格读数据
     * @throws Exception
     */
    @Test
    public void test01() throws Exception{
        // 创建excel表格对象
        XSSFWorkbook workbook = new XSSFWorkbook("d:\\atguigu.xlsx");
        // 获取页签对象
        XSSFSheet sheetAt = workbook.getSheetAt(0);
        // 获取行对象
        for (Row row : sheetAt) {
            // 获取列对象
            for (Cell cell : row) {

                String value = cell.getStringCellValue();

                System.out.println(value);

            }
        }
    }
}