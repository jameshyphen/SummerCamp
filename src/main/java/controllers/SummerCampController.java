package controllers;

import domain.*;
import domain.services.PostalCodeService;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SummerCampController {
	
	@Autowired
	private PostalCodeService postalCodeService;
	
	@GetMapping("/summercamp")
	public String showIndex(Model model) {
		model.addAttribute("test1", "testaaa");
		return "index";
	}
	

	@PostMapping("/summercamp")
	public String submitPostalCode(@RequestParam String postalCode, Model model, Locale loc) {
		model.addAttribute("test2", postalCode);
		if (postalCode == null || postalCode.toString().length() == 0) {
			model.addAttribute("postalCodeError", postalCodeService.getPostValidationError(loc, "postNoValue"));
		} else if (!postalCode.toString().matches("[0-9]+")) {
			model.addAttribute("postalCodeError", postalCodeService.getPostValidationError(loc, "postNoNumber"));
		} else if (Integer.parseInt(postalCode)<1000) {
			model.addAttribute("postalCodeError", postalCodeService.getPostValidationError(loc, "postBelowMin"));
		} else if (Integer.parseInt(postalCode)>9990) {
			model.addAttribute("postalCodeError", postalCodeService.getPostValidationError(loc, "postAboveMax"));
		} else {
			// No errors
			List<Camp> camps = postalCodeService.getCampsByPostalCode(Integer.parseInt(postalCode));
			model.addAttribute("campsCloseby", camps);
		}

		return showIndex(model);
	}
	
//	
//	@PostMapping("/camptest")
//	@ResponseBody
//	public Object postCamp() {
//		Camp camp = new Camp(1, "test", 9000, 5);
//		return camp;
//	}

}
