package facosta.jwtdemo.repository;

import facosta.jwtdemo.models.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, Long>
{
}
