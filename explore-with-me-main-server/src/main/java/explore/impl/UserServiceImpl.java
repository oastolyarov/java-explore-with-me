package explore.impl;

import explore.mapper.UserMapper;
import explore.model.User;
import explore.model.dto.UserDto;
import explore.repository.UserRepository;
import explore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setId(0);
        User user = userRepository.save(UserMapper.toUser(userDto));

        return UserMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
