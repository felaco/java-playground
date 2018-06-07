

public class MainClass
{
    public static void main(String[] args)
    {
        Indicator indicator = new Indicator("rsi", 14);
        indicator.setLongPeriod(20);

        System.out.println(indicator.stringRepresentation());
        System.out.println(indicator.stringRepresentation(true));
    }
}
