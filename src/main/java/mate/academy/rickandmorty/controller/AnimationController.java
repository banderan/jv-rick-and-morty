package mate.academy.rickandmorty.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.OutputCharacterResponseDto;
import mate.academy.rickandmorty.service.AnimationService;
import mate.academy.rickandmorty.service.watcher.AnimationWatcher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
