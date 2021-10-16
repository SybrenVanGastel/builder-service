package be.itf.builderservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "builds")
public class Build {
    @Id
    private String id;
    private Integer primaryWeaponId;
    private Integer secondaryWeaponId;
    @Indexed(unique=true)
    private String name;
    private String username;
    private Tag tag;
    private List<Integer> selectedAbilitiesWeapon1 = new ArrayList<>();
    private List<Integer> selectedAbilitiesWeapon2 = new ArrayList<>();
    private List<Integer> attributeOptions = new ArrayList<>();

    public Build() {
    }

    public Build(Integer primaryWeaponId, Integer secondaryWeaponId, String name, String username, Tag tag) {
        this.primaryWeaponId = primaryWeaponId;
        this.secondaryWeaponId = secondaryWeaponId;
        this.name = name;
        this.username = username;
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPrimaryWeaponId() {
        return primaryWeaponId;
    }

    public void setPrimaryWeaponId(Integer primaryWeaponId) {
        this.primaryWeaponId = primaryWeaponId;
    }

    public Integer getSecondaryWeaponId() {
        return secondaryWeaponId;
    }

    public void setSecondaryWeaponId(Integer secondaryWeaponId) {
        this.secondaryWeaponId = secondaryWeaponId;
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

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
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
