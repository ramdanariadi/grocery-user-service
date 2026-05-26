package id.grocery.tunas.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeldItemDTO {
    private ItemDTO item;
    
    @JsonProperty("version_details")
    private List<VersionDetailDTO> versionDetails;
}
