import lombok.Data;
import org.apache.commons.beanutils.BeanMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Data
public class Indicator
{
    private String indicatorName;

    private Integer period;
    private Float standardDeviation;
    private Integer longPeriod;
    private Integer shortPeriod;
    private Integer signalPeriod;


    public Indicator(String indicatorName)
    {
        this.indicatorName = indicatorName;
    }

    public Indicator(String indicatorName, int period)
    {
        this.indicatorName = indicatorName;
        this.period = period;
    }

    /**
     * returns a string representing the instance like it were a function call ignoring
     * the null attributes.
     *
     * Indicator indicator = new Indicator("BollingerBands");
     * indicator.setPeriod(14);
     * indicator.standardDeviation(2);
     *
     * indicator.stringRepresentation();
     * would returns "(14, 2)"
     *
     * and
     * indicator.stringRepresentation(true);
     * would returns "BollingerBands(14, 2)"
     * @param includeIndicatorName whether includes the indicatorName at the beginning
     * @return String
     */
    public String stringRepresentation(boolean includeIndicatorName)
    {
        List<String> skipKeys = new ArrayList<String>(Arrays.asList(
                "class", "indicatorName", "source"));

        BeanMap beanMap = new BeanMap(this);
        Iterator<String> it = beanMap.keyIterator();
        StringBuilder stringBuilder = new StringBuilder();

        if (includeIndicatorName)
            stringBuilder.append(beanMap.get("indicatorName"));

        stringBuilder.append('(');
        String commaChar = "";
        while (it.hasNext())
        {
            String key = it.next();
            if (skipKeys.contains(key)) continue;

            Object value = beanMap.get(key);
            if (value != null)
            {
                stringBuilder.append(commaChar);
                stringBuilder.append(value);
                commaChar = ", ";
            }
        }

        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    public String stringRepresentation()
    {
        return stringRepresentation(false);
    }
}
