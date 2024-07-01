package ECService.demo.controller;


import ECService.demo.dto.applyForm;
import ECService.demo.entity.Apply;
import ECService.demo.repository.ApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplyController {

    @Autowired // 스프링부트가 미리 생성해놓은 객체를 자동으로 연결
    private ApplyRepository applyRepository;

    @GetMapping("/apply")
    public String apply() {
        return "forms/applyForm";
    }

    @PostMapping("forms/apply")
    public String applyForm(applyForm form) {
        System.out.println("DTO: " + form.toString());

        //1. DTO를 Entity로 변환
        Apply apply = form.toEntity();
        //2. Repository에게 Entity를 DB안에 저장하게 함.(Entity를 반환)
        Apply saved = applyRepository.save(apply);

        System.out.println("Entity: " + saved.toString());
        //이후 조회 페이지로 이동 예정.
        return "/index";
    }
}
