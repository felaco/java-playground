package cl.facosta.mongodbminimal.Repository;

import cl.facosta.mongodbminimal.entity.SomeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SomeEntityRepository extends MongoRepository<SomeEntity, String>
{
}
