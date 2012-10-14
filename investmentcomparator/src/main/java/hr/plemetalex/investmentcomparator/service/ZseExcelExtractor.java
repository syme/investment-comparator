package hr.plemetalex.investmentcomparator.service;

import hr.plemetalex.investmentcomparator.domain.StockTrade;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZseExcelExtractor {

    static final String ZSE_HEADER_START = "ZSE - podaci o vrijednosnici";

    static final Logger LOG              = LoggerFactory.getLogger(ZseExcelExtractor.class);

    public List<StockTrade> listStockTrade(final File p_excelFile) {
        final List<StockTrade> l = new ArrayList<StockTrade>();

        Workbook wb = null;

        try {
            wb = WorkbookFactory.create(p_excelFile);
            final FormulaEvaluator fe = wb.getCreationHelper().createFormulaEvaluator();

            final Sheet sheet = wb.getSheetAt(0);

            final String header = PoiUtils.getCellStringValue(sheet.getRow(0).getCell(0), fe);
            String ticker = null;
            if (header.startsWith(ZSE_HEADER_START)) {
                ticker = header.substring(header.lastIndexOf(' ')).trim();
            }
            LOG.error(ticker);

            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                final Row r = sheet.getRow(i);

                if (r != null) {
                    final BigDecimal price = PoiUtils.getCellBigDecimalValue(r.getCell(2), fe);
                    LOG.info(price != null ? price.toString() : null);
                }

            }

        } catch (final IOException e) {
            return l;
        } catch (final InvalidFormatException e) {
            LOG.error(e.toString());
        }

        return l;
    }

}
