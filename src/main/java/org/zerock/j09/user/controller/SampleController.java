package org.zerock.j09.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 1.어노테이션 방식 설정 2.컨피그레이션을 잡는방법
@RestController
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/all")
    public String[] doALL() {


        return new String[]{"AAA", "AAA", "AAA"};
    }

    @GetMapping("/member")
    public String[] doMember() {


        return new String[]{"BBB", "BBB", "BBB"};
    }

    @GetMapping("/admin")
    public String[] doAdmin() {


        return new String[]{"CCC", "CCC", "CCC"};
    }

}


