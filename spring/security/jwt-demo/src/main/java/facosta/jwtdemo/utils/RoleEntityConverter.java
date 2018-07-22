package facosta.jwtdemo.utils;

import facosta.jwtdemo.models.RoleEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RoleEntityConverter
{
    public static SimpleGrantedAuthority toSimpleGrantedAuthority(RoleEntity roleEntity)
    {
        return new SimpleGrantedAuthority(roleEntity.getName());
    }

    public static List<SimpleGrantedAuthority> toSimpleGrantedAuthority(Collection<RoleEntity> roleEntityList)
    {
        return roleEntityList
                .stream()
                .map(RoleEntityConverter::toSimpleGrantedAuthority)
                .collect(Collectors.toList());
    }
}
