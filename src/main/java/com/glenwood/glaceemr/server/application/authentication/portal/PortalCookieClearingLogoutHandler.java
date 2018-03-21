package com.glenwood.glaceemr.server.application.authentication.portal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class PortalCookieClearingLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
                    Authentication auth) {
            Cookie cookieWithSlash = new Cookie("JSESSIONID", null);
            //Tomcat adds extra slash at the end of context path (e.g. "/foo/")
            cookieWithSlash.setPath(request.getContextPath() + "/"); 
            cookieWithSlash.setMaxAge(0); 

            Cookie cookieWithoutSlash = new Cookie("JSESSIONID", null);
            //JBoss doesn't add extra slash at the end of context path (e.g. "/foo")
            cookieWithoutSlash.setPath(request.getContextPath()); 
            cookieWithoutSlash.setMaxAge(0); 

            //Remove cookies on logout so that invalidSessionURL (session timeout) is not displayed on proper logout event
            response.addCookie(cookieWithSlash); //For cookie added by Tomcat 
            response.addCookie(cookieWithoutSlash); //For cookie added by JBoss
    }
}