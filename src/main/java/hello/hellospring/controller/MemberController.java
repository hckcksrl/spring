package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 컴포넌트 스캔 ( @Controller, @Repository, @Service 어노테이션 안에 @Component라는 어노테이션이 존재)
 * @Component는 @SpringBootApplication 어노테이션 안에 @ComponentScan이라는 어노테이션이 있기때문에 스캔을 한다.
 * 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다
 */

@Controller
public class MemberController {

    private final MemberService memberService;

    /**
     * @Autowired가 있으면 스프링에서 연관된 객체를 스프링 컨테이너에서 찾아서 넣어줌
     * 아래의 형식이 DI(Dependency Injection) 의존성 주입 ( 객체 의존관계를 외부에서 넣어주는 것 ) 이라고함
     * @Autowired 를 통한 DI는 helloConroller , memberService 등과 같이 스프링이 관리하는
     * 객체에서만 동작한다. 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다.
     */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * @GetMapping은 Http Get 메소드로 작동
     */
    @GetMapping("/members/new")
    public String createFoem() {
        return "members/createMemberForm";
    }

    /**
     * @PostMappring Http Post 메소드로 작동
     */
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";

    }
}
