package pl.patryklubik.myweight.logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.patryklubik.myweight.logic.security.SecurityUserService;
import pl.patryklubik.myweight.model.PersonalData;
import pl.patryklubik.myweight.model.PersonalDataRepository;
import pl.patryklubik.myweight.model.security.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


/**
 * Create by Patryk Łubik on 11.11.2021.
 */

@ExtendWith(MockitoExtension.class)
class PersonalDataServiceImplTest {

    @InjectMocks
    private PersonalDataServiceImpl personalDataServiceImpl;

    @Mock
    private PersonalDataRepository personalDataRepository;
    @Mock
    private SecurityUserService securityUserService;


    @Test
    void checkingIfPersonalDataExistsWhenPersonalDataExistsShouldReturnTrue() {

        //given
        PersonalData personalData = new PersonalData();
        given(personalDataRepository.findByUser(any())).willReturn(personalData);

        //when
        boolean isPersonalDataExists = personalDataServiceImpl.isPersonalDataExists();

        //then
        assertThat(isPersonalDataExists, is(true));
    }

    @Test
    void checkingIfPersonalDataExistsWhenPersonalDataNotExistsShouldReturnFalse() {

        //given
        given(personalDataRepository.findByUser(any())).willReturn(null);

        //when
        boolean isPersonalDataExists = personalDataServiceImpl.isPersonalDataExists();

        //then
        assertThat(isPersonalDataExists, is(false));
    }

    @Test
    void getPersonalDataLoggedInUserShouldSearchPersonalDataLoggedInUserInRepository() {

        //given
        User user = new User("username", "pass");
        given(securityUserService.getLoggedInUser()).willReturn(user);

        //when
        personalDataServiceImpl.getPersonalDataLoggedInUser();

        //then
        verify(personalDataRepository, Mockito.times(1)).findByUser(user);
    }

    @Test
    void ifPersonalDataExistsServiceShouldSearchItWhenDataIsUpdated() {

        //given
        PersonalData personalData = new PersonalData();
        given(personalDataRepository.findByUser(any())).willReturn(personalData);

        //when
        personalDataServiceImpl.save(personalData);

        //then
        verify(personalDataRepository, Mockito.times(1)).findById(personalData.getId());
    }

    @Test
    void ifPersonalDataNotExistsServiceShouldSaveItWhenDataIsSaved() {

        //given
        PersonalData personalData = new PersonalData();
        given(personalDataRepository.findByUser(any())).willReturn(null);

        //when
        personalDataServiceImpl.save(personalData);

        //then
        verify(personalDataRepository, Mockito.times(1)).save(personalData);
    }
}