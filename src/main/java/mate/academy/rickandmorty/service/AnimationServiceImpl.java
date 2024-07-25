package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.OutputCharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.AnimationRepository;
import mate.academy.rickandmorty.service.watcher.AnimationWatcher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnimationServiceImpl implements AnimationService {
    private final AnimationRepository animationRepository;
    private final AnimationWatcher animationWatcher;
    private final CharacterMapper characterMapper;

    @Override
    public OutputCharacterResponseDto getRandomCharacter() {
        List<CharacterResponseDto> character = animationWatcher.getCharacter();
        CharacterResponseDto characterResponseDto = takeRandomCharacter(character);
        Character model = characterMapper.toModel(characterResponseDto);
        return characterMapper.toDto(animationRepository.save(model));
    }

    private CharacterResponseDto takeRandomCharacter(List<CharacterResponseDto> character) {
        Random random = new Random();
        int randomId = random.nextInt(character.size());
        return character.get(randomId);
    }

    @Override
    public List<OutputCharacterResponseDto> findCharactersByName(String characterName) {
        return animationRepository.findAllByNameContaining(characterName)
                .stream().map(characterMapper::toDto).toList();
    }
}
