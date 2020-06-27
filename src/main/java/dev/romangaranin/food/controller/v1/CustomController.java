package dev.romangaranin.food.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test controller to trigger OpenAPI integration.
 */
@RestController
@RequestMapping(path = "/api/v1")
public class CustomController {

    @GetMapping("/custom")
    public String custom() {
        return "custom";
    }
}
