package controllers;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import domain.Camp;
import domain.services.AuthorizationService;
import domain.services.CampService;
import domain.services.PostalCodeService;

@RunWith(Parameterized.class)
public class SummerCampControllerTest {

	SummerCampController controller = new SummerCampController();
	Model model;

	@BeforeEach
	public void before() {
		this.model = new ExtendedModelMap();

	}

	@Test
	public void test_controllerInstance() {
		assertThat(controller, instanceOf(SummerCampController.class));
	}

	@Test
	public void test_showIndexReturnsIndex() {
		assertThat(controller.showIndex(model), is("index"));
		// assertThat((String) model.asMap().get("msg"), is("SUCCESS"));
	}

	@Parameters
	public static Collection<Object[]> postData() {
		return Arrays.asList(new Object[][] { { "94b1", true }, { "9041", false }, { "", true }, { "15", true },
				{ "999", true }, { "10000", true }, { "8412", false }, { null, true }, });
	}

	@ParameterizedTest
	@MethodSource("postData")
	public void test_wrongPostalCodeShouldReturnError(String postInput, boolean expected) {
		System.out.println(postInput);
		assertEquals(expected, isPostWrong(model, postInput));
	}

	private boolean isPostWrong(Model _model, String postalCode) {
		// Mocking the translation service for errors
		PostalCodeService service = mock(PostalCodeService.class);
		when(service.getPostValidationError(any(Locale.class), any(String.class))).thenReturn("sample error");

		AuthorizationService authService = mock(AuthorizationService.class);
		when(authService.hasRole(any(SecurityContext.class), any(String.class))).thenReturn(false);
		
		
		controller.setPostalCodeServiceMock(service);
		controller.setAuthorizationMock(authService);

		controller.submitPostalCode(postalCode, _model, Locale.ENGLISH);
		return _model.asMap().get("postalCodeError") != null;
	}

	@Parameters
	public static Collection<Object[]> childData() {
		return Arrays.asList(new Object[][] { { "Maxim", "1", "2", false }, { "12", "1", "2", true }, { "", "1", "2", true },
				{ "Maxim", "2", "1", true }, { "Maxim", "1", null, true }, { "Maxim", null, "1", true }, { null, "1", "2", true },
				{ "Maxim", "1", "1", false }, });
	}

	    @ParameterizedTest
	    @MethodSource("childData")
		public void test_incorrectChildInputShouldReturnError(String childName, String code1, String code2, boolean expected) {
			assertEquals(expected, isChildWrong(model, childName, code1, code2, expected));
		}
	    
		private boolean isChildWrong(Model _model, String name, String code1, String code2, boolean expected) {
			// Mocking the translation service for errors
			PostalCodeService pService = mock(PostalCodeService.class);
			when(pService.getPostValidationError(any(Locale.class), any(String.class))).thenReturn("sample error");
		
			CampService campService = mock(CampService.class);
			when(campService.findCamp(1)).thenReturn(new Camp(1, "sample camp", 9000, 2));
			
			AuthorizationService authService = mock(AuthorizationService.class);
			when(authService.hasRole(any(SecurityContext.class), any(String.class))).thenReturn(false);			

			
			controller.setPostalCodeServiceMock(pService);
			controller.setCampServiceMock(campService);
			controller.setAuthorizationMock(authService);
			
			controller.submitAddKid(1, _model, Locale.ENGLISH, name, String.valueOf(code1), String.valueOf(code2));
			if(expected) {
				verify(pService).getPostValidationError(any(Locale.class), any(String.class));
			}

			return _model.asMap().get("errorName") != null || _model.asMap().get("errorCodeTwo") != null || _model.asMap().get("errorCodeOne") != null;
		}
}
