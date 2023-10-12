package pl.javastart.equipy.components.assignment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Optional<Assignment> findByAsset_IdAndEndIsNull(Long assetId);
}
