package com.test.technicalMarcos.service;

import com.test.technicalMarcos.adapter.NumberValid;
import com.test.technicalMarcos.domain.ContactsDTO;
import com.test.technicalMarcos.domain.request.ContactBody;
import com.test.technicalMarcos.model.Contact;
import com.test.technicalMarcos.model.repository.IContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ContactService implements IContactService {

    @Autowired
    IContactRepository contactRepository;

    @Autowired
    NumberValid numberValid;

    @Override
    public void save(List<ContactBody> contacts, String userId) throws TelephoneServiceException {

        deleteContactsByUserId(userId);

        for (ContactBody newContact : contacts) {
            if (numberValid.isNumberValid(newContact.getPhone())) {
                Contact contact = new Contact();
                contact.setContactName(newContact.getContactName());
                contact.setTelephone(newContact.getPhone());
                contact.setUserId(userId);
                contactRepository.save(contact);
            } else {
                throw new TelephoneServiceException("Invalid phone: " + newContact.getPhone());
            }
        }
    }

    @Override
    public List<ContactsDTO> commonContacts(String user1, String user2) {

        List<Contact> contactsUser1 = contactRepository.findByUserId(user1);
        List<Contact> contactsUser2 = contactRepository.findByUserId(user2);

        List<Contact> common = contactsUser1.stream().filter(contact1 ->
                contactsUser2.stream().anyMatch(contact2 ->
                        contact1.getTelephone().equals(contact2.getTelephone()))).collect(Collectors.toList());

        return common.stream().map(contact -> {
            ContactsDTO contactDto = new ContactsDTO();
            contactDto.setContactName(contact.getContactName());
            contactDto.setPhone(contact.getTelephone());
            return contactDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ContactsDTO> getContactsByUser(String userId) {

        List<Contact> contactsUser = contactRepository.findByUserId(userId);

        return contactsUser.stream().map(contact -> {
            ContactsDTO contactDto = new ContactsDTO();
            contactDto.setContactName(contact.getContactName());
            contactDto.setPhone(contact.getTelephone());
            return contactDto;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteContactsByUserId(String userId) {

        List<Contact> contactsUser = contactRepository.findByUserId(userId);

        contactRepository.deleteAll(contactsUser);
    }
}
