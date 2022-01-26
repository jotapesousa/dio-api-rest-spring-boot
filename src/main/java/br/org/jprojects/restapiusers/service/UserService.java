package br.org.jprojects.restapiusers.service;

import br.org.jprojects.restapiusers.dto.UserDTO;
import br.org.jprojects.restapiusers.entity.User;
import br.org.jprojects.restapiusers.exceptions.UserNotFoundException;
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
        return userRepository.save(convertedUser);
    }

    public List<UserDTO> getAll() {
        return this.userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findById(Long id) throws UserNotFoundException {
        User user = validateUser(id);
        return userMapper.toDTO(user);
    }

    public void delete(Long id) throws UserNotFoundException {
        validateUser(id);
        userRepository.deleteById(id);
    }

    public UserDTO updateById(Long id, UserDTO userDTO) throws UserNotFoundException {
        validateUser(id);
        User updatedUser = userMapper.toModel(userDTO);
        return userMapper.toDTO(userRepository.save(updatedUser));
    }

    private User validateUser(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
