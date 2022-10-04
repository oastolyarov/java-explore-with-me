package explore.controller;

import explore.model.dto.UserDto;
import explore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        log.debug("Входящий запрос на создание нового пользователя.");
        return userService.createUser(userDto);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        log.debug("Запрос на получение списка пользователей.");
        return userService.getUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        log.debug("Запрос на удаление пользователя по id = " + id);
        userService.deleteUserById(id);
    }

}
