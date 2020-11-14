package com.test.technicalMarcos.service;

import com.test.technicalMarcos.adapter.NumberValid;
import com.test.technicalMarcos.domain.NewUserDTO;
import com.test.technicalMarcos.domain.UserDTO;
import com.test.technicalMarcos.model.User;
import com.test.technicalMarcos.model.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private NumberValid numberValid;

    @Override
    public UserDTO createUser(NewUserDTO newUserDTO) throws TelephoneServiceException {

        if (numberValid.isNumberValid(newUserDTO.getTelephone())) {
            User user = new User();
            user.setName(newUserDTO.getName());
            user.setLastName(newUserDTO.getLastName());
            user.setTelephone(newUserDTO.getTelephone());

            User userCreated = userRepository.save(user);

            UserDTO userDTO = new UserDTO();
            userDTO.setName(userCreated.getName());
            userDTO.setLastName(userCreated.getLastName());
            userDTO.setTelephone(userCreated.getTelephone());
            userDTO.setId(userCreated.getId());

            return userDTO;
        } else {
            throw new TelephoneServiceException("Invalid phone: " + newUserDTO.getTelephone());
        }
    }
}
