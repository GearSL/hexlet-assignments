package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO createContact(@RequestBody ContactCreateDTO contactCreateDTO) {
        Contact contact = new Contact();
        contact.setFirstName(contactCreateDTO.getFirstName());
        contact.setLastName(contactCreateDTO.getLastName());
        contact.setPhone(contactCreateDTO.getPhone());

        Contact savedContact = contactRepository.save(contact);

        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(savedContact.getId());
        contactDTO.setFirstName(savedContact.getFirstName());
        contactDTO.setLastName(savedContact.getLastName());
        contactDTO.setPhone(savedContact.getPhone());
        contactDTO.setCreatedAt(savedContact.getCreatedAt());
        contactDTO.setUpdatedAt(savedContact.getUpdatedAt());

        return contactDTO;
    }
    // END
}
