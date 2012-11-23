package hr.plemetalex.investmentcomparator.service.def;

import hr.plemetalex.investmentcomparator.domain.SecurityTrade;

import java.io.File;
import java.util.List;

public interface StockTradeExtractor {

    List<SecurityTrade> listStockTrade(final File p_file);

}
