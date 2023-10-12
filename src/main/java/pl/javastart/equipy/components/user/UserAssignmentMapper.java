package pl.javastart.equipy.components.user;

import pl.javastart.equipy.components.assignment.Assignment;
import pl.javastart.equipy.components.inventory.asset.Asset;

class UserAssignmentMapper {
    static UserAssignmentDto toDto(Assignment assignment) {
        UserAssignmentDto dto = new UserAssignmentDto();
        dto.setId(assignment.getId());
        dto.setStart(assignment.getStart());
        dto.setEnd(assignment.getEnd());
        Asset asset = assignment.getAsset();
        dto.setAssetId(assignment.getId());
        dto.setAssetName(asset.getName());
        dto.setAssetSerialNumber(asset.getSerialNumber());
        return dto;
    }
}
