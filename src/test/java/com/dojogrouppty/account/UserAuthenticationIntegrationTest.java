package com.dojogrouppty.account;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserAuthenticationIntegrationTest {

    private static final Logger logger
            = LoggerFactory.getLogger(UserAuthenticationIntegrationTest.class);
//public class UserAuthenticationIntegrationTest extends WebSecurityConfigurationAware {

//    private static String SEC_CONTEXT_ATTR = HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;
//
//   @Ignore @Test
//    public void requiresAuthentication() throws Exception {
//        mockMvc.perform(get("/account/current"))
//                .andExpect(redirectedUrl("http://localhost/signin"));
//    }
//
//   @Ignore @Test
//    public void userAuthenticates() throws Exception {
//        final String username = "user";
//
//        mockMvc.perform(post("/authenticate").param("username", username).param("password", "demo"))
//                .andExpect(redirectedUrl("/"))
//                .andExpect(r -> Assert.assertEquals(((SecurityContext) r.getRequest().getSession().getAttribute(SEC_CONTEXT_ATTR)).getAuthentication().getName(), username));
//
//    }
//
//   @Ignore @Test
//    public void userAuthenticationFails() throws Exception {
//        final String username = "user";
//        mockMvc.perform(post("/authenticate").param("username", username).param("password", "invalid"))
//                .andExpect(redirectedUrl("/signin?error=1"))
//                .andExpect(r -> Assert.assertNull(r.getRequest().getSession().getAttribute(SEC_CONTEXT_ATTR)));
//    }
    @Test
    public void testSimple() {
        String p = "XXX";
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        String encPassword = pe.encode(p);
        logger.debug("HELLB  ---->[" + encPassword+"]");
    }
}
