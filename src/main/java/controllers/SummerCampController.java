package controllers;

import domain.*;
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

	@Autowired
	private PostalCodeService postalCodeService;

	@Autowired
	private CampService campService;

	@GetMapping
	public String showIndex(Model model) {
		model.addAttribute("test1", "testaaa");
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

			Boolean admin = hasRole("ROLE_ADMIN");
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
		return "AddKidForm";
	}

	private boolean hasRole(String role) {
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		boolean hasRole = false;
		for (GrantedAuthority authority : authorities) {
			hasRole = authority.getAuthority().equals(role);
			if (hasRole) {
				break;
			}
		}
		return hasRole;
	}

	@PostMapping("/add/{id}")
	public String submitAddKid(@PathVariable("id") int id, Model model, Locale loc, @RequestParam String name,
			@RequestParam String code1, @RequestParam String code2) {
		Camp camp = campService.findCamp(id);

		System.out.println(name);
		System.out.println(code1);
		System.out.println(code2);

		model.addAttribute(camp);
		if (name == null || name.toString().length() == 0) {
			System.out.println("HERE AAA");
			model.addAttribute("errorName", postalCodeService.getPostValidationError(loc, "nameEmpty"));
		} else if (name.toString().matches("[0-9]+")) {
			model.addAttribute("errorName", postalCodeService.getPostValidationError(loc, "nameNotAlphabetical"));
		}
		if (code2 == null || code2.toString().length() == 0) {
			model.addAttribute("errorCodeTwo", postalCodeService.getPostValidationError(loc, "codeTwoEmpty"));
		} else if (!code2.toString().matches("[0-9]+")) {
			model.addAttribute("errorCodeTwo", postalCodeService.getPostValidationError(loc, "codeNotNumeric"));
		}

		if (code1 == null || code1.toString().length() == 0) {
			System.out.println("HERE 222AAA");
			model.addAttribute("errorCodeOne", postalCodeService.getPostValidationError(loc, "codeOneEmpty"));
		} else if (!code1.toString().matches("[0-9]+")) {
			model.addAttribute("errorCodeOne", postalCodeService.getPostValidationError(loc, "codeNotNumeric"));
		} else if (Integer.parseInt(code1) > Integer.parseInt(code2)) {
			model.addAttribute("errorCodeOne", postalCodeService.getPostValidationError(loc, "codeOneBiggerThanTwo"));
		}

		return showAddKid(id, model, loc);
	}

	public void Test() {
		System.out.println("TEEEST");
	}

	//
	// @PostMapping("/camptest")
	// @ResponseBody
	// public Object postCamp() {
	// Camp camp = new Camp(1, "test", 9000, 5);
	// return camp;
	// }

}
