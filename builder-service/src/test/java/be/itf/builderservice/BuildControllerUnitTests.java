package be.itf.builderservice;

import be.itf.builderservice.model.Build;
import be.itf.builderservice.model.Tag;
import be.itf.builderservice.repository.BuildRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class BuildControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BuildRepository buildRepository;

    private ObjectMapper mapper = new ObjectMapper();

    Build build1 = new Build("TestWeapon1", "TestWeapon2", "TestBuild", "Tester", Tag.PvE, Arrays.asList(1, 2, 3), Arrays.asList(4,5,6), Arrays.asList(1, 2, 3, 100, 100));
    Build build2 = new Build("TestWeapon3", "TestWeapon4", "TestBuild2", "Tester2", Tag.PvP, Arrays.asList(7,8,9), Arrays.asList(10,11,12), Arrays.asList(4, 5, 6, 200, 200));

    private List<Build> allBuilds = Arrays.asList(build1, build2);

    @Test
    public void givenBuilds_whenGetAllBuilds_thenReturnJsonBuilds() throws Exception {
        given(buildRepository.findAll()).willReturn(allBuilds);

        mockMvc.perform(get("/builders"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].primaryWeaponName", is("TestWeapon1")))
                .andExpect(jsonPath("$[0].secondaryWeaponName", is("TestWeapon2")))
                .andExpect(jsonPath("$[0].name", is("TestBuild")))
                .andExpect(jsonPath("$[0].username", is("Tester")))
                .andExpect(jsonPath("$[0].tag", is("PvE")))
                .andExpect(jsonPath("$[0].selectedAbilitiesWeapon1", is(build1.getSelectedAbilitiesWeapon1())))
                .andExpect(jsonPath("$[0].selectedAbilitiesWeapon2", is(build1.getSelectedAbilitiesWeapon2())))
                .andExpect(jsonPath("$[0].attributeOptions", is(build1.getAttributeOptions())))

                .andExpect(jsonPath("$[1].primaryWeaponName", is("TestWeapon3")))
                .andExpect(jsonPath("$[1].secondaryWeaponName", is("TestWeapon4")))
                .andExpect(jsonPath("$[1].name", is("TestBuild2")))
                .andExpect(jsonPath("$[1].username", is("Tester2")))
                .andExpect(jsonPath("$[1].tag", is("PvP")))
                .andExpect(jsonPath("$[1].selectedAbilitiesWeapon1", is(build2.getSelectedAbilitiesWeapon1())))
                .andExpect(jsonPath("$[1].selectedAbilitiesWeapon2", is(build2.getSelectedAbilitiesWeapon2())))
                .andExpect(jsonPath("$[1].attributeOptions", is(build2.getAttributeOptions())));
    }

    @Test
    public void givenBuild_whenGetBuildByName_thenReturnJsonBuild() throws Exception {
        given(buildRepository.findByNameEquals("TestBuild")).willReturn(build1);

        mockMvc.perform(get("/builder/{name}", build1.getName()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primaryWeaponName", is("TestWeapon1")))
                .andExpect(jsonPath("$.secondaryWeaponName", is("TestWeapon2")))
                .andExpect(jsonPath("$.name", is("TestBuild")))
                .andExpect(jsonPath("$.username", is("Tester")))
                .andExpect(jsonPath("$.tag", is("PvE")))
                .andExpect(jsonPath("$.selectedAbilitiesWeapon1", is(build1.getSelectedAbilitiesWeapon1())))
                .andExpect(jsonPath("$.selectedAbilitiesWeapon2", is(build1.getSelectedAbilitiesWeapon2())))
                .andExpect(jsonPath("$.attributeOptions", is(build1.getAttributeOptions())));
    }

    @Test
    public void givenBuilds_whenGetBuildsByName_thenReturnJsonBuilds() throws Exception {
        given(buildRepository.findAllByNameIgnoreCaseContaining("TestBuild")).willReturn(allBuilds);

        mockMvc.perform(get("/builders/{name}", "TestBuild"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].primaryWeaponName", is("TestWeapon1")))
                .andExpect(jsonPath("$[0].secondaryWeaponName", is("TestWeapon2")))
                .andExpect(jsonPath("$[0].name", is("TestBuild")))
                .andExpect(jsonPath("$[0].username", is("Tester")))
                .andExpect(jsonPath("$[0].tag", is("PvE")))
                .andExpect(jsonPath("$[0].selectedAbilitiesWeapon1", is(build1.getSelectedAbilitiesWeapon1())))
                .andExpect(jsonPath("$[0].selectedAbilitiesWeapon2", is(build1.getSelectedAbilitiesWeapon2())))
                .andExpect(jsonPath("$[0].attributeOptions", is(build1.getAttributeOptions())))

                .andExpect(jsonPath("$[1].primaryWeaponName", is("TestWeapon3")))
                .andExpect(jsonPath("$[1].secondaryWeaponName", is("TestWeapon4")))
                .andExpect(jsonPath("$[1].name", is("TestBuild2")))
                .andExpect(jsonPath("$[1].username", is("Tester2")))
                .andExpect(jsonPath("$[1].tag", is("PvP")))
                .andExpect(jsonPath("$[1].selectedAbilitiesWeapon1", is(build2.getSelectedAbilitiesWeapon1())))
                .andExpect(jsonPath("$[1].selectedAbilitiesWeapon2", is(build2.getSelectedAbilitiesWeapon2())))
                .andExpect(jsonPath("$[1].attributeOptions", is(build2.getAttributeOptions())));
    }

    @Test
    public void givenBuilds_whenGetBuildsByUsername_thenReturnJsonBuilds() throws Exception {
        given(buildRepository.findAllByUsernameIgnoreCaseContaining("Tester")).willReturn(allBuilds);

        mockMvc.perform(get("/builders/user/{name}", "Tester"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].primaryWeaponName", is("TestWeapon1")))
                .andExpect(jsonPath("$[0].secondaryWeaponName", is("TestWeapon2")))
                .andExpect(jsonPath("$[0].name", is("TestBuild")))
                .andExpect(jsonPath("$[0].username", is("Tester")))
                .andExpect(jsonPath("$[0].tag", is("PvE")))
                .andExpect(jsonPath("$[0].selectedAbilitiesWeapon1", is(build1.getSelectedAbilitiesWeapon1())))
                .andExpect(jsonPath("$[0].selectedAbilitiesWeapon2", is(build1.getSelectedAbilitiesWeapon2())))
                .andExpect(jsonPath("$[0].attributeOptions", is(build1.getAttributeOptions())))

                .andExpect(jsonPath("$[1].primaryWeaponName", is("TestWeapon3")))
                .andExpect(jsonPath("$[1].secondaryWeaponName", is("TestWeapon4")))
                .andExpect(jsonPath("$[1].name", is("TestBuild2")))
                .andExpect(jsonPath("$[1].username", is("Tester2")))
                .andExpect(jsonPath("$[1].tag", is("PvP")))
                .andExpect(jsonPath("$[1].selectedAbilitiesWeapon1", is(build2.getSelectedAbilitiesWeapon1())))
                .andExpect(jsonPath("$[1].selectedAbilitiesWeapon2", is(build2.getSelectedAbilitiesWeapon2())))
                .andExpect(jsonPath("$[1].attributeOptions", is(build2.getAttributeOptions())));
    }

    @Test
    public void givenBuilds_whenGetBuildsByWeapon_thenReturnJsonBuilds() throws Exception {
        given(buildRepository.findAllByPrimaryWeaponNameIgnoreCaseContainingOrSecondaryWeaponNameIgnoreCaseContaining("TestWeapon", "TestWeapon")).willReturn(allBuilds);

        mockMvc.perform(get("/builders/weapon/{name}", "TestWeapon"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].primaryWeaponName", is("TestWeapon1")))
                .andExpect(jsonPath("$[0].secondaryWeaponName", is("TestWeapon2")))
                .andExpect(jsonPath("$[0].name", is("TestBuild")))
                .andExpect(jsonPath("$[0].username", is("Tester")))
                .andExpect(jsonPath("$[0].tag", is("PvE")))
                .andExpect(jsonPath("$[0].selectedAbilitiesWeapon1", is(build1.getSelectedAbilitiesWeapon1())))
                .andExpect(jsonPath("$[0].selectedAbilitiesWeapon2", is(build1.getSelectedAbilitiesWeapon2())))
                .andExpect(jsonPath("$[0].attributeOptions", is(build1.getAttributeOptions())))

                .andExpect(jsonPath("$[1].primaryWeaponName", is("TestWeapon3")))
                .andExpect(jsonPath("$[1].secondaryWeaponName", is("TestWeapon4")))
                .andExpect(jsonPath("$[1].name", is("TestBuild2")))
                .andExpect(jsonPath("$[1].username", is("Tester2")))
                .andExpect(jsonPath("$[1].tag", is("PvP")))
                .andExpect(jsonPath("$[1].selectedAbilitiesWeapon1", is(build2.getSelectedAbilitiesWeapon1())))
                .andExpect(jsonPath("$[1].selectedAbilitiesWeapon2", is(build2.getSelectedAbilitiesWeapon2())))
                .andExpect(jsonPath("$[1].attributeOptions", is(build2.getAttributeOptions())));
    }

    @Test
    public void givenBuilds_whenGetBuildsByTag_thenReturnJsonBuilds() throws Exception {
        given(buildRepository.findAllByTagEquals("Pv")).willReturn(allBuilds);

        mockMvc.perform(get("/builders/tag/{name}", "Pv"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].primaryWeaponName", is("TestWeapon1")))
                .andExpect(jsonPath("$[0].secondaryWeaponName", is("TestWeapon2")))
                .andExpect(jsonPath("$[0].name", is("TestBuild")))
                .andExpect(jsonPath("$[0].username", is("Tester")))
                .andExpect(jsonPath("$[0].tag", is("PvE")))
                .andExpect(jsonPath("$[0].selectedAbilitiesWeapon1", is(build1.getSelectedAbilitiesWeapon1())))
                .andExpect(jsonPath("$[0].selectedAbilitiesWeapon2", is(build1.getSelectedAbilitiesWeapon2())))
                .andExpect(jsonPath("$[0].attributeOptions", is(build1.getAttributeOptions())))

                .andExpect(jsonPath("$[1].primaryWeaponName", is("TestWeapon3")))
                .andExpect(jsonPath("$[1].secondaryWeaponName", is("TestWeapon4")))
                .andExpect(jsonPath("$[1].name", is("TestBuild2")))
                .andExpect(jsonPath("$[1].username", is("Tester2")))
                .andExpect(jsonPath("$[1].tag", is("PvP")))
                .andExpect(jsonPath("$[1].selectedAbilitiesWeapon1", is(build2.getSelectedAbilitiesWeapon1())))
                .andExpect(jsonPath("$[1].selectedAbilitiesWeapon2", is(build2.getSelectedAbilitiesWeapon2())))
                .andExpect(jsonPath("$[1].attributeOptions", is(build2.getAttributeOptions())));
    }

    @Test
    public void whenCreateBuild_thenReturnJsonBuild() throws Exception {
        Build build3 = new Build("TestWeapon5", "TestWeapon6", "TestBuild3", "Tester3", Tag.War, Arrays.asList(13,14,15), Arrays.asList(16,17,18), Arrays.asList(7, 8, 9, 300, 300));

        when(buildRepository.save(any(Build.class))).thenReturn(build3);

        mockMvc.perform(post("/builder")
                .content(mapper.writeValueAsString(build3))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primaryWeaponName", is("TestWeapon5")))
                .andExpect(jsonPath("$.secondaryWeaponName", is("TestWeapon6")))
                .andExpect(jsonPath("$.name", is("TestBuild3")))
                .andExpect(jsonPath("$.username", is("Tester3")))
                .andExpect(jsonPath("$.tag", is("War")))
                .andExpect(jsonPath("$.selectedAbilitiesWeapon1", is(build3.getSelectedAbilitiesWeapon1())))
                .andExpect(jsonPath("$.selectedAbilitiesWeapon2", is(build3.getSelectedAbilitiesWeapon2())))
                .andExpect(jsonPath("$.attributeOptions", is(build3.getAttributeOptions())));
    }

    @Test
    public void whenPutBuild_thenReturnJsonBuild() throws Exception {
        Build build3 = new Build("TestWeapon5", "TestWeapon6", "TestBuild3", "Tester3", Tag.War, Arrays.asList(13,14,15), Arrays.asList(16,17,18), Arrays.asList(7, 8, 9, 300, 300));

        given(buildRepository.findByNameEquals("TestBuild3")).willReturn(build3);

        Build updatedBuild = new Build("TestWeapon5", "TestWeapon6", "TestBuild3", "Luuk", Tag.War, Arrays.asList(13,14,15), Arrays.asList(16,17,18), Arrays.asList(7, 8, 9, 300, 300));

        when(buildRepository.save(any(Build.class))).thenReturn(updatedBuild);

        mockMvc.perform(put("/builder/{name}", "TestBuild3")
                .content(mapper.writeValueAsString(updatedBuild))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primaryWeaponName", is("TestWeapon5")))
                .andExpect(jsonPath("$.secondaryWeaponName", is("TestWeapon6")))
                .andExpect(jsonPath("$.name", is("TestBuild3")))
                .andExpect(jsonPath("$.username", is("Luuk")))
                .andExpect(jsonPath("$.tag", is("War")))
                .andExpect(jsonPath("$.selectedAbilitiesWeapon1", is(build3.getSelectedAbilitiesWeapon1())))
                .andExpect(jsonPath("$.selectedAbilitiesWeapon2", is(build3.getSelectedAbilitiesWeapon2())))
                .andExpect(jsonPath("$.attributeOptions", is(build3.getAttributeOptions())));
    }

    @Test
    public void givenBuild_whenDeleteBuild_thenStatusOk() throws Exception {
        Build buildToBeDeleted = new Build("TestWeapon10", "TestWeapon11", "TestBuild10", "Tester10", Tag.General, Arrays.asList(100,101,102), Arrays.asList(103,104,105), Arrays.asList(100,101,102,500,500));

        given(buildRepository.findByNameEquals("TestBuild10")).willReturn(buildToBeDeleted);


        mockMvc.perform(delete("/builder/{name}", "TestBuild10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoBuild_whenDeleteBuild_thenStatusNotFound() throws Exception {
        given(buildRepository.findByNameEquals("NonExistent")).willReturn(null);

        mockMvc.perform(delete("/builder/{name}", "NonExistent")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
