package domain.services;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;

public class AuthorizationServiceImpl implements AuthorizationService {
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean hasRole(SecurityContext context, String role) {
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) context.getAuthentication().getAuthorities();

		boolean hasRole = false;
		for (GrantedAuthority authority : authorities) {
			hasRole = authority.getAuthority().equals(role);
			if (hasRole) {
				break;
			}
		}
		return hasRole;
	}
}
