package org.zerock.j09;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.AntPathMatcher;
import org.zerock.j09.user.secyrity.utill.JWTUtill;

@SpringBootTest
@ActiveProfiles("dev")
class J09ApplicationTests {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
    }
    //1

    @Test
    public void testCreateJWT()throws Exception{

        String email ="user88@aaa.com";

        String result = new JWTUtill().generateToken(email);

        System.out.println(result);


    }
    //2

    @Test
    public void testValidate() throws Exception{
        String str = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjU2NDAzNzcsImV4cCI6MTYyODIzMjM3Nywic3ViIjoidXNlcjg4QGFhYS5jb20ifQ.p7Hr1J_rQdffuhtj1X1f90OCZiNYjwsujO-YHtLPHFo";

        System.out.println(new JWTUtill().validateAndExtract(str));
    }

    @Test
    public void testMatch(){
        String pattern = "/api/board/**/*";
        AntPathMatcher matcher = new AntPathMatcher();

        System.out.println(matcher.match(pattern,"/api/board/read/123"));
    }

    @Test
    public void testEncode() {

        System.out.println(passwordEncoder.encode("1111"));

        String enStr = "$2a$10$s56SRk9i6Ta73Fs7s9AbkOMZC4nThNmFl49d76Zzv5zmE4c43WNgu";

        System.out.println(passwordEncoder.matches("1111",enStr));

    }
}