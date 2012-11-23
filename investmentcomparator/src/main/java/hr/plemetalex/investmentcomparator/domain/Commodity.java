package hr.plemetalex.investmentcomparator.domain;

import java.io.Serializable;

public class Commodity extends Security implements Serializable {

    private static final long serialVersionUID = -3574593470333914919L;

    private String            m_name;

    public static Commodity   GOLD             = new Commodity("Gold");
    public static Commodity   SILVER           = new Commodity("Silver");
    public static Commodity   BRENT            = new Commodity("Brent");
    public static Commodity   CORN             = new Commodity("Corn");

    public Commodity() {
    }

    public Commodity(final String p_name) {
        super();
        m_name = p_name;
    }

    public String getName() {
        return m_name;
    }

    public void setName(final String p_name) {
        m_name = p_name;
    }

    @Override
    public String toString() {
        return "Commodity: " + m_name;
    }

}
