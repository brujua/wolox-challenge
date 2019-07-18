package com.wolox.challenge.service.external;

import com.wolox.challenge.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ExternalUserServiceTest {

    @Autowired
    private ExternalUserService serv;

    @Test
    public void findById() {
        User user = serv.findById(1L);
        assertThat(user.getId()).isEqualTo(1L);
    }

    @Test
    public void findAll() {
        List<User> users = serv.findAll();
        assertThat(users.size()).isGreaterThan(0);
    }
}