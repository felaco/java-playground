package facosta.jwtdemo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginUserDto
{
    @NotBlank(message = "username no puede estar vacío")
    private String username;

    @NotBlank(message = "password no puede estar vacío")
    private String password;
}
