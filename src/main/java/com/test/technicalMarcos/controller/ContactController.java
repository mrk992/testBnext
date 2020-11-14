package com.test.technicalMarcos.controller;

import com.test.technicalMarcos.domain.ContactsDTO;
import com.test.technicalMarcos.domain.request.ContactsBody;
import com.test.technicalMarcos.service.IContactService;
import com.test.technicalMarcos.service.TelephoneServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    IContactService contactService;

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody ContactsBody contactsBody) {
        try {
            contactService.save(contactsBody.getContacts(), contactsBody.getUserId());
        } catch (TelephoneServiceException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/common", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity commonContacts(@RequestParam String user1, @RequestParam String user2) {

        List<ContactsDTO> commonContacts = contactService.commonContacts(user1, user2);

        if (commonContacts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(commonContacts, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/mycontacts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getMyContacts(@RequestParam String id) {

        List<ContactsDTO> contacts = contactService.getContactsByUser(id);

        if (contacts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        }
    }

}
