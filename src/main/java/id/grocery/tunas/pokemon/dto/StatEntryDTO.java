package id.grocery.tunas.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatEntryDTO {
    @JsonProperty("base_stat")
    private Integer baseStat;
    
    private Integer effort;
    private StatDTO stat;
}
