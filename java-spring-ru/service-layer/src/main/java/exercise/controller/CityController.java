package exercise.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.dto.SearchDto;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;

    private final WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> getWeather(@PathVariable(name = "id") long cityId) throws JsonProcessingException {
        return weatherService.getWeather(cityId);
    }

    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SearchDto> searchCities(@RequestParam(required = false, name = "name") String partCityName) throws JsonProcessingException {
        return weatherService.searchCities(partCityName);
    }
    // END
}

