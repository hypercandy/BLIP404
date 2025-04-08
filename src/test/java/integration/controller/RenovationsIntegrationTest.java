package nz.ac.canterbury.seng302.homehelper.integration.controller;

import nz.ac.canterbury.seng302.homehelper.controller.RenovationsController;
import nz.ac.canterbury.seng302.homehelper.entity.RenovationFormResult;
import nz.ac.canterbury.seng302.homehelper.repository.RenovationFormRepository;
import nz.ac.canterbury.seng302.homehelper.service.RenovationFormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
public class RenovationsIntegrationTest {

    @Autowired
    private RenovationsController renovationsController;


    @Autowired
    private RenovationFormService renovationFormService;


    MockMvc mockMvc;


    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(renovationsController).build();
    }

    @Test
    void getRecord_recordIdDoesNotExist_NotFoundPageReturned() throws Exception {
        mockMvc.perform(get("/renovations/1000000")).andExpect(status().isOk())
                .andExpect(model().attribute("errorMessage", "Record with id 1000000 not found."))
                .andExpect(view().name("notFound"));
    }

    @Test
    void getRecord_recordIdInvalidFormat_Failure() throws Exception {
        mockMvc.perform(get("/renovations/invalid_string")).andExpect(status().is(404));
    }

    @Test
    void getRecord_recordIdDoesExist_PageReturned() throws Exception {
        var expected = new RenovationFormResult("Name", "Description", List.of("Room 1"));
        var id = renovationFormService.addRenovationFormResult(expected).getId();
        mockMvc.perform(get("/renovations/" + id)).andExpect(status().isOk())
                .andExpect(view().name("renovationView"))
                .andExpect(model().attributeExists("record"));
    }

    @Test
    void getRenovationEdit_recordDoesExist_PageReturned() throws Exception {
        var expected = new RenovationFormResult("Renovation name", "Description", List.of("Room 1"));
        var id = renovationFormService.addRenovationFormResult(expected).getId();
        mockMvc.perform(get("/renovations/edit/" + id)).andExpect(status().isOk())
                .andExpect(view().name("editRenovation"))
                .andExpect(model().attributeExists("name"))
                .andExpect(model().attributeExists("id"))
                .andExpect(model().attributeExists("description"))
                .andExpect(model().attributeExists("rooms"));

    }

    @Test
    void getRenovationEdit_recordDoesNotExist_NotFoundPageReturned() throws Exception {
        mockMvc.perform(get("/renovations/edit/1000000")).andExpect(status().isOk())
                .andExpect(model().attribute("errorMessage", "Record with id 1000000 not found."))
                .andExpect(view().name("notFound"));
    }


}


