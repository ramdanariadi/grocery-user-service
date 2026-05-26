package id.grocery.tunas.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbilityEntryDTO {
    private AbilityDTO ability;
    
    @JsonProperty("is_hidden")
    private Boolean isHidden;
    
    private Integer slot;
}
