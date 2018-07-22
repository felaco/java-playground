package facosta.jwtdemo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RandomController
{
    @GetMapping("/user")
    @Secured("ROLE_USER")
    public ResponseEntity userFunction()
    {
        Map<String, String> body = new HashMap<>();
        body.put("response", "estás viendo este mensaje como rol usuario");

        return ResponseEntity.ok(body);
    }

    @GetMapping("/admin")
    @Secured("ROLE_ADMIN")
    public ResponseEntity adminFunction()
    {
        Map<String, String> body = new HashMap<>();
        body.put("response", "estás viendo este mensaje como rol admin");

        return ResponseEntity.ok(body);
    }
}
