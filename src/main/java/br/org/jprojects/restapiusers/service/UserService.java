package br.org.jprojects.restapiusers.service;

import br.org.jprojects.restapiusers.dto.UserDTO;
import br.org.jprojects.restapiusers.entity.User;
import br.org.jprojects.restapiusers.mappers.UserMapper;
import br.org.jprojects.restapiusers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(UserDTO userDTO) {
        User convertedUser = userMapper.toModel(userDTO);

        User createdUser = null;
        try {
        createdUser = userRepository.save(convertedUser);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return createdUser;
    }

    public List<UserDTO> getAll() {
        return this.userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
}
