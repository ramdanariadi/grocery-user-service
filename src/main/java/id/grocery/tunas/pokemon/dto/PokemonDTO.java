package id.grocery.tunas.pokemon.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonDTO {
    private List<AbilityEntryDTO> abilities;
    
    @JsonProperty("base_experience")
    private Integer baseExperience;
    
    private CriesDTO cries;
    private List<FormDTO> forms;
    
    @JsonProperty("game_indices")
    private List<GameIndexEntryDTO> gameIndices;
    
    private Integer height;
    
    @JsonProperty("held_items")
    private List<HeldItemDTO> heldItems;
    
    private Integer id;
    
    @JsonProperty("is_default")
    private Boolean isDefault;
    
    @JsonProperty("location_area_encounters")
    private String locationAreaEncounters;
    
    private List<MoveDTO> moves;
    private String name;
    private Integer order;
    
    @JsonProperty("past_abilities")
    private List<PastAbilityDTO> pastAbilities;
    
    @JsonProperty("past_stats")
    private List<PastStatDTO> pastStats;
    
    @JsonProperty("past_types")
    private List<Object> pastTypes;
    
    private SpeciesDTO species;
    private SpritesDTO sprites;
    private List<StatEntryDTO> stats;
    private List<TypeEntryDTO> types;
    private Integer weight;
}
