package cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.mapper;

import cl.huellaschile.medicalcon.domain.model.User;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    UserEntity toUserEntity(User user);

    User toUser(UserEntity userEntity);

}
