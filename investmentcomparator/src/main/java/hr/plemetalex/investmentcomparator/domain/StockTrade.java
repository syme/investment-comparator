package hr.plemetalex.investmentcomparator.domain;

import java.math.BigDecimal;
import java.util.Date;

public class StockTrade {

    private Stock      m_stock;
    private Date       m_date;
    private BigDecimal m_closingPrice;
    private BigDecimal m_openingPrice;
    private BigDecimal m_maxPrice;
    private BigDecimal m_minPrice;
    private BigDecimal m_avgPrice;
    private Integer    m_txCount;
    private BigDecimal m_volume;
    private BigDecimal m_stockCount;

    public Stock getStock() {
        return m_stock;
    }

    public void setStock(final Stock p_stock) {
        m_stock = p_stock;
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

}
