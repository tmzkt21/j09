package org.zerock.j09;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.AntPathMatcher;

@SpringBootTest
@ActiveProfiles("dev")
@Log4j2
class J09ApplicationTests {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
    }

    @Test
    public void testEncode() {

        System.out.println(passwordEncoder.encode("1111"));

        String enStr = "$2a$10$s56SRk9i6Ta73Fs7s9AbkOMZC4nThNmFl49d76Zzv5zmE4c43WNgu";

        System.out.println(passwordEncoder.matches("1111",enStr));

    }

    @Test
    public void testNatch() {

        String patten = "/api/board/**/*";
        AntPathMatcher matcher = new AntPathMatcher();
        log.info(matcher.match(patten,"/api/board/read/123"));
        // 앤트패턴
    }

}
