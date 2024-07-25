package mate.academy.rickandmorty.service.watcher;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.InfoResponseDto;
import mate.academy.rickandmorty.exception.AnimationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AnimationWatcher {
    private final ObjectMapper objectMapper;
    @Value("${mate.academy.animation.outer.db}")
    private String BASE_URL;

    public List<CharacterResponseDto> getCharacter() {
        HttpClient httpClient = HttpClient.newHttpClient();
        List<CharacterResponseDto> allCharacters = new ArrayList<>();
        String url = BASE_URL;
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        InfoResponseDto infoResponseDto = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            infoResponseDto = objectMapper.readValue(response.body(), InfoResponseDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        String nextUrl = infoResponseDto.getInfo().next();

        try {
            while (nextUrl != null) {
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(nextUrl))
                        .build();

                response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                infoResponseDto = objectMapper.readValue(response.body(), InfoResponseDto.class);

                allCharacters.addAll(infoResponseDto.getResults());
                nextUrl = infoResponseDto.getInfo().next();
            }
            return allCharacters;
        } catch (IOException | InterruptedException e) {
            throw new AnimationException(
                    "Error while waiting for character", e);
        }
    }
}
