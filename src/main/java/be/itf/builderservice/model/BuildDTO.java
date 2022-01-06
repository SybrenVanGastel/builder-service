package be.itf.builderservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;

public class BuildDTO {
    private String id;
    private String primaryWeaponName;
    private String secondaryWeaponName;
    private String name;
    private String username;
    private Tag tag;
    private List<Integer> selectedAbilitiesWeapon1 = new ArrayList<>();
    private List<Integer> selectedAbilitiesWeapon2 = new ArrayList<>();
    private List<Integer> attributeOptions = new ArrayList<>();


}
