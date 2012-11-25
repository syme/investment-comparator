package hr.plemetalex.investmentcomparator.service;

import hr.plemetalex.investmentcomparator.domain.Market;
import hr.plemetalex.investmentcomparator.domain.SecurityDayTrade;
import hr.plemetalex.investmentcomparator.domain.Stock;
import hr.plemetalex.investmentcomparator.service.def.SecurityTradeExtractor;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZseExcelExtractor implements SecurityTradeExtractor {

    static final String ZSE_HEADER_START = "ZSE - podaci o vrijednosnici";

    static final Logger LOG              = LoggerFactory.getLogger(ZseExcelExtractor.class);

    public List<SecurityDayTrade> listStockTrade(final File p_file) {
        final List<SecurityDayTrade> l = new ArrayList<SecurityDayTrade>();

        Workbook wb = null;

        try {
            wb = WorkbookFactory.create(p_file);
            final FormulaEvaluator fe = wb.getCreationHelper().createFormulaEvaluator();

            final Sheet sheet = wb.getSheetAt(0);

            final String header = PoiUtils.getCellStringValue(sheet.getRow(0).getCell(0), fe);
            String ticker = null;
            if (header.startsWith(ZSE_HEADER_START)) {
                ticker = header.substring(header.lastIndexOf(' ')).trim();
            }
            final Stock stock = new Stock(ticker, Market.ZSE);

            for (int i = 3; i < sheet.getLastRowNum(); i++) {
                final Row r = sheet.getRow(i);

                if (r != null) {
                    final BigDecimal price = PoiUtils.getCellBigDecimalValue(r.getCell(2), fe);
                    final Date date = r.getCell(0).getDateCellValue();
                    if (price != null && date != null) {
                        final SecurityDayTrade st = new SecurityDayTrade(stock, date, Currency.getInstance("HRK"), price);
                        l.add(st);
                    }
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
