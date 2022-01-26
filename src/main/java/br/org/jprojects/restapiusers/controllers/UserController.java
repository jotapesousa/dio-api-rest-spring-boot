package br.org.jprojects.restapiusers.controllers;

import br.org.jprojects.restapiusers.dto.UserDTO;
import br.org.jprojects.restapiusers.entity.User;
import br.org.jprojects.restapiusers.exceptions.UserNotFoundException;
import br.org.jprojects.restapiusers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String HelloTest() {
        return "Hello World!";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody @Valid UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<UserDTO> getAll(){
        return userService.getAll();
    }
    
    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id) throws UserNotFoundException {
        return this.userService.findById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws UserNotFoundException {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public UserDTO updateById(@PathVariable Long id, @RequestBody UserDTO userDTO) throws UserNotFoundException {
        return userService.updateById(id, userDTO);
    }
}
