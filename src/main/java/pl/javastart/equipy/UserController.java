package pl.javastart.equipy;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
class UserController {
    private final UserService userService;

    @GetMapping
    List<UserDto> findAll(@RequestParam(required = false) String lastName) {
        if (lastName == null) {
            return userService.findAll();
        } else {
            return userService.findAllByLastName(lastName);
        }
    }

    @PostMapping
    ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        //jeśli u userDto podany zostanie id, to wywalamy BAD_REQUEST
        if (userDto.getId() != null) {
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

    @GetMapping("/{id}")
    ResponseEntity<UserDto> findUser(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto user) {
        if (!id.equals(user.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Aktualizowany obiekt musi mieć id zgodne z id w ścieżce zasobu"
            );
        }
        UserDto updatedUser = userService.update(user);
        return ResponseEntity.ok(updatedUser);
    }

}
