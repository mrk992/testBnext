package com.test.technicalMarcos.service;

import com.test.technicalMarcos.domain.NewUserDTO;
import com.test.technicalMarcos.domain.UserDTO;

public interface IUserService {

    /**
     * @param newUserDTO
     * @return
     */
    UserDTO createUser(NewUserDTO newUserDTO) throws TelephoneServiceException;
}
