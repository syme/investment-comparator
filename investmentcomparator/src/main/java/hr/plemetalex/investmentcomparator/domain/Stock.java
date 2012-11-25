package hr.plemetalex.investmentcomparator.domain;

import java.io.Serializable;

public class Stock extends Security implements Serializable {

    private static final long serialVersionUID = 7855703899284065782L;

    private String            m_ticker;
    private String            m_name;
    private Market            m_market;

    public Stock(final String p_ticker) {
        super();
        m_ticker = p_ticker;
    }

    public Stock(final String p_ticker, final Market p_market) {
        super();
        m_ticker = p_ticker;
        m_market = p_market;
    }

    public Stock(final String p_ticker, final String p_name, final Market p_market) {
        super();
        m_ticker = p_ticker;
        m_name = p_name;
        m_market = p_market;
    }

    @Override
    public String toString() {
        return m_ticker + "(" + m_market + ")";
    }

    public String getTicker() {
        return m_ticker;
    }

    public void setTicker(final String p_ticker) {
        m_ticker = p_ticker;
    }

    public String getName() {
        return m_name;
    }

    public void setName(final String p_name) {
        m_name = p_name;
    }

    public Market getMarket() {
        return m_market;
    }

    public void setMarket(final Market p_market) {
        m_market = p_market;
    }

}
