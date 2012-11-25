package hr.plemetalex.investmentcomparator.service;

import hr.plemetalex.investmentcomparator.domain.Security;
import hr.plemetalex.investmentcomparator.domain.SecurityDayTrade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityTradeAggregator {

    static final Logger LOG = LoggerFactory.getLogger(SecurityTradeAggregator.class);

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
}
