package com.example.demo;

import com.example.demo.entity.Crossroad;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CrossroadController {

    @GetMapping(value = "/crossroads")
    public List<Crossroad> getCrossroads() {
        List<Crossroad> crossroads = new ArrayList<>();
        Crossroad c1 = new Crossroad(19.66044, 52.59162);
        Crossroad c2 = new Crossroad(19.67254, 52.59047);
        Crossroad c3 = new Crossroad(19.66576, 52.58635);
        Crossroad c4 = new Crossroad(19.68052, 52.58625);
        crossroads.add(c1);
        crossroads.add(c2);
        crossroads.add(c3);
        crossroads.add(c4);
        return crossroads;
    }
}
