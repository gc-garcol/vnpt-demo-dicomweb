package gc.garcol.demodicomweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author garcol
 */
@Controller
public class HomePageController {

    @GetMapping(path = "/")
    public String home() {
        return "index";
    }

}
