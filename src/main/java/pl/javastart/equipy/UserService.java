package pl.javastart.equipy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    UserDto save(UserDto userdto) {
        //sprawdzamy czy użytkownik o podanym w userDto peselu już istnieje
        Optional<User> userByPesel = userRepository.findByPesel(userdto.getPesel());
        //jeśli użytkownik z danym pesel już istnieje to wyrzucamy wyjątek DuplicatePeselException
        userByPesel.ifPresent(u -> {
            throw new DuplicatePeselException();
        });
        //jeśli nie istnieje, zamieniamy dto na encje i zapisujemy za pomocą userRepository.save(user)
        return mapAndSaveUser(userdto);
    }

    Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(UserDtoMapper::toUserDto);
    }

    UserDto update(UserDto userDto) {
        Optional<User> userByPesel = userRepository.findByPesel(userDto.getPesel());
        userByPesel.ifPresent(user -> {
            if(user.getId().equals(userDto.getId())) {
                throw new DuplicatePeselException();
            }
        });
        return mapAndSaveUser(userDto);
    }

    private UserDto mapAndSaveUser(UserDto userDto) {
        User userEntity = UserDtoMapper.toUser(userDto);
        User savedUser = userRepository.save(userEntity);
        return UserDtoMapper.toUserDto(savedUser);
    }
}
