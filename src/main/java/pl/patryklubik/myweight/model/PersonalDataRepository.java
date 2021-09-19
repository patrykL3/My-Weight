package pl.patryklubik.myweight.model;


public interface PersonalDataRepository {

    PersonalData findByUser(User user);
}
