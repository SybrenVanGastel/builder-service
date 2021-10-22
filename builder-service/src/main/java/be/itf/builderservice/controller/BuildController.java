package be.itf.builderservice.controller;

import be.itf.builderservice.model.Build;
import be.itf.builderservice.model.Tag;
import be.itf.builderservice.repository.BuildRepository;
import be.itf.builderservice.response.BuildResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BuildController {

    @Autowired
    private BuildRepository buildRepository;

    @GetMapping("/fill")
    public String fill() {

        Build build = new Build("Rapier", "Musket", "TestBuild", "Sybren", Tag.PvE);

        List<Integer> test = new ArrayList<>();
        test.add(20);
        test.add(50);
        test.add(10);
        test.add(50);
        test.add(75);

        build.setAttributeOptions(test);
        build.setSelectedAbilitiesWeapon1(test);
        build.setSelectedAbilitiesWeapon2(test);

        buildRepository.save(build);

        return "Success";
    }

    @GetMapping("/builders")
    public ResponseEntity<Object> getAllBuilds() {
        try {
            return new ResponseEntity<>(buildRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/builder/{name}")
    public ResponseEntity<Object> getBuildByName(@PathVariable String name) {
        try {
            return new ResponseEntity<>(buildRepository.findByNameEquals(name), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/builder")
    public ResponseEntity<Object> createBuild(@RequestBody Build build){
        try {
            buildRepository.save(build);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/builder/{name}")
    public ResponseEntity<Object> putBuild(@PathVariable String name, @RequestBody Build build){
        try {
            Build buildByName = buildRepository.findByNameEquals(name);
            build.setId(buildByName.getId());
            buildRepository.save(build);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/builder/{name}")
    public ResponseEntity<Object> deleteBuild(@PathVariable String name) {
        try {
            buildRepository.deleteByNameEquals(name);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
