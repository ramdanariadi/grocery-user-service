package id.grocery.tunas.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameIndexEntryDTO {
    @JsonProperty("game_index")
    private Integer gameIndex;
    
    private VersionRefDTO version;
}
