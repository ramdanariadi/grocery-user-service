package id.grocery.tunas.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtherSpritesDTO {
    @JsonProperty("dream_world")
    private DreamWorldDTO dreamWorld;
    
    private HomeSpritesDTO home;
    
    @JsonProperty("official-artwork")
    private OfficialArtworkDTO officialArtwork;
    
    private ShowdownSpritesDTO showdown;
}
