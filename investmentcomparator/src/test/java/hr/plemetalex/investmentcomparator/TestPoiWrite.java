package hr.plemetalex.investmentcomparator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class TestPoiWrite {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        try {
            final FileOutputStream fileOut = new FileOutputStream("c:\\\\poi-test.xls");
            final HSSFWorkbook workbook = new HSSFWorkbook();
            final HSSFSheet worksheet = workbook.createSheet("POI Worksheet");

            // index from 0,0... cell A1 is cell(0,0)
            final HSSFRow row1 = worksheet.createRow((short) 0);

            final HSSFCell cellA1 = row1.createCell(0);
            cellA1.setCellValue("Hello");
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellA1.setCellStyle(cellStyle);

            final HSSFCell cellB1 = row1.createCell(1);
            cellB1.setCellValue("Goodbye");
            cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellB1.setCellStyle(cellStyle);

            final HSSFCell cellC1 = row1.createCell(2);
            cellC1.setCellValue(true);

            final HSSFCell cellD1 = row1.createCell(3);
            cellD1.setCellValue(new Date());
            cellStyle = workbook.createCellStyle();
            cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
            cellD1.setCellStyle(cellStyle);

            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }

}
