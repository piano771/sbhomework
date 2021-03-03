package com.homeworkcompany.sbhomework.controller;

import com.homeworkcompany.sbhomework.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
@RequestMapping("/personApi")
public class PersonController {

    // example: /personApi/welcome/Vlad?age=24
    @GetMapping("/welcome/{name}")
    @ResponseBody
    public String welcomeMessage(@PathVariable String name, @RequestParam int age) {
        return "Hello, my name is " + name + ", and I'am " + age + " years old!";
    }

    @PostMapping("/welcomeJson")
    public Person welcomeJson(@RequestBody Person person) {
        System.out.println("Hello, my name is " + person.getName() + ", and I'am " + person.getAge() + " years old!");
        return null;
    }

    @PostMapping("/returnJson")
    @ResponseBody
    public Person returnJson() {
        // Random Number generated
        int minAge = 1;
        int maxAge = 100;
        int age = minAge + (int) (Math.random() * maxAge);

        // Random username generated
        int length = age;
        Random r = new Random();
        String name = r.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();

        // Check name and age
        System.out.println("Name: " + name + "\n" + "Age: " + age);

        return new Person(name, age);
    }
}
