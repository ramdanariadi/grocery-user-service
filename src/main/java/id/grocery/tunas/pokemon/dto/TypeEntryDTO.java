package id.grocery.tunas.pokemon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeEntryDTO {
    private Integer slot;
    private TypeDTO type;
}
