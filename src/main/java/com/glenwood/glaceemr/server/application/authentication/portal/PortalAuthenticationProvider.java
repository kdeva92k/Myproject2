package com.glenwood.glaceemr.server.application.authentication.portal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

import com.glenwood.glaceemr.server.datasource.TennantContextHolder;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.glenwood.glaceemr.server.utils.SessionMap;
@Component
public class PortalAuthenticationProvider implements AuthenticationProvider {

	PortalUserDetailsService portalUserDetailsService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	SessionMap sessionMap;
	private UserDetailsChecker preAuthenticationChecks = new DefaultPreAuthenticationChecks();
	private UserDetailsChecker postAuthenticationChecks = new DefaultPostAuthenticationChecks();
	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	protected final Logger logger = LoggerFactory.getLogger(PortalUserDetailsService.class);
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		String tennantId = HUtil.Nz(request.getParameter("dbname"),"-1");
		if(tennantId != "-1"){
			TennantContextHolder.setTennantId(tennantId);
		}	

		sessionMap.setSessionId("N/A");
		//sessionMap.setUserID(loggedInUser.getUserId());
		sessionMap.setUserName("N/A");
		String clientIP = HUtil.Nz(request.getHeader("X-FORWARDED-FOR"),"-1");
		if (clientIP.equals("-1")) {
			clientIP = HUtil.Nz(request.getRemoteAddr(),"-1");
		}
		//		sessionMap.setClientIp(clientIP);


		return validateOfficeUser(authentication);

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}


	public PortalUserDetailsService getUserDetailsService() {
		return portalUserDetailsService;
	}

	public void setUserDetailsService(PortalUserDetailsService portalUserDetailsService) {
		this.portalUserDetailsService = portalUserDetailsService;
	}

	@SuppressWarnings("deprecation")
	public Authentication validateOfficeUser(Authentication authentication)throws AuthenticationException{
		UserDetails userDetails =portalUserDetailsService.loadUserByUsername(authentication.getName());
		preAuthenticationChecks.check(userDetails);
		if (authentication.getCredentials() == null) {
			logger.debug("Authentication failed: no credentials provided");
			System.out.println("password is null");
			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);
		}
		if(!authentication.getCredentials().toString().trim().equals(userDetails.getPassword())){

			logger.debug("Authentication failed: password does not match stored value");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);

		}
		postAuthenticationChecks.check(userDetails);

		return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
				userDetails.getPassword(),userDetails.getAuthorities());
	}

	private class DefaultPreAuthenticationChecks implements UserDetailsChecker {
		@SuppressWarnings("deprecation")
		public void check(UserDetails user) {
			if (!user.isAccountNonLocked()) {
				logger.debug("User account is locked");

				throw new LockedException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked",
						"User account is locked"), user);
			}

			if (!user.isEnabled()) {
				logger.debug("User account is disabled");

				throw new DisabledException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled",
						"User is disabled"), user);
			}

			if (!user.isAccountNonExpired()) {
				logger.debug("User account is expired");

				throw new AccountExpiredException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.expired",
						"User account has expired"), user);
			}
		}
	}

	private class DefaultPostAuthenticationChecks implements UserDetailsChecker {
		@SuppressWarnings("deprecation")
		public void check(UserDetails user) {
			if (!user.isCredentialsNonExpired()) {
				logger.debug("User account credentials have expired");

				throw new CredentialsExpiredException(messages.getMessage(
						"AbstractUserDetailsAuthenticationProvider.credentialsExpired",
						"User credentials have expired"), user);
			}
		}
	}
}