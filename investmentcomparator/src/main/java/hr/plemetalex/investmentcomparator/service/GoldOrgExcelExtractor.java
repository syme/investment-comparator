package hr.plemetalex.investmentcomparator.service;

import hr.plemetalex.investmentcomparator.domain.Commodity;
import hr.plemetalex.investmentcomparator.domain.SecurityTrade;
import hr.plemetalex.investmentcomparator.service.def.StockTradeExtractor;

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

public class GoldOrgExcelExtractor implements StockTradeExtractor {

    static final Logger LOG = LoggerFactory.getLogger(GoldOrgExcelExtractor.class);

    public List<SecurityTrade> listStockTrade(final File p_file) {
        final List<SecurityTrade> l = new ArrayList<SecurityTrade>();

        Workbook wb = null;

        try {
            wb = WorkbookFactory.create(p_file);
            final FormulaEvaluator fe = wb.getCreationHelper().createFormulaEvaluator();

            final Sheet sheet = wb.getSheet("Daily");

            final String header = PoiUtils.getCellStringValue(sheet.getRow(0).getCell(0), fe);
            final String ticker = null;

            for (int i = 9; i < 200 /* sheet.getLastRowNum() */; i++) {
                final Row r = sheet.getRow(i);

                if (r != null) {
                    final BigDecimal price = PoiUtils.getCellBigDecimalValue(r.getCell(4), fe);
                    final Date d = r.getCell(3).getDateCellValue();

                    if (price != null && d != null) {
                        final SecurityTrade st = new SecurityTrade();
                        st.setClosingPrice(price);
                        st.setCurrency(Currency.getInstance("USD"));
                        st.setSecurity(Commodity.GOLD);
                        st.setDate(d);
                        l.add(st);
                    }
                    LOG.info(price != null ? d + " " + price.toString() : null);
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
