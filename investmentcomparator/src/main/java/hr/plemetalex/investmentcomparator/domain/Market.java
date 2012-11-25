package hr.plemetalex.investmentcomparator.domain;

public class Market {

    public static Market ZSE            = new Market("ZSE");
    public static Market NYSE           = new Market("NYSE");
    public static Market DEUTSCHE_BÖRSE = new Market("DEUTSCHE_BÖRSE");
    public static Market LBMA           = new Market("LBMA");
    public static Market VARAZDIN_SE    = new Market("VSE");

    private String       m_name;

    public Market(final String p_name) {
        super();
        m_name = p_name;
    }

    public Market() {
    }

    @Override
    public String toString() {
        return m_name;
    }

    public String getName() {
        return m_name;
    }

    public void setName(final String p_name) {
        m_name = p_name;
    }

}
