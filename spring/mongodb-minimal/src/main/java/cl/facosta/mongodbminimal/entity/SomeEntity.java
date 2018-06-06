package cl.facosta.mongodbminimal.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SomeEntity
{
    @Id
    private String id;

    private String someText;

    public SomeEntity(String someText)
    {
        this.someText = someText;
    }

    public SomeEntity(){}
}
