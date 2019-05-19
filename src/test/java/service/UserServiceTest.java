package service;

import dao.HibernateUserDao;
import entity.User;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EmailValidator.class)
public class UserServiceTest {

    private HibernateUserDao hibernateUserDao = PowerMockito.mock(HibernateUserDao.class);
    private MainService mainService = PowerMockito.mock(MainService.class);


    @Mock
    private EmailValidator emailValidator;

    @InjectMocks
    UserService userService = new UserService(hibernateUserDao, mainService);

    @Before
    public void before() {
        mockStatic(EmailValidator.class);
        PowerMockito.when(EmailValidator.getInstance()).thenReturn(emailValidator);
    }

    @Test
    public void isFailAddUser() {
        PowerMockito.when(emailValidator.isValid(anyString())).thenReturn(false);
        boolean t = userService.isAddUser("login", "password", "name", "lastname", "email");
        assertFalse(t);
        verify(hibernateUserDao, never()).add(any(User.class));
    }
}
