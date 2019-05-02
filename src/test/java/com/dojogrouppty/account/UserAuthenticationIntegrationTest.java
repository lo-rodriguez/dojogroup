package com.dojogrouppty.account;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserAuthenticationIntegrationTest {

    private static final Logger logger
            = LoggerFactory.getLogger(UserAuthenticationIntegrationTest.class);

    @Test
    public void testSimple() {
        String p = "XXX";
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        String encPassword = pe.encode(p);
        logger.debug("HELLB  ---->[" + encPassword+"]");
    }
}
