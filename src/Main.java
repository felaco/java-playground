import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        List<LocalDateTime>lista = new Bootstrap().getLista();
        System.out.println(lista);
    }
}
