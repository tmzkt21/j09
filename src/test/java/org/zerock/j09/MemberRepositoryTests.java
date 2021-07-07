package org.zerock.j09;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.zerock.j09.user.entity.Member;
import org.zerock.j09.user.entity.MemberRole;
import org.zerock.j09.user.repository.MemberRepository;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@ActiveProfiles("dev")
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@aaa.com")
                    .mpw(passwordEncoder.encode("111"))
                    .mname("USER" + i)
                    .build();
            // 회원만들고 비번에 패스워드 인코더 1111로 건상태
            if (i > 80) {
                member.addMemberRole(MemberRole.MEMBER);
            }
            if (i > 90) {
                member.addMemberRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
            // 80번초과이면 멤버고  90개 초과이면 관리자
        });

    }
    @Test
    public void testLoad() {
        String email = "user10@aaa.com";
        // 10 유저라는 권한밖에없음

        Optional<Member> member  = memberRepository.findByEmail(email);

        member.ifPresent(todo -> {
            System.out.println(todo);
        });
        // 스프링시큐리티가 사용하는 타입은 user user 클래스 상속 빌더도 존재
    }


}
