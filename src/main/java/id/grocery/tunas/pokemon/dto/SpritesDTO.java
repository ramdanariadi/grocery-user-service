package id.grocery.tunas.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpritesDTO {
    @JsonProperty("back_default")
    private String backDefault;
    
    @JsonProperty("back_female")
    private String backFemale;
    
    @JsonProperty("back_shiny")
    private String backShiny;
    
    @JsonProperty("back_shiny_female")
    private String backShinyFemale;
    
    @JsonProperty("front_default")
    private String frontDefault;
    
    @JsonProperty("front_female")
    private String frontFemale;
    
    @JsonProperty("front_shiny")
    private String frontShiny;
    
    @JsonProperty("front_shiny_female")
    private String frontShinyFemale;
    
    private OtherSpritesDTO other;
    
    private Map<String, Object> versions;
}
