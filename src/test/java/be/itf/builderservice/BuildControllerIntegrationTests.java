package be.itf.builderservice;

import be.itf.builderservice.model.Build;
import be.itf.builderservice.model.Tag;
import be.itf.builderservice.repository.BuildRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BuildControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BuildRepository buildRepository;
    
    private ObjectMapper mapper = new ObjectMapper();

    private Build build1 = new Build("TestWeapon1", "TestWeapon2", "Test", "Luuk", Tag.PVE);
    private Build build2 = new Build("TestWeapon3", "TestWeapon4", "Test2", "Luuk2", Tag.PVP);
    private List<Integer> test1 = new ArrayList<>();
    private List<Integer> test2 = new ArrayList<>();

    @BeforeEach
    public void beforeAllTests() {
        buildRepository.deleteAll();

        test1.add(1);
        test1.add(2);
        test1.add(3);
        test1.add(100);
        test1.add(100);
        build1.setAttributeOptions(test1);
        build1.setSelectedAbilitiesWeapon1(test1);
        build1.setSelectedAbilitiesWeapon2(test1);
        buildRepository.save(build1);

        test2.add(4);
        test2.add(5);
        test2.add(6);
        test2.add(200);
        test2.add(200);
        build2.setAttributeOptions(test2);
        build2.setSelectedAbilitiesWeapon1(test2);
        build2.setSelectedAbilitiesWeapon2(test2);
        buildRepository.save(build2);
    }

    @AfterEach
    public void afterAllTests() {
        buildRepository.deleteAll();
    }

    public void parameterizedTest(String url, String vars) throws Exception {
        List<Integer> testList1 = new ArrayList<>();
        testList1.add(1);
        testList1.add(2);
        testList1.add(3);
        testList1.add(100);
        testList1.add(100);

        List<Integer> testList2 = new ArrayList<>();
        testList2.add(4);
        testList2.add(5);
        testList2.add(6);
        testList2.add(200);
        testList2.add(200);

        mockMvc.perform(get(url, vars))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].primaryWeaponName", is("TestWeapon1")))
                .andExpect(jsonPath("$[0].secondaryWeaponName", is("TestWeapon2")))
                .andExpect(jsonPath("$[0].name", is("Test")))
                .andExpect(jsonPath("$[0].username", is("Luuk")))
                .andExpect(jsonPath("$[0].tag", is("PVE")))
                .andExpect(jsonPath("$[0].selectedAbilitiesWeapon1", is(testList1)))
                .andExpect(jsonPath("$[0].selectedAbilitiesWeapon2", is(testList1)))
                .andExpect(jsonPath("$[0].attributeOptions", is(testList1)))

                .andExpect(jsonPath("$[1].primaryWeaponName", is("TestWeapon3")))
                .andExpect(jsonPath("$[1].secondaryWeaponName", is("TestWeapon4")))
                .andExpect(jsonPath("$[1].name", is("Test2")))
                .andExpect(jsonPath("$[1].username", is("Luuk2")))
                .andExpect(jsonPath("$[1].tag", is("PVP")))
                .andExpect(jsonPath("$[1].selectedAbilitiesWeapon1", is(testList2)))
                .andExpect(jsonPath("$[1].selectedAbilitiesWeapon2", is(testList2)))
                .andExpect(jsonPath("$[1].attributeOptions", is(testList2)));
    }

    @Test
    void givenBuilds_whenGetAllBuilds_thenReturnJsonBuilds() throws Exception {
        parameterizedTest("/builders", null);
    }

    @Test
    void givenBuild_whenGetBuildByName_thenReturnJsonBuild() throws Exception {
        List<Integer> testList1 = new ArrayList<>();
        testList1.add(1);
        testList1.add(2);
        testList1.add(3);
        testList1.add(100);
        testList1.add(100);

        mockMvc.perform(get("/builder/{name}", build1.getName()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primaryWeaponName", is("TestWeapon1")))
                .andExpect(jsonPath("$.secondaryWeaponName", is("TestWeapon2")))
                .andExpect(jsonPath("$.name", is("Test")))
                .andExpect(jsonPath("$.username", is("Luuk")))
                .andExpect(jsonPath("$.tag", is("PVE")))
                .andExpect(jsonPath("$.selectedAbilitiesWeapon1", is(testList1)))
                .andExpect(jsonPath("$.selectedAbilitiesWeapon2", is(testList1)))
                .andExpect(jsonPath("$.attributeOptions", is(testList1)));
    }

    @Test
    void givenBuilds_whenGetBuildsByName_thenReturnJsonBuilds() throws Exception {
        parameterizedTest("/builders/{name}", "Test");
    }

    @Test
    void givenBuilds_whenGetBuildsByUsername_thenReturnJsonBuilds() throws Exception {
        parameterizedTest("/builders/user/{name}", "Luuk");
    }

    @Test
    void givenBuilds_whenGetBuildsByWeapon_thenReturnJsonBuilds() throws Exception {
        parameterizedTest("/builders/weapon/{name}", "TestWeapon");
    }

    @Test
    void givenBuilds_whenGetBuildsByTag_thenReturnJsonBuilds() throws Exception {
        List<Integer> testList1 = new ArrayList<>();
        testList1.add(1);
        testList1.add(2);
        testList1.add(3);
        testList1.add(100);
        testList1.add(100);

        mockMvc.perform(get("/builders/tag/{name}", "PVE"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].primaryWeaponName", is("TestWeapon1")))
                .andExpect(jsonPath("$[0].secondaryWeaponName", is("TestWeapon2")))
                .andExpect(jsonPath("$[0].name", is("Test")))
                .andExpect(jsonPath("$[0].username", is("Luuk")))
                .andExpect(jsonPath("$[0].tag", is("PVE")))
                .andExpect(jsonPath("$[0].selectedAbilitiesWeapon1", is(testList1)))
                .andExpect(jsonPath("$[0].selectedAbilitiesWeapon2", is(testList1)))
                .andExpect(jsonPath("$[0].attributeOptions", is(testList1)));
    }

    @Test
    void whenCreateBuild_thenReturnJsonBuild() throws Exception {
        Build build3 = new Build("TestWeapon5", "TestWeapon6", "Test3", "Luuk3", Tag.WAR);
        List<Integer> testList3 = new ArrayList<>();
        testList3.add(7);
        testList3.add(8);
        testList3.add(9);
        testList3.add(300);
        testList3.add(300);
        build3.setAttributeOptions(testList3);
        build3.setSelectedAbilitiesWeapon1(testList3);
        build3.setSelectedAbilitiesWeapon2(testList3);

        mockMvc.perform(post("/builder")
                .content(mapper.writeValueAsString(build3))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primaryWeaponName", is("TestWeapon5")))
                .andExpect(jsonPath("$.secondaryWeaponName", is("TestWeapon6")))
                .andExpect(jsonPath("$.name", is("Test3")))
                .andExpect(jsonPath("$.username", is("Luuk3")))
                .andExpect(jsonPath("$.tag", is("WAR")))
                .andExpect(jsonPath("$.selectedAbilitiesWeapon1", is(testList3)))
                .andExpect(jsonPath("$.selectedAbilitiesWeapon2", is(testList3)))
                .andExpect(jsonPath("$.attributeOptions", is(testList3)));
    }

    @Test
    void whenPutBuild_thenReturnJsonBuild() throws Exception {
        Build build3 = new Build("TestWeapon5", "TestWeapon6", "Test3", "Luuk3", Tag.WAR);
        List<Integer> testList3 = new ArrayList<>();
        testList3.add(7);
        testList3.add(8);
        testList3.add(9);
        testList3.add(300);
        testList3.add(300);
        build3.setAttributeOptions(testList3);
        build3.setSelectedAbilitiesWeapon1(testList3);
        build3.setSelectedAbilitiesWeapon2(testList3);

        mockMvc.perform(put("/builder/{name}", "Test")
                .content(mapper.writeValueAsString(build3))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primaryWeaponName", is("TestWeapon5")))
                .andExpect(jsonPath("$.secondaryWeaponName", is("TestWeapon6")))
                .andExpect(jsonPath("$.name", is("Test3")))
                .andExpect(jsonPath("$.username", is("Luuk3")))
                .andExpect(jsonPath("$.tag", is("WAR")))
                .andExpect(jsonPath("$.selectedAbilitiesWeapon1", is(testList3)))
                .andExpect(jsonPath("$.selectedAbilitiesWeapon2", is(testList3)))
                .andExpect(jsonPath("$.attributeOptions", is(testList3)));
    }

    @Test
    void givenBuild_whenDeleteBuild_thenStatusOk() throws Exception {
        mockMvc.perform(delete("/builder/{name}", "Test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenNoBuild_whenDeleteBuild_thenStatusNotFound() throws Exception {
        mockMvc.perform(delete("/builder/{name}", "NonExistent")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
