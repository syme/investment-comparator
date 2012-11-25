package hr.plemetalex.investmentcomparator.service;

import hr.plemetalex.investmentcomparator.domain.Commodity;
import hr.plemetalex.investmentcomparator.domain.SecurityDayTrade;
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

public class GoldOrgExcelExtractor implements SecurityTradeExtractor {

    static final Logger LOG = LoggerFactory.getLogger(GoldOrgExcelExtractor.class);

    public List<SecurityDayTrade> listStockTrade(final File p_file) {
        final List<SecurityDayTrade> l = new ArrayList<SecurityDayTrade>();

        Workbook wb = null;

        try {
            wb = WorkbookFactory.create(p_file);
            final FormulaEvaluator fe = wb.getCreationHelper().createFormulaEvaluator();

            final Sheet sheet = wb.getSheet("Daily");

            for (int i = 9; i < 200 /* sheet.getLastRowNum() */; i++) {
                final Row r = sheet.getRow(i);

                if (r != null) {
                    final BigDecimal price = PoiUtils.getCellBigDecimalValue(r.getCell(4), fe);
                    final Date d = r.getCell(3).getDateCellValue();

                    if (price != null && d != null) {
                        final SecurityDayTrade st = new SecurityDayTrade(Commodity.GOLD, d, Currency.getInstance("USD"), price);
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
