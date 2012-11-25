package hr.plemetalex.investmentcomparator.domain;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

public class SecurityDayTrade {

    private Security   m_security;
    private Date       m_date;
    private Currency   m_currency;
    private BigDecimal m_closingPrice;
    private BigDecimal m_openingPrice;
    private BigDecimal m_maxPrice;
    private BigDecimal m_minPrice;
    private BigDecimal m_avgPrice;
    private Integer    m_txCount;
    private BigDecimal m_volume;
    private BigDecimal m_stockCount;

    public SecurityDayTrade(final Security p_security, final Date p_date, final Currency p_currency, final BigDecimal p_closingPrice) {
        super();
        m_security = p_security;
        m_date = p_date;
        m_currency = p_currency;
        m_closingPrice = p_closingPrice;
    }

    public Security getSecurity() {
        return m_security;
    }

    public void setSecurity(final Security p_security) {
        m_security = p_security;
    }

    public Date getDate() {
        return m_date;
    }

    public void setDate(final Date p_date) {
        m_date = p_date;
    }

    public BigDecimal getClosingPrice() {
        return m_closingPrice;
    }

    public void setClosingPrice(final BigDecimal p_closingPrice) {
        m_closingPrice = p_closingPrice;
    }

    public BigDecimal getOpeningPrice() {
        return m_openingPrice;
    }

    public void setOpeningPrice(final BigDecimal p_openingPrice) {
        m_openingPrice = p_openingPrice;
    }

    public BigDecimal getMaxPrice() {
        return m_maxPrice;
    }

    public void setMaxPrice(final BigDecimal p_maxPrice) {
        m_maxPrice = p_maxPrice;
    }

    public BigDecimal getMinPrice() {
        return m_minPrice;
    }

    public void setMinPrice(final BigDecimal p_minPrice) {
        m_minPrice = p_minPrice;
    }

    public BigDecimal getAvgPrice() {
        return m_avgPrice;
    }

    public void setAvgPrice(final BigDecimal p_avgPrice) {
        m_avgPrice = p_avgPrice;
    }

    public Integer getTxCount() {
        return m_txCount;
    }

    public void setTxCount(final Integer p_txCount) {
        m_txCount = p_txCount;
    }

    public BigDecimal getVolume() {
        return m_volume;
    }

    public void setVolume(final BigDecimal p_volume) {
        m_volume = p_volume;
    }

    public BigDecimal getStockCount() {
        return m_stockCount;
    }

    public void setStockCount(final BigDecimal p_stockCount) {
        m_stockCount = p_stockCount;
    }

    public Currency getCurrency() {
        return m_currency;
    }

    public void setCurrency(final Currency p_currency) {
        m_currency = p_currency;
    }

}
