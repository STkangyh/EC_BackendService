package ECService.demo.controller;


import ECService.demo.dto.applyForm;
import ECService.demo.entity.Apply;
import ECService.demo.repository.ApplyRepository;
import ECService.demo.repository.ListInfoRepository;
import ECService.demo.repository.mapping.ListInfoMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ApplyController {
    @Autowired
    private ListInfoRepository listInfoRepository;
    @Autowired // @Repository 어노테이션을 통해 스프링부트가 Bean에 미리 생성해놓은 객체를 자동으로 연결
    private ApplyRepository applyRepository;



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

    @GetMapping("/applies/{id}")
    public String showForm(@PathVariable Long id, Model model) {
        //URL로부터 id를 받아서
        System.out.println("controller 실행 - id = " + id);
        //1. id로 데이터를 가져오기
        Apply applyEntity = applyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid apply"));
        //Optional<Apply> applyEntity = applyRepository.findById(id);
        //2. 가져온 데이터를 model에 등록하기
        model.addAttribute("apply", applyEntity);
        //3. 보여줄 페이지를 설정하기

        return "apply/showApply";
    }

    @GetMapping("/applies")
    public String showApplyList(Model model) {
        //1. 모든 Apply를 가져온다.
        List<ListInfoMapping> applies = listInfoRepository.findAllBy();

        //2. 가져온 Apply 묶음을 뷰로 전달
        model.addAttribute("applyList", applies);
        //3. 뷰 페이지를 설정
        return "apply/showList";
    }

    @PostMapping("apply{id}/update")
    public String updateState(@PathVariable Long id, @RequestParam String state, RedirectAttributes redirectAttributes) {
        //apply의 id와 status가져오기
        System.out.println("지원자 id: " + id);
        System.out.println("지원자 상태: " + state);
        //id로 DB에서 Entity를 찾고 setter로 바꾸기
        Apply apply = applyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid apply Id: " + id));
        apply.setState(state);
        applyRepository.save(apply);
        //변경 성공 메시지 전송
        redirectAttributes.addFlashAttribute("statusMessage", "지원자의 상태가 {state}으로 변경되었습니다.");
        return "redirect:/applies";
    }

    @GetMapping("lookUP")
    public String lookUp() {
        return "apply/applyLookUp";
    }

    @PostMapping("forms/lookUp")
    public String showResult(@RequestParam String phoneNumber, Model model) {

        System.out.println("phoneNumber: " + phoneNumber);
        //phoneNumber로 apply를 찾을 수 없는 경우 예외처리하기!!!
        Apply applyEntity = applyRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new IllegalArgumentException("unknown phone number: " + phoneNumber));
        System.out.println(applyEntity);
        System.out.println(applyEntity.getState());

        model.addAttribute("apply", applyEntity);
        return "apply/showResult";
    }

}