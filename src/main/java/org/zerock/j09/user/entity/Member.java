package org.zerock.j09.user.entity;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
    //소셜 로그인 할떄 공공 데이터가 이메일이다 ; Long 으로
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    private String email;

    private String mname;

    private String mpw;

    @ElementCollection(fetch = FetchType.LAZY)
    // pk 없이 만들때 ElementCollection 쓸수있다
    @Builder.Default
    private Set<MemberRole> memberRoleset = new HashSet<>();
    // 회원 테이블과 권한 테이블을 분류 멤버와 멤버롤 로 구분

    public void addMemberRole(MemberRole role) {
        memberRoleset.add(role);
    }
}
