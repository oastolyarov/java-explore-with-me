package explore.service;

import explore.model.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    List<UserDto> getUsers();

    void deleteUserById(int id);
}
