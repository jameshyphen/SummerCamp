package domain.services;

import org.springframework.security.core.context.SecurityContext;

public interface AuthorizationService {
	public boolean hasRole(SecurityContext context, String role);
}
