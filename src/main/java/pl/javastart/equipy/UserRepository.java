package pl.javastart.equipy;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByLastNameContainingIgnoreCase(String lastName);
    Optional<User> findByPesel(String pesel);

}
