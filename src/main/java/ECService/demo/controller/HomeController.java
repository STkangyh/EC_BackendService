package ECService.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        System.out.println("controller run");
        return "index";
    }

    @GetMapping("/apply")
    public String apply() {
        return "apply/applyForm";
    }
}
