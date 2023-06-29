package exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.HttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.dto.SearchDto;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;
    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public Map<String, String> getWeather(long cityId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Optional<City> city = cityRepository.findById(cityId);
        String result;

        if (city.isPresent()) {
            result = client.get("http://weather/api/v2/cities/" + city.get().getName());
        } else {
            throw new CityNotFoundException("City not found");
        }
        return objectMapper.readValue(result, Map.class);
    }

    public List<SearchDto> searchCities(String partCityName) throws JsonProcessingException {
        List<City> cities;
        List<SearchDto> searchDtoList = new ArrayList<>();
        if (partCityName != null) {
            cities = cityRepository.findByNameStartingWithIgnoreCase(partCityName);
        } else {
            cities = cityRepository.OrderByName();
        }
        for (City city : cities) {
            SearchDto searchDto = new SearchDto();
            int temperature = Integer.parseInt(getWeather(city.getId()).get("temperature"));
            searchDto.setName(city.getName());
            searchDto.setTemperature(temperature);
            searchDtoList.add(searchDto);
        }
        return searchDtoList;
    }
    // END
}
