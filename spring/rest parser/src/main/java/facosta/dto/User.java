package facosta.dto;

import lombok.Data;

@Data
public class User
{
    private long id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private Address address;
    private Company company;
}
