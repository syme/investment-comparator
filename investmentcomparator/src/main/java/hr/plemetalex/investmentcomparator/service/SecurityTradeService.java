package hr.plemetalex.investmentcomparator.service;

import hr.plemetalex.investmentcomparator.domain.Security;
import hr.plemetalex.investmentcomparator.domain.SecurityDayTrade;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityTradeService {

    static final Logger LOG = LoggerFactory.getLogger(SecurityTradeService.class);

    public static SortedMap<Date, Map<Security, SecurityDayTrade>> aggregateSecurityTrade(final List<SecurityDayTrade>... p_securityTradeList) {

        final SortedMap<Date, Map<Security, SecurityDayTrade>> map = new TreeMap<Date, Map<Security, SecurityDayTrade>>();

        for (final List<SecurityDayTrade> secDayList : p_securityTradeList) {
            for (final SecurityDayTrade sdt : secDayList) {
                if (sdt.getDate() == null || sdt.getSecurity() == null) {
                    LOG.warn("No trade date/security found:" + sdt);
                }
                else {
                    if (map.containsKey(sdt.getDate())) {
                        map.get(sdt.getDate()).put(sdt.getSecurity(), sdt);
                    }
                    else {
                        final Map<Security, SecurityDayTrade> m = new HashMap<Security, SecurityDayTrade>();
                        m.put(sdt.getSecurity(), sdt);
                        map.put(sdt.getDate(), m);
                    }
                }

            }
        }

        return map;
    }

    public static void writeSecurityTradeMapToExcel(final SortedMap<Date, Map<Security, SecurityDayTrade>> p_map) {
        try {
            final FileOutputStream fileOut = new FileOutputStream("c:\\\\outtest.xls");
            final HSSFWorkbook workbook = new HSSFWorkbook();
            final HSSFSheet worksheet = workbook.createSheet("Plemetalex");
            final HSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));

            final List<Security> uniqSec = new ArrayList<Security>();
            int rowNo = 0;
            int headerCol = 1;

            final HSSFRow header = worksheet.createRow(rowNo);

            rowNo++;
            for (final Entry<Date, Map<Security, SecurityDayTrade>> entry : p_map.entrySet()) {
                final HSSFRow row = worksheet.createRow(rowNo);

                final HSSFCell cell = row.createCell(0);
                cell.setCellValue(entry.getKey());
                cell.setCellStyle(dateStyle);

                for (final Entry<Security, SecurityDayTrade> e : entry.getValue().entrySet()) {
                    if (!uniqSec.contains(e.getKey())) {
                        uniqSec.add(e.getKey());
                        final HSSFCell hCell = header.createCell(headerCol++);
                        hCell.setCellValue(e.getKey().toString());

                    }

                    final HSSFCell secCell = row.createCell(1 + uniqSec.indexOf(e.getKey()));
                    secCell.setCellValue(e.getValue().getClosingPrice().doubleValue());
                }

                rowNo++;
            }

            // index from 0,0... cell A1 is cell(0,0)
            // final HSSFRow row1 = worksheet.createRow((short) 0);
            //
            // final HSSFCell cellA1 = row1.createCell(0);
            // cellA1.setCellValue("Hello");
            // HSSFCellStyle cellStyle = workbook.createCellStyle();
            // cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
            // cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            // cellA1.setCellStyle(cellStyle);
            //
            // final HSSFCell cellB1 = row1.createCell(1);
            // cellB1.setCellValue("Goodbye");
            // cellStyle = workbook.createCellStyle();
            // cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
            // cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            // cellB1.setCellStyle(cellStyle);
            //
            // final HSSFCell cellC1 = row1.createCell(2);
            // cellC1.setCellValue(true);
            //
            // final HSSFCell cellD1 = row1.createCell(3);
            // cellD1.setCellValue(new Date());
            // cellStyle = workbook.createCellStyle();
            // cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
            // cellD1.setCellStyle(cellStyle);

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
