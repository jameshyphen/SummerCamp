package domain.services;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import domain.Camp;

public class PostServiceImpl implements PostService{
	private SummerCampServiceImpl summerCampService;
	
	public PostServiceImpl() {
		summerCampService = new SummerCampServiceImpl();
	}

	@Override
    public String getPostValidationError(Locale curLoc, String key) {
		ResourceBundle exceptions = ResourceBundle.getBundle("resources/exceptions", curLoc);
		String postValErr = exceptions.getString(key);
		return postValErr;
	}
	
	@Override
	public List<Camp> getCampsByPostalCode(int postalCode){
		return summerCampService.findCamps(postalCode);   	
	}
	

}
