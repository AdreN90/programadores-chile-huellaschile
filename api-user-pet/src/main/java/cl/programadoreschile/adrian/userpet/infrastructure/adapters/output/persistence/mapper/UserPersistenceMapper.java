package cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.mapper;

import cl.programadoreschile.adrian.userpet.domain.model.User;
import cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    UserEntity toUserEntity(User user);

    User toUser(UserEntity userEntity);

}
