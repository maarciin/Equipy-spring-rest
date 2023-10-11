package pl.javastart.equipy;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


}
