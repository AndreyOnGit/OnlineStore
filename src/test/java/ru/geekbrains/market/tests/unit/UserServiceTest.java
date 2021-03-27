package ru.geekbrains.market.tests.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.market.models.User;
import ru.geekbrains.market.repositories.UserRepository;
import ru.geekbrains.market.services.UserService;

import java.util.Optional;

@SpringBootTest(classes = UserService.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testFindByUsername(){
        User demoUser = new User ("Smn", "pass");

        Mockito.doReturn(Optional.of(demoUser)).when(userRepository).findByUsername("Smn");

        Assertions.assertEquals("Smn", userService.findByUsername("Smn").get().getUsername());
        Assertions.assertEquals(true, userService.findByUsername("nn").isEmpty());
    }
}
