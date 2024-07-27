package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.OutputCharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.watcher.CharacterClient;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterClient characterClient;
    private final CharacterMapper characterMapper;
    private List<CharacterResponseDto> characterList;

    @PostConstruct
    public void init() {
        characterList = characterClient.getListOfAllCharacters();
    }

    @Override
    public OutputCharacterResponseDto findCharacterByRandomId() {
        Character model = characterMapper.toModel(takeRandomCharacter());
        return characterMapper.toDto(characterRepository.save(model));
    }

    private CharacterResponseDto takeRandomCharacter() {
        Random random = new Random();
        int randomId = random.nextInt(characterList.size());
        return characterList.get(randomId);
    }

    @Override
    public List<OutputCharacterResponseDto> findCharactersByName(String characterName) {
        return characterRepository
                .findAllByNameContaining(characterName)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
