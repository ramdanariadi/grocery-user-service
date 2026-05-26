package id.grocery.tunas.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoveDTO {
    private MoveNameDTO move;
    
    @JsonProperty("version_group_details")
    private List<VersionGroupDetailDTO> versionGroupDetails;
}
