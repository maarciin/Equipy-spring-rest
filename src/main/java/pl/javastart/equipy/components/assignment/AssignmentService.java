package pl.javastart.equipy.components.assignment;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javastart.equipy.components.inventory.asset.Asset;
import pl.javastart.equipy.components.inventory.asset.AssetRepository;
import pl.javastart.equipy.components.user.User;
import pl.javastart.equipy.components.user.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
class AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    AssignmentDto createAssignment(AssignmentDto assignmentDto) {
        if(assignmentDto.getUserId() == null) {
            throw new InvalidAssignmentExcpetion("UserId nie może być nullem");
        }
        if(assignmentDto.getAssetId() == null) {
            throw new InvalidAssignmentExcpetion("AssetId nie moze być nullem");
        }
        Optional<Assignment> activeAssignmentForAsset =
                assignmentRepository.findByAsset_IdAndEndIsNull(assignmentDto.getAssetId());
        activeAssignmentForAsset.ifPresent(assignment -> {
            throw new InvalidAssignmentExcpetion("To wyposażenie jest aktualnie do kogoś przypisane");
        });
        Optional<User> user = userRepository.findById(assignmentDto.getUserId());
        Optional<Asset> asset = assetRepository.findById(assignmentDto.getAssetId());
        Assignment assignment = new Assignment();
        Long userId = assignmentDto.getUserId();
        Long assetId = assignmentDto.getAssetId();
        assignment.setUser(user.orElseThrow(() -> new InvalidAssignmentExcpetion("Brak użytkownika z id: " + userId)));
        assignment.setAsset(asset.orElseThrow(() -> new InvalidAssignmentExcpetion("Brak wyposażenia z id: " + assetId)));
        assignment.setStart(LocalDateTime.now());
        Assignment savedAssignment = assignmentRepository.save(assignment);
        return AssignmentMapper.toDto(savedAssignment);
    }


    @Transactional
    public LocalDateTime endAssignment(Long assignmentId) {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        Assignment assignmentEntity = assignment.orElseThrow(AssignmentNotFoundException::new);
        if(assignmentEntity.getEnd() != null) {
            throw new AssignmentAlreadyFinishedException();
        } else {
            assignmentEntity.setEnd(LocalDateTime.now());
        }
        return assignmentEntity.getEnd();
    }
}
