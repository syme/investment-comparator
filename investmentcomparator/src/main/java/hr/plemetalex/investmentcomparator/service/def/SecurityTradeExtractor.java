package hr.plemetalex.investmentcomparator.service.def;

import hr.plemetalex.investmentcomparator.domain.SecurityDayTrade;

import java.io.File;
import java.util.List;

public interface SecurityTradeExtractor {

    List<SecurityDayTrade> listStockTrade(final File p_file);

}
