package hr.plemetalex.investmentcomparator;

import hr.plemetalex.investmentcomparator.service.GoldOrgExcelExtractor;
import hr.plemetalex.investmentcomparator.service.ZseExcelExtractor;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 * 
 */
public class App {

    static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(final String[] args) {

        LOG.info("Hello.");

        final ZseExcelExtractor z = new ZseExcelExtractor();
        // z.listStockTrade(new File("c:\\\\ptkm.xlsx"));

        final GoldOrgExcelExtractor ge = new GoldOrgExcelExtractor();
        ge.listStockTrade(new File("c:\\\\gold_prices.xls"));
    }
}
