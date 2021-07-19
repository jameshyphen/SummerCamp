package controllers;

import domain.*;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SummerCampController {
	
	@GetMapping("/summercamp")
	public String showIndex(Model model) {
		model.addAttribute("test1", "testaaa");
		return "index";
	}
	

	@PostMapping("/summercamp")
	public String submitPostalCode(@RequestParam String postalCode, Model model) {
		model.addAttribute("test2", postalCode);
		if (postalCode == null || postalCode.toString().length() == 0) {
			model.setAttribute("postalCodeError", getPostValidationError(request.getLocale(), "postNoValue"));
		} else if (!postalCode.toString().matches("[0-9]+")) {
			model.setAttribute("postalCodeError", getPostValidationError(request.getLocale(), "postNoNumber"));
		} else if (Integer.parseInt(postalCode)<1000) {
			model.setAttribute("postalCodeError", getPostValidationError(request.getLocale(), "postBelowMin"));
		} else if (Integer.parseInt(postalCode)>9990) {
			model.setAttribute("postalCodeError", getPostValidationError(request.getLocale(), "postAboveMax"));
		} else {
			// No errors
			int postalCode = Integer.parseInt(postalCode);
			List<Camp> camps = getCampsByPostalCode(postalCode);
			model.setAttribute("campsCloseby", camps);
		}

		return showIndex(model);
	}

}
