package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Dog;
import com.example.demo.repositories.DogRepository;
import com.example.demo.service.DogService;

@Controller
public class DogController {

	@Autowired
	private DogRepository dogRepository;
	
	@Autowired
	private DogService dogService;
	
	private ArrayList dogModelList;
	
	private List<String> dogrisklist = null;
	
	@GetMapping(value = "/mysqlsample")
    public String doghome(
                    @RequestParam(value = "search", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date q,
                    Model model) {
            if (q != null) {
                    dogModelList = new ArrayList();
                    System.out.println("q is = " + q);
                    dogrisklist = dogService.atriskdogs(q);
                    for (String name : dogrisklist) {
                            System.out.println("Dogs in repository are : " + dogRepository.findAll());
                            Dog doggy = dogRepository.findByName(name);
                            System.out.println(doggy.toString() + "doggy name : " + doggy.getName());
                            dogModelList.add(doggy);
                            System.out.println("This dog's name is : " + doggy.getName());
                    }
            }
            model.addAttribute("search", dogModelList);

            model.addAttribute("dogs", dogRepository.findAll());

            return "mysqlsample";

    }

    @PostMapping(value = "/")
    public String adddog(@RequestParam("name") String name,
                    @RequestParam("rescued") @DateTimeFormat(pattern = "yyyy-MM-dd") Date rescued,
                    @RequestParam("vaccinated") Boolean vaccinated, Model model) {
            dogService.addADog(name, rescued, vaccinated);
            System.out.println("name = " + name + ",rescued = " + rescued + ", vaccinated = " + vaccinated);
            return "redirect:/mysqlsample";
    }

    @PostMapping(value = "/delete")
    public String deleteDog(@RequestParam("name") String name,
                    @RequestParam("id") Long id) {
            dogService.deleteADOG(name, id);
            System.out.println("Dog named = " + name + "was removed from our database. Hopefully he or she was adopted.");
            return "redirect:/mysqlsample";

    }
    
    @PostMapping(value = "/genkey")
    public String genkey(@RequestParam("name") String name,
                    @RequestParam("rescued") @DateTimeFormat(pattern = "yyyy-MM-dd") Date rescued,
                    @RequestParam("vaccinated") Boolean vaccinated, Model model) {
            dogService.getGeneratedKey(name, rescued, vaccinated);
            System.out.println("name = " + name + ",rescued = " + rescued + ", vaccinated = " + vaccinated);
            return "redirect:/mysqlsample";
    }
}
