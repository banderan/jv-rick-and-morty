package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.OutputCharacterResponseDto;
import mate.academy.rickandmorty.service.AnimationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/animation")
public class AnimationController {
    private final AnimationService animationService;

    @GetMapping
    public OutputCharacterResponseDto get() {
        return animationService.getRandomCharacter();
    }

    @GetMapping("/search")
    public List<OutputCharacterResponseDto> getWithLettersInName(
            @RequestParam String name) {
        return animationService.findCharactersByName(name);
    }
}
