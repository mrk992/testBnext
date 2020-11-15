package com.test.technicalMarcos;

import com.test.technicalMarcos.adapter.NumberValid;
import com.test.technicalMarcos.domain.NewUserDTO;
import com.test.technicalMarcos.domain.UserDTO;
import com.test.technicalMarcos.model.User;
import com.test.technicalMarcos.model.repository.IUserRepository;
import com.test.technicalMarcos.service.TelephoneServiceException;
import com.test.technicalMarcos.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ContactServiceTest {

    @MockBean
    private IUserRepository userRepository;

    @MockBean
    private NumberValid numberValid;

    @InjectMocks
    private UserService userService;

    @Test
    @Disabled
    public void test_Create_User_Number_Valid() throws TelephoneServiceException {

        String name = "name1";
        String lastName = "lastName1";
        String telephone = "666666666";

        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setName(name);
        newUserDTO.setLastName(lastName);
        newUserDTO.setTelephone(telephone);

        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setTelephone(telephone);

        User userCreated = new User();
        userCreated.setName(name);
        userCreated.setLastName(lastName);
        userCreated.setTelephone(telephone);

        Mockito.when(userRepository.save(user)).thenReturn(userCreated);
        Mockito.when(numberValid.isNumberValid(newUserDTO.getTelephone())).thenReturn(true);

        UserDTO result = userService.createUser(newUserDTO);

        assertThat(result, is(userCreated));
    }
}