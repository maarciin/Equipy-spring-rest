package pl.javastart.equipy.components.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class UserAssignmentDto {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Long assetId;
    private String assetName;
    private String assetSerialNumber;
}
