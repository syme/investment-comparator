package hr.plemetalex.investmentcomparator.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PoiUtils {

    static final Logger LOG = LoggerFactory.getLogger(PoiUtils.class);

    /**
     * Provjerava vrstu celije, ako je string vraca string vrijednost, ako je numeric, uzima brojku i vraca brojku kao string (POI baca exception ako
     * se pozove .getStringCellValue()
     * 
     * @param p_cell
     * @return
     */
    public static String getCellStringValue(final Cell p_cell, final FormulaEvaluator p_fe) {

        if (p_cell == null) {
            return null;
        }

        try {
            if (p_cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                return null;
            }

            final CellValue cellValue = p_fe.evaluate(p_cell);

            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    final double d = cellValue.getNumberValue();
                    if (Math.round(d) == d) { // zaokruzivat cemo
                        return Integer.toString((int) d);
                    }
                    else {
                        return Double.toString(d);
                    }

                case Cell.CELL_TYPE_STRING:
                    return cellValue.getStringValue();

                case Cell.CELL_TYPE_BOOLEAN:
                    return Boolean.toString(cellValue.getBooleanValue());

                case Cell.CELL_TYPE_ERROR:
                    // LOG.warn(cellValue);
                    return null;

                default:
            }

        } catch (final Exception e) {
            // LOG.warn("Exception " + e);
        }

        return null;

    }

    public static BigDecimal getCellBigDecimalValue(final Cell p_cell, final FormulaEvaluator p_fe) {

        if (p_cell == null) {
            return null;
        }

        final CellValue cellValue = p_fe.evaluate(p_cell);

        if (cellValue != null) {
            if (Cell.CELL_TYPE_NUMERIC == cellValue.getCellType()) {
                final BigDecimal bdRaw = new BigDecimal(Double.valueOf(cellValue.getNumberValue()).toString());
                return bdRaw.setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            else {
                return tryToExtractPrice(getCellStringValue(p_cell, p_fe), null, null);
            }
        }

        return null;

    }

    public static Date getDate(final Cell p_cell, final FormulaEvaluator p_fe, final String p_pattern) {

        if (p_cell == null) {
            return null;
        }

        final CellValue cellValue = p_fe.evaluate(p_cell);

        if (cellValue != null) {
            final SimpleDateFormat sdf = new SimpleDateFormat(p_pattern);
            try {
                final Date d = sdf.parse(cellValue.getStringValue());
                return d;
            } catch (final ParseException e) {
                LOG.warn("Couldn't parse date:'" + cellValue.getStringValue() + "', pattern:" + p_pattern);
            }
        }

        return null;

    }

    public static BigDecimal tryToExtractPrice(final String p_cijenaString, final String p_hintDecimalSeparator, final String p_hintThousandsSeparator) {

        if (p_cijenaString == null) {
            return null;
        }

        String cijenaStr = p_cijenaString.toLowerCase();

        if (p_hintThousandsSeparator != null) {
            cijenaStr = cijenaStr.replace(p_hintThousandsSeparator, "");
        }
        if (p_hintDecimalSeparator != null) {
            cijenaStr = cijenaStr.replace(p_hintDecimalSeparator, ".");
        }

        BigDecimal bd = null;
        try {
            bd = new BigDecimal(cijenaStr);
        } catch (final Exception e) {
            // necemo nista logirati
        }
        if (bd != null) {
            return bd;
        }

        try {
            return new BigDecimal(cijenaStr);
        } catch (final NumberFormatException e) {
            LOG.warn("Nisam uspio izvući BigDecimal iz Stringa: '" + cijenaStr + "'");
            return null;
        }

    }

}
