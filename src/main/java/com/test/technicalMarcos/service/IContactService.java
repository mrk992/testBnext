package com.test.technicalMarcos.service;

import com.test.technicalMarcos.domain.ContactsDTO;
import com.test.technicalMarcos.domain.request.ContactBody;

import java.util.List;

public interface IContactService {

    /**
     * @param contacts
     */
    void save(List<ContactBody> contacts, String userId) throws TelephoneServiceException;

    /**
     * @param user1
     * @param user2
     * @return
     */
    List<ContactsDTO> commonContacts(String user1, String user2);

    /**
     * @param userId
     * @return
     */
    List<ContactsDTO> getContactsByUser(String userId);

    /**
     * @param userId
     */
    void deleteContactsByUserId(String userId);
}
