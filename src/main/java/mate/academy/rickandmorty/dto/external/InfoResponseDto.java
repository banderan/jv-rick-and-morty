package mate.academy.rickandmorty.dto.external;

import lombok.Data;

import java.util.List;

@Data
public class InfoResponseDto {
    private InfoPages info;
    private List<CharacterResponseDto> results;
}
