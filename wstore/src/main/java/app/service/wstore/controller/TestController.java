package app.service.wstore.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/admin")
public class TestController {

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String test() {
        return "quyen admin";
    }
}
