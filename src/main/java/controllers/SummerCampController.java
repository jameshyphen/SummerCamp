package controllers;

import domain.*;
import domain.services.AuthorizationService;
import domain.services.CampService;
import domain.services.PostalCodeService;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/summercamp")
public class SummerCampController {
	
	public void setPostalCodeServiceMock(PostalCodeService service) {
		this.postalCodeService = service;
	}

	public void setCampServiceMock(CampService service) {
		this.campService = service;
	}
	

	public void setAuthorizationMock(AuthorizationService service) {
		this.authorizationService = service;
	}


	@Autowired
	private AuthorizationService authorizationService;

	@Autowired
	private PostalCodeService postalCodeService;

	@Autowired
	private CampService campService;

	@GetMapping
	public String showIndex(Model model) {
		return "index";
	}

	@PostMapping
	public String submitPostalCode(@RequestParam String postalCode, Model model, Locale loc) {
		model.addAttribute("test2", postalCode);
		if (postalCode == null || postalCode.toString().length() == 0) {
			model.addAttribute("postalCodeError", postalCodeService.getPostValidationError(loc, "postNoValue"));
		} else if (!postalCode.toString().matches("[0-9]+")) {
			model.addAttribute("postalCodeError", postalCodeService.getPostValidationError(loc, "postNoNumber"));
		} else if (Integer.parseInt(postalCode) < 1000) {
			model.addAttribute("postalCodeError", postalCodeService.getPostValidationError(loc, "postBelowMin"));
		} else if (Integer.parseInt(postalCode) > 9990) {
			model.addAttribute("postalCodeError", postalCodeService.getPostValidationError(loc, "postAboveMax"));
		} else {
			// No errors
			List<Camp> camps = postalCodeService.getCampsByPostalCode(Integer.parseInt(postalCode));
			model.addAttribute("campsCloseby", camps);

			Boolean admin = this.authorizationService.hasRole(SecurityContextHolder.getContext(), "ROLE_ADMIN");
			if(!admin) {admin=null;}
			model.addAttribute("admin", admin);
			System.out.println(admin);
		}

		return showIndex(model);
	}

	@GetMapping("/add/{id}")
	public String showAddKid(@PathVariable("id") int id, Model model, Locale loc) {
		Camp camp = campService.findCamp(id);
		model.addAttribute(camp);
		
		boolean submitted = Boolean.TRUE == model.getAttribute("submitted");
		
		if(submitted && model.getAttribute("errorName") == null && model.getAttribute("errorCodeOne") == null && model.getAttribute("errorCodeTwo") == null) {
			model.addAttribute("message", postalCodeService.getPostValidationError(loc, "kidAdded"));
			return "redirect:/summercamp";
		}
		return "AddKidForm";
	}

	@PostMapping("/add/{id}")
	public String submitAddKid(@PathVariable("id") int id, Model model, Locale loc, @RequestParam String name,
			@RequestParam String code1, @RequestParam String code2) {
		Camp camp = campService.findCamp(id);

		model.addAttribute(camp);
		if (isEmptyOrNull(name)) {
			model.addAttribute("errorName", postalCodeService.getPostValidationError(loc, "nameEmpty"));
		} else if (name.matches(".*\\d.*")) {
			model.addAttribute("errorName", postalCodeService.getPostValidationError(loc, "nameNotAlphabetical"));
		}
		if (isEmptyOrNull(code2)) {
			model.addAttribute("errorCodeTwo", postalCodeService.getPostValidationError(loc, "codeTwoEmpty"));
		} else if (!code2.toString().matches("[0-9]+")) {
			model.addAttribute("errorCodeTwo", postalCodeService.getPostValidationError(loc, "codeNotNumeric"));
		}

		if (isEmptyOrNull(code1)) {
			model.addAttribute("errorCodeOne", postalCodeService.getPostValidationError(loc, "codeOneEmpty"));
		} else if (!code1.toString().matches("[0-9]+")) {
			model.addAttribute("errorCodeOne", postalCodeService.getPostValidationError(loc, "codeNotNumeric"));
		}
		
		if( !isEmptyOrNull(code1) && !isEmptyOrNull(code2)) {
			System.out.println("Codes here lol");
			System.out.println(code1);
			System.out.println(code2);
			if (Integer.parseInt(code1) > Integer.parseInt(code2)) {
				model.addAttribute("errorCodeOne", postalCodeService.getPostValidationError(loc, "codeOneBiggerThanTwo"));
			}
		}
		
		
		if(model.getAttribute("errorName") == null && model.getAttribute("errorCodeOne") == null && model.getAttribute("errorCodeTwo") == null) {
			if(camp.maxChildrenExceeded()) {
				model.addAttribute("error", postalCodeService.getPostValidationError(loc, "maxKidsExceeded"));
				return "redirect:/summercamp";
			}
			this.campService.addKid(id, name, Integer.parseInt(code2), Integer.parseInt(code2));
			model.addAttribute("message", postalCodeService.getPostValidationError(loc, "kidAdded"));
			return "redirect:/summercamp";
		}
		
		return showAddKid(id, model, loc);
	}
	
	private boolean isEmptyOrNull(String val) {
		return val == null || val.length()<=0 || val == "null";
	}


	public void Test() {
		System.out.println("TEEEST");
	}


}
