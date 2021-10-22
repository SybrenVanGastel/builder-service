package be.itf.builderservice.controller;

import be.itf.builderservice.model.Build;
import be.itf.builderservice.model.Tag;
import be.itf.builderservice.repository.BuildRepository;
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
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(100);
        test.add(100);

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

    @GetMapping("/builders/{name}")
    public ResponseEntity<Object> getBuildsByName(@PathVariable String name) {
        try {
            return new ResponseEntity<>(buildRepository.findAllByNameIgnoreCaseContaining(name), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/builders/user/{name}")
    public ResponseEntity<Object> getBuildsByUsername(@PathVariable String name) {
        try {
            return new ResponseEntity<>(buildRepository.findAllByUsernameIgnoreCaseContaining(name), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/builders/weapon/{name}")
    public ResponseEntity<Object> getBuildsByWeapon(@PathVariable String name) {
        try {
            return new ResponseEntity<>(buildRepository.findAllByPrimaryWeaponNameIgnoreCaseContainingOrSecondaryWeaponNameIgnoreCaseContaining(name, name), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/builders/tag/{name}")
    public ResponseEntity<Object> getBuildsByTag(@PathVariable String name) {
        try {
            return new ResponseEntity<>(buildRepository.findAllByTagEquals(name), HttpStatus.OK);
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
