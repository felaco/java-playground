package facosta;

import com.fasterxml.jackson.databind.ObjectMapper;
import facosta.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RestParser
{
    public static void main(String[] args)
    {
        String url = "http://jsonplaceholder.typicode.com/users";
        printParserAsString(url);
        printParserAsObject(url);
    }

    private static void printParserAsString(String url)
    {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        List <User> list;

        try
        {
            list = mapper.readValue(result, mapper.getTypeFactory().constructCollectionType(List.class, User.class));
            System.out.println(list);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void printParserAsObject(String url)
    {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> response = restTemplate.getForEntity(url, User[].class);
        List<User> list = Arrays.asList(response.getBody());

        System.out.println(list);
    }

}
