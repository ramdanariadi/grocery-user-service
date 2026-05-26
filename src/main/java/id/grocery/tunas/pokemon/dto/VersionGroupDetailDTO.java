package id.grocery.tunas.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionGroupDetailDTO {
    @JsonProperty("level_learned_at")
    private Integer levelLearnedAt;
    
    @JsonProperty("move_learn_method")
    private MoveLearnMethodDTO moveLearnMethod;
    
    private Object order;
    
    @JsonProperty("version_group")
    private VersionRefDTO versionGroup;
}
