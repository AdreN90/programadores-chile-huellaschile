package cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.mapper;

import cl.programadoreschile.adrian.userpet.domain.model.User;
import cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface UserPersistenceMapper {

    UserEntity toUserEntity(User person);

    User toUser(UserEntity userEntity);

    List<User> toUsers(List<UserEntity> persons);

}
