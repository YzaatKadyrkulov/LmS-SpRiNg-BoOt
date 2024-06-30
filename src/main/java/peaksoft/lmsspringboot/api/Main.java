package peaksoft.lmsspringboot.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class Main {

    @GetMapping
    public String getMain(){
        return "first_main";
    }

}
