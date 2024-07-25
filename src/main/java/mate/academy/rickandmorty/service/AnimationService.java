package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.OutputCharacterResponseDto;

public interface AnimationService {
    OutputCharacterResponseDto getRandomCharacter();

    List<OutputCharacterResponseDto> findCharactersByName(String characterName);
}
