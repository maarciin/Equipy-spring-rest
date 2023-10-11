package pl.javastart.equipy;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface UserRepository extends JpaRepository<User, Integer> {

    public List<User> findAllByLastNameContainingIgnoreCase(String lastName);
}
