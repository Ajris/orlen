package com.example.demo;

import com.example.demo.entity.CalculateRoute;
import com.example.demo.entity.Crossroad;
import com.example.demo.entity.Road;
import com.example.demo.entity.Vehicle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        Crossroad crossroad1 = new Crossroad();
        crossroad1.setLatitude(new Double(1));
        crossroad1.setLongitude(new Double(1));
        Crossroad crossroad2 = new Crossroad();
        crossroad2.setLatitude(new Double(2));
        crossroad2.setLongitude(new Double(2));
        Crossroad crossroad3 = new Crossroad();
        crossroad3.setLatitude(new Double(3));
        crossroad3.setLongitude(new Double(3));
        Crossroad crossroad4 = new Crossroad();
        crossroad4.setLatitude(new Double(4));
        crossroad4.setLongitude(new Double(4));
        ArrayList<Crossroad> crossroads = new ArrayList<>();
        crossroads.add(crossroad1);
        crossroads.add(crossroad2);
        crossroads.add(crossroad3);
        crossroads.add(crossroad4);

        Road road1 = new Road();
        road1.setStart(crossroad1);
        road1.setEnd(crossroad2);
        Road road2 = new Road();
        road2.setStart(crossroad2);
        road2.setEnd(crossroad3);
        Road road3 = new Road();
        road3.setStart(crossroad3);
        road3.setEnd(crossroad4);
        Road road4 = new Road();
        road4.setStart(crossroad2);
        road4.setEnd(crossroad4);
        crossroad1.setConnectedRoads(new ArrayList<>());
        crossroad2.setConnectedRoads(new ArrayList<>());
        crossroad3.setConnectedRoads(new ArrayList<>());
        crossroad4.setConnectedRoads(new ArrayList<>());

        crossroad1.getConnectedRoads().add(road1);
        crossroad2.getConnectedRoads().add(road1);
        crossroad2.getConnectedRoads().add(road2);
        crossroad3.getConnectedRoads().add(road2);
        crossroad3.getConnectedRoads().add(road3);
        crossroad4.getConnectedRoads().add(road3);
        crossroad2.getConnectedRoads().add(road4);
        crossroad4.getConnectedRoads().add(road4);
        Vehicle vehicle = new Vehicle();
        CalculateRoute calculateRoute = new CalculateRoute(vehicle);
        HashMap<Crossroad, Crossroad> hashMap = calculateRoute.route(crossroad1, crossroad4, crossroads);

        Crossroad crossroad = crossroad4;

       while(crossroad != null) {
            System.out.println(crossroad.getLatitude());
            crossroad = hashMap.get(crossroad);
        }
    }
}
