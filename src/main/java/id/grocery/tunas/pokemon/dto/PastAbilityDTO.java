package id.grocery.tunas.pokemon.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PastAbilityDTO {
    private List<AbilityEntryDTO> abilities;
    private GenerationDTO generation;
}
