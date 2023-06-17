package exercise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import netscape.javascript.JSObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {


    private final JdbcTemplate jdbc;


    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping(produces = "application/json")
    public String getAllPersons() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String query = "SELECT * FROM person";
        List<Map<String, Object>> results = jdbc.queryForList(query);

        return objectMapper.writeValueAsString(results);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public String getPerson(@PathVariable Long id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String sql = "SELECT * FROM person WHERE id = ?";
        Map<String, Object> user = jdbc.queryForMap(sql, id);
        return objectMapper.writeValueAsString(user);
    }
    // END
}
