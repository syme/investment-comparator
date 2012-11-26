package hr.plemetalex.investmentcomparator;

import hr.plemetalex.investmentcomparator.domain.Security;
import hr.plemetalex.investmentcomparator.domain.SecurityDayTrade;
import hr.plemetalex.investmentcomparator.service.GoldOrgExcelExtractor;
import hr.plemetalex.investmentcomparator.service.PlemetalexUtils;
import hr.plemetalex.investmentcomparator.service.SecurityTradeService;
import hr.plemetalex.investmentcomparator.service.ZseExcelExtractor;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

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
        final List<SecurityDayTrade> l_ptkm = z.listStockTrade(new File("c:\\\\ptkm.xlsx"));

        final GoldOrgExcelExtractor ge = new GoldOrgExcelExtractor();
        final List<SecurityDayTrade> l_gold = ge.listStockTrade(new File("c:\\\\gold_prices2.xls"));

        final SortedMap<Date, Map<Security, SecurityDayTrade>> map = SecurityTradeService.aggregateSecurityTrade(l_ptkm, l_gold);

        for (final Entry<Date, Map<Security, SecurityDayTrade>> e : map.entrySet()) {
            LOG.info("Date:" + PlemetalexUtils.SDF_DD_MM_YYYY.format(e.getKey()));
            for (final Entry<Security, SecurityDayTrade> es : e.getValue().entrySet()) {
                LOG.info("Security:" + es.getValue().getSecurity() + ", value:" + es.getValue().getClosingPrice());
            }
        }

        SecurityTradeService.writeSecurityTradeMapToExcel(map);

    }
}
