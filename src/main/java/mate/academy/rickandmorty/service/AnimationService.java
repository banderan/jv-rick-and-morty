package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.internal.OutputCharacterResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnimationService {
    OutputCharacterResponseDto getRandomCharacter();

    public List<OutputCharacterResponseDto> findCharactersByName(String characterName);
}
