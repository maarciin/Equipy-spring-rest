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
        User user = UserDtoMapper.toUser(userdto);
        User savedUser = userRepository.save(user);
        //po zapisie savedUser ma już id i znów go mapujemy na userDto i zwracamy
        return UserDtoMapper.toUserDto(savedUser);
    }

    Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(UserDtoMapper::toUserDto);
    }
}
