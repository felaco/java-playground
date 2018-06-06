package cl.facosta.mongodbminimal.controller;

import cl.facosta.mongodbminimal.Repository.SomeEntityRepository;
import cl.facosta.mongodbminimal.entity.SomeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadWriteController
{
    private SomeEntityRepository someEntityRepository;

    @Autowired
    public ReadWriteController(SomeEntityRepository someEntityRepository)
    {
        this.someEntityRepository = someEntityRepository;
    }

    @GetMapping("/entity")
    public SomeEntity readEntity(@RequestParam("id") String id)
    {
        return someEntityRepository.findById(id).orElse(new SomeEntity());
    }

    @PostMapping("/entity")
    public void insertEntity(@RequestParam("text") String text)
    {
        SomeEntity newEntity = new SomeEntity(text);
        someEntityRepository.save(newEntity);
    }
}
