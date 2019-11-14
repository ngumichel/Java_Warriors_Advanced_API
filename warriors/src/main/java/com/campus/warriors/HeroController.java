package com.campus.warriors;

import java.util.concurrent.atomic.AtomicLong;

import com.campus.warriors.contracts.Hero;
import com.campus.warriors.engine.Warriors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeroController extends Warriors {

    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping("/heroes")
    public Iterable<Hero> heroes() {
        return availableHeroes();
    }

}
