package br.org.jprojects.restapiusers.mappers;

import br.org.jprojects.restapiusers.dto.UserDTO;
import br.org.jprojects.restapiusers.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    User toModel(UserDTO userDTO);

    UserDTO toDTO(User user);
}
