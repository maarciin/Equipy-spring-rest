package pl.javastart.equipy.components.assignment;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/api/assignments")
class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping
    ResponseEntity<AssignmentDto> saveAssignment(@RequestBody AssignmentDto assignmentDto) {
        AssignmentDto savedAssignment;
        try {
            savedAssignment = assignmentService.createAssignment(assignmentDto);
        } catch (InvalidAssignmentExcpetion e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAssignment.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/{id}/end")
    ResponseEntity<?> endAssignment(@PathVariable Long id) {
        LocalDateTime endDate = assignmentService.endAssignment(id);
        return ResponseEntity.accepted().body(endDate);
    }
}
