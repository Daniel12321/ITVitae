package com.github.daniel12321.phones.controller;

import com.github.daniel12321.phones.model.Phone;
import com.github.daniel12321.phones.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/phones")
public class PhonesController {

    @Autowired
    private PhoneService service;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("phones", this.service.getAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String phone(@PathVariable("id") int id, Model model) {
        model.addAttribute("phone", this.service.getPhone(id));
        return "phone";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        model.addAttribute("phones", this.service.search(query));
        return "search";
    }
}
