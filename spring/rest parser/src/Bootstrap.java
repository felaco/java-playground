import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Bootstrap
{
    List<LocalDateTime> lista;

    public  Bootstrap()
    {
        lista = new ArrayList<>();
        generate_data(200, 120);
    }

    private void generate_data(int count, int delta)
    {
        int startTime = 1522008854;
        int nextTime = startTime;
        lista.add(LocalDateTime.ofInstant(Instant.ofEpochSecond(startTime),
                                          ZoneId.systemDefault()));

        for (int i = 0; i < count; i++)
        {
            nextTime += delta;
            lista.add(LocalDateTime.ofInstant(Instant.ofEpochSecond(nextTime),
                                              ZoneId.systemDefault()));

        }
    }

    public List<LocalDateTime> getLista()
    {
        return lista;
    }
}
