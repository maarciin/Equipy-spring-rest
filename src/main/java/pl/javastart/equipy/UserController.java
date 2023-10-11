package pl.javastart.equipy;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
class UserController {
    private final UserService userService;

    @GetMapping
    List<UserDto> findAll(@RequestParam(required = false) String lastName) {
        if(lastName == null) {
            return userService.findAll();
        } else {
            return userService.findAllByLastName(lastName);
        }
    }

    @PostMapping
    ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        //jeśli u userDto podany zostanie id, to wywalamy BAD_REQUEST
        if(userDto.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Zapisywany obiekt nie może mieć ustawionego id"
            );
        }
        UserDto savedUser = userService.save(userDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        //ResponseEntity.created() oczekuje location, więc powyżej tworzymy taki URI
        return ResponseEntity.created(location).build();
    }


}
