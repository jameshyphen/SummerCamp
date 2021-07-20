package controllers;

import domain.services.CampService;
import domain.Camp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/summercamp")
public class SummerCampRestController {
	
	@Autowired
	private CampService campService;
	
	@GetMapping("/{id}")
	@ResponseBody
	public Object getCampById(@PathVariable("id") int id) {
		Camp camp = campService.findCamp(id);
		return camp;
	}

}
