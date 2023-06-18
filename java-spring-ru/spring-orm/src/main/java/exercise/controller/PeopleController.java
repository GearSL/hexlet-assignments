package exercise.controller;

import exercise.model.Person;
import exercise.dto.PersonDto;
import exercise.repository.PersonRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    // Автоматически заполняем значение поля
    private final PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person getPerson(@PathVariable long id) {
        return personRepository.findById(id).orElseThrow();
    }

    @GetMapping(path = "")
    public List<PersonDto> getPeople() {
        List<PersonDto> personsDtoList = new ArrayList<>();
        Iterable<Person> persons = this.personRepository.findAll();

        persons.forEach(person -> {
            PersonDto personDto = new PersonDto();
            personDto.setFirstName(person.getFirstName());
            personDto.setLastName(person.getLastName());
            personsDtoList.add(personDto);
        });

        return personsDtoList;
    }

    // BEGIN
    @PostMapping
    public void createPerson(@RequestBody PersonDto personDto) {
        Person person = new Person();
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        personRepository.save(person);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
    }

    @PatchMapping(path = "/{id}")
    public void patchPerson(@PathVariable Long id, @RequestBody PersonDto updatedPerson) {
        Person person = new Person();
        person.setFirstName(updatedPerson.getFirstName());
        person.setLastName(updatedPerson.getLastName());
        person.setId(id);
        personRepository.save(person);
    }
    // END
}
