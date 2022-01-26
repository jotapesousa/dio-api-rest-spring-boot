package br.org.jprojects.restapiusers.controllers;

import br.org.jprojects.restapiusers.dto.UserDTO;
import br.org.jprojects.restapiusers.entity.User;
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

    @GetMapping("hello")
    public String HelloTest() {
        return "Hello World!";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody @Valid UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @GetMapping("getAll")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<UserDTO> getAll(){
        return userService.getAll();
    }
}
