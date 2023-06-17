package exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
@RequestMapping("/")
public class WelcomeController {
    @GetMapping("/")
    public String welcome() {
        return "Welcome to Spring";
    }

    @GetMapping("/hello")
    public String personalisedWelcome(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s", name);
    }
}
// END