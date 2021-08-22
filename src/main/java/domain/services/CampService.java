package domain.services;

import java.util.List;

import domain.Camp;
import domain.Person;

public interface CampService {
	Camp findCamp(int id);
    List<Camp> findCamps(int postalCode);
    Person addKid(int campId, String name, int code1, int code2);
}
