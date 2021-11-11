package pl.patryklubik.myweight.logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import pl.patryklubik.myweight.logic.security.SecurityUserService;
import pl.patryklubik.myweight.model.PersonalData;
import pl.patryklubik.myweight.model.PersonalDataRepository;
import pl.patryklubik.myweight.model.Weight;
import pl.patryklubik.myweight.model.WeightRepository;
import pl.patryklubik.myweight.model.dto.BasicWeightDataDto;
import pl.patryklubik.myweight.model.security.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


/**
 * Create by Patryk Łubik on 11.11.2021.
 */

@ExtendWith(MockitoExtension.class)
class WeightServiceImplTest {

    @InjectMocks
    private WeightServiceImpl weightServiceImpl;

    @Mock
    private WeightRepository weightRepository;
    @Mock
    private PersonalDataRepository personalDataRepository;
    @Mock
    private SecurityUserService securityUserService;

    @Test
    void checkingIfWeightDataExistsWhenWeightDataExistsShouldReturnTrue() {

        //given
        List<Weight> weightList = new ArrayList<>();
        Weight weight = new Weight();
        weightList.add(weight);
        given(weightRepository.findByUser(any())).willReturn(weightList);

        //when
        boolean isWeightDataExists = weightServiceImpl.isWeightDataExists();

        //then
        assertThat(isWeightDataExists, is(true));
    }

    @Test
    void checkingIfWeightDataExistsWhenWeightDataNotExistsShouldReturnFalse() {

        //given
        List<Weight> weightList = new ArrayList<>();
        given(weightRepository.findByUser(any())).willReturn(weightList);

        //when
        boolean isWeightDataExists = weightServiceImpl.isWeightDataExists();

        //then
        assertThat(isWeightDataExists, is(false));
    }

    @Test
    void deleteWeightWhenWeightNotExistsShouldThrowResponseStatusException() {

        //given
        int weightId = 1;
        given(weightRepository.existsById(weightId)).willReturn(false);

        //then
        assertThrows(ResponseStatusException.class, ()->weightServiceImpl.delete(weightId));
    }

    @Test
    void deleteWeightWhenWeightExistsShouldDeleteWeightFromRepository() {

        //given
        int weightIdToDelete = 1;
        given(weightRepository.existsById(any())).willReturn(true);

        //when
        weightServiceImpl.delete(weightIdToDelete);

        //then
        verify(weightRepository, Mockito.times(1)).deleteById(weightIdToDelete);
    }

    @Test
    void saveMethodShouldSavedNewWeightInRepository() {

        //given
        given(securityUserService.getLoggedInUser()).willReturn(new User());
        Weight newWeight = new Weight();

        //when
        weightServiceImpl.save(newWeight);

        //then
        verify(weightRepository, Mockito.times(1)).save(newWeight);
    }

    @Test
    void updateWeightWhenWeightNotExistsShouldThrowResponseStatusException() {

        //given
        given(weightRepository.existsById(any())).willReturn(false);

        //then
        assertThrows(ResponseStatusException.class, ()->weightServiceImpl.update(new Weight()));
    }

    @Test
    void ifWeightExistsServiceShouldSearchItWhenWeightIsUpdated() {

        //given
        Weight weight = new Weight();
        weight.setId(1);
        given(weightRepository.existsById(any())).willReturn(true);

        //when
        weightServiceImpl.update(weight);

        //then
        verify(weightRepository, Mockito.times(1)).findById(weight.getId());
    }

    @Test
    void ifLoggedInUsersBMILevelIsCorrectServiceShouldReturnTrue() {

        //given
        PersonalData personalData = new PersonalData();
        personalData.setHeight(180);
        User user = new User(1, "username", "pass");

        given(securityUserService.getLoggedInUser()).willReturn(user);
        given(personalDataRepository.findByUser(any())).willReturn(personalData);
        given(weightRepository.getUserCurrentWeight(any())).willReturn(75F);

        //when
        boolean isLoggedInUsersBMILevelCorrect = weightServiceImpl.isLoggedInUsersBMILevelCorrect();

        //then
        assertThat(isLoggedInUsersBMILevelCorrect, is(true));
    }

    @Test
    void ifLoggedInUsersBMILevelIsNotCorrectServiceShouldReturnFalse() {

        //given
        PersonalData personalData = new PersonalData();
        personalData.setHeight(180);
        User user = new User(1, "username", "pass");

        given(securityUserService.getLoggedInUser()).willReturn(user);
        given(personalDataRepository.findByUser(any())).willReturn(personalData);
        given(weightRepository.getUserCurrentWeight(any())).willReturn(60F);

        //when
        boolean isLoggedInUsersBMILevelCorrect = weightServiceImpl.isLoggedInUsersBMILevelCorrect();

        //then
        assertThat(isLoggedInUsersBMILevelCorrect, is(false));
    }

    @Test
    void getWeightHistoryDataLoggedInUserShouldFindWeightHistoryByUser() {

        //when
        weightServiceImpl.getWeightHistoryDataLoggedInUser();

        //then
        verify(weightRepository, Mockito.times(1)).findByUser(any());
    }

    @Test
    void getBasicWeightDataLoggedInUserShouldReturnCorrectData() {

        //given
        PersonalData personalData = new PersonalData();
        personalData.setHeight(180);
        Date date = Date.from(Instant.parse("2021-10-01T00:00:00.000Z"));
        User user = new User(1, "username", "pass");
        float weight = 90F;
        given(securityUserService.getLoggedInUser()).willReturn(user);
        given(personalDataRepository.findByUser(user)).willReturn(personalData);
        given(weightRepository.getUserCurrentWeight(user.getId())).willReturn(weight);
        given(weightRepository.getUserMinWeight(user.getId())).willReturn(weight);
        given(weightRepository.getUserMaxWeight(user.getId())).willReturn(weight);
        given(weightRepository.getUserCurrentWeightDate(user.getId())).willReturn(date);
        given(weightRepository.getUserMinWeightDate(user.getId())).willReturn(date);
        given(weightRepository.getUserMaxWeightDate(user.getId())).willReturn(date);

        //when
        BasicWeightDataDto basicWeightDataDto = weightServiceImpl.getBasicWeightDataLoggedInUser();

        //then
        assertThat(basicWeightDataDto.getBmi(), equalTo(27.8F));
        assertThat(basicWeightDataDto.getCurrentWeight(), equalTo(weight));
        assertThat(basicWeightDataDto.getMaxWeight(), equalTo(weight));
        assertThat(basicWeightDataDto.getMinWeight(), equalTo(weight));
        assertThat(basicWeightDataDto.getCurrentWeightDate(), equalTo(date));
        assertThat(basicWeightDataDto.getMaxWeightDate(), equalTo(date));
        assertThat(basicWeightDataDto.getMinWeightDate(), equalTo(date));
    }
}