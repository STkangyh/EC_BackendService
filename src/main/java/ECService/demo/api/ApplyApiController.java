package ECService.demo.api;

import ECService.demo.dto.ApplyForm;
import ECService.demo.entity.Apply;
import ECService.demo.repository.ApplyRepository;
import ECService.demo.repository.ListInfoRepository;
import ECService.demo.repository.mapping.ListInfo;
import ECService.demo.repository.mapping.ListInfoMapping;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController //RestAPI 용 컨트롤러 - JSON을 반환
public class ApplyApiController {
    @Autowired
    ApplyRepository applyRepository;
    @Autowired
    ListInfoRepository listInfoRepository;

    //GET
    @GetMapping("/api/applies")
    public List<ListInfoMapping> index() {
        return listInfoRepository.findAllBy();
    }

    @GetMapping("/api/applies/{id}")
    public Apply showForm(@PathVariable Long id) {
        return applyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid apply"));
    }

    ///POST
    @PostMapping("/api/applyForm")
    public Apply applyForm(ApplyForm applyDto) {
        Apply apply = applyDto.toEntity();
        return applyRepository.save(apply);
    }
    @PostMapping("/api/showResult")
    public Apply showResult(@RequestParam String phoneNumber) {
        Apply applyEntity = applyRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new IllegalArgumentException("Invalid phone number"));
        return applyEntity;
    }


    //PATCH
    //RequestBody를 통한 JSON 받기
    @PatchMapping("/api/apply/{id}/update")
    public Apply updateState(@PathVariable Long id,
                             @RequestBody Map<String, String> requestBody) {
        String state = requestBody.get("state");
        Apply apply = applyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid apply"));
        apply.setState(state);
        return applyRepository.save(apply);
    }
    //RequestParam을 통하여 본문 내용에 직접 접근
    @PatchMapping("/api/apply{id}/update")
    public Apply updateState1(@PathVariable Long id,
                              @RequestParam String state) {
        Apply applyEntity = applyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid apply"));
        applyEntity.setState(state);
        return applyRepository.save(applyEntity);
    }

}
