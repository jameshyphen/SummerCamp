package domain.services;

import domain.Camp;

import java.util.List;
import java.util.Locale;

public interface PostalCodeService {
	public String getPostValidationError(Locale curLoc, String key);
	
	public List<Camp> getCampsByPostalCode(int postalCode);
}
