package pl.javastart.equipy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class UserService {

    private final UserRepository userRepository;

    List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDtoMapper::toUserDto)
                .collect(Collectors.toList());
    }

    List<UserDto> findAllByLastName(String lastName) {
        return userRepository.findAllByLastNameContainingIgnoreCase(lastName)
                .stream()
                .map(UserDtoMapper::toUserDto)
                .collect(Collectors.toList());
    }
}
