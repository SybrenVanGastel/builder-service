package be.itf.builderservice.model;

import java.util.ArrayList;
import java.util.List;

public class BuildDTO {
    private String primaryWeaponName;
    private String secondaryWeaponName;
    private String name;
    private String username;
    private String tag;
    private List<Integer> selectedAbilitiesWeapon1 = new ArrayList<>();
    private List<Integer> selectedAbilitiesWeapon2 = new ArrayList<>();
    private List<Integer> attributeOptions = new ArrayList<>();

    public BuildDTO() {
    }

    public String getPrimaryWeaponName() {
        return primaryWeaponName;
    }

    public void setPrimaryWeaponName(String primaryWeaponName) {
        this.primaryWeaponName = primaryWeaponName;
    }

    public String getSecondaryWeaponName() {
        return secondaryWeaponName;
    }

    public void setSecondaryWeaponName(String secondaryWeaponName) {
        this.secondaryWeaponName = secondaryWeaponName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Integer> getSelectedAbilitiesWeapon1() {
        return selectedAbilitiesWeapon1;
    }

    public void setSelectedAbilitiesWeapon1(List<Integer> selectedAbilitiesWeapon1) {
        this.selectedAbilitiesWeapon1 = selectedAbilitiesWeapon1;
    }

    public List<Integer> getSelectedAbilitiesWeapon2() {
        return selectedAbilitiesWeapon2;
    }

    public void setSelectedAbilitiesWeapon2(List<Integer> selectedAbilitiesWeapon2) {
        this.selectedAbilitiesWeapon2 = selectedAbilitiesWeapon2;
    }

    public List<Integer> getAttributeOptions() {
        return attributeOptions;
    }

    public void setAttributeOptions(List<Integer> attributeOptions) {
        this.attributeOptions = attributeOptions;
    }
}
