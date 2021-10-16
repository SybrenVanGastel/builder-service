package be.itf.builderservice.response;

import be.itf.builderservice.model.Build;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BuildResponseBuilder {

    public static ResponseEntity<Object> generateAllBuilds(String message, HttpStatus status, List<Build> builds) {

        List<Object> buildList = new ArrayList<>();

        for (Build build : builds) {
            Map<String, Object> buildMap = new LinkedHashMap<>();

            buildMap.put("id", build.getId());
            buildMap.put("name", build.getName());
            buildMap.put("tag", build.getTag());
            buildMap.put("primary_weapon_id", build.getPrimaryWeaponId());
            buildMap.put("secondary_weapon_id", build.getSecondaryWeaponId());
            buildMap.put("username", build.getUsername());

            buildList.add(buildMap);
        }

        return new ResponseEntity<>(buildList, status);
    }

    public static ResponseEntity<Object> generateOneBuild(String message, HttpStatus status, Build build) {

        Map<String, Object> buildMap = new LinkedHashMap<>();

        buildMap.put("id", build.getId());
        buildMap.put("name", build.getName());
        buildMap.put("username", build.getUsername());
        buildMap.put("tag", build.getTag());
        buildMap.put("primary_weapon_id", build.getPrimaryWeaponId());
        buildMap.put("primary_weapon_abilities", build.getSelectedAbilitiesWeapon1());
        buildMap.put("secondary_weapon_id", build.getSecondaryWeaponId());
        buildMap.put("secondary_weapon_abilities", build.getSelectedAbilitiesWeapon2());

        Map<String, Object> attributesMap = new LinkedHashMap<>();
        List<Integer> attributePoints = build.getAttributeOptions();
        attributesMap.put("strength", attributePoints.get(0));
        attributesMap.put("dexterity", attributePoints.get(1));
        attributesMap.put("intelligence", attributePoints.get(2));
        attributesMap.put("focus", attributePoints.get(3));
        attributesMap.put("constitution", attributePoints.get(4));
        buildMap.put("attributes", attributesMap);

        return new ResponseEntity<>(buildMap, status);
    }

}
