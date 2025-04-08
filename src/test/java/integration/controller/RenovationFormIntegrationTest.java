package nz.ac.canterbury.seng302.homehelper.integration.controller;
import nz.ac.canterbury.seng302.homehelper.controller.RenovationFormController;
import nz.ac.canterbury.seng302.homehelper.entity.RenovationFormResult;
import nz.ac.canterbury.seng302.homehelper.repository.RenovationFormRepository;
import nz.ac.canterbury.seng302.homehelper.service.RenovationFormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class RenovationFormIntegrationTest {

    @Autowired
    private RenovationFormController renovationFormController;

    @SpyBean
    private RenovationFormService renovationFormService;

    @MockBean
    private RenovationFormRepository renovationFormRepository;

    MockMvc mockMvc;


    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(renovationFormController).build();
    }

    @Test
    void getRenovationFormTest_defaultValues() throws Exception {
        mockMvc.perform(get("/renovations/new")).andExpect(status().isOk())
                .andExpect(model().attribute("name", ""))
                .andExpect(model().attribute("description", ""))
                .andExpect(model().attributeDoesNotExist("errorMessage"));

    }

    @Test
    void getRenovationFormTest_Parameters() throws Exception {
        mockMvc.perform(get("/renovations/new")
                        .param("name", "Kitchen Upgrade")
                        .param("description", "Renovating the floor")
                        .param("rooms[]", "Kitchen")
                        .param("errorMessages", "Invalid input"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("name", "Kitchen Upgrade"))
                .andExpect(model().attribute("description", "Renovating the floor"))
                .andExpect(model().attribute("rooms", new ArrayList<>(List.of("Kitchen"))))
                .andExpect(model().attribute("errorMessage", "Invalid input"));

    }

    @Test
    void submitRenovationFormTest_Success() throws Exception {
        Mockito.when(renovationFormRepository.save(Mockito.any(RenovationFormResult.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        mockMvc.perform(post("/renovations/new")
                .param("name", "Kitchen Upgrade")
                .param("description", "Renovating the floor")
                .param("rooms[]", "Kitchen")
                .param("action", "Submit"))
                .andExpect(status().isFound());
        ArgumentCaptor<RenovationFormResult> formCaptor = ArgumentCaptor.forClass(RenovationFormResult.class);
        verify(renovationFormService).addRenovationFormResult(formCaptor.capture());
        RenovationFormResult capturedForm = formCaptor.getValue();
        assertEquals("Kitchen Upgrade", capturedForm.getName());
        assertEquals("Renovating the floor", capturedForm.getDescription());
        assertEquals(List.of("Kitchen"), capturedForm.getRooms());
        verify(renovationFormRepository).save(Mockito.any(RenovationFormResult.class));

    }

    @Test
    void testUpdateRenovationRecord_Success() throws Exception {
        RenovationFormResult mockRenovation = new RenovationFormResult("Kitchen Upgrade", "Renovating the floor", List.of("Kitchen"));
        Mockito.when(renovationFormRepository.findById(mockRenovation.getId())).thenReturn(Optional.of(mockRenovation));
        Mockito.when(renovationFormRepository.save(Mockito.any(RenovationFormResult.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        mockMvc.perform(post("/renovation/edit/" + mockRenovation.getId())
                        .param("name", "Kitchen Upgrade")
                        .param("description", "Edited description")
                        .param("rooms[]", "Edited rooms")
                        .param("action", "Submit"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/renovations/" + mockRenovation.getId()));

        ArgumentCaptor<RenovationFormResult> formCaptor = ArgumentCaptor.forClass(RenovationFormResult.class);
        verify(renovationFormService).addRenovationFormResult(formCaptor.capture());
        RenovationFormResult capturedForm = formCaptor.getValue();
        assertEquals("Kitchen Upgrade", capturedForm.getName());
        assertEquals("Edited description", capturedForm.getDescription());
        assertEquals(List.of("Edited rooms"), capturedForm.getRooms());
        verify(renovationFormRepository).save(Mockito.any(RenovationFormResult.class));
    }

    @Test
    void testUpdateRenovationRecord_Failure() throws Exception {
        RenovationFormResult mockRenovation = new RenovationFormResult("Invalid input!", "Renovating the floor", List.of("Kitchen"));
        Mockito.when(renovationFormRepository.findById(mockRenovation.getId())).thenReturn(Optional.of(mockRenovation));
        mockMvc.perform(post("/renovation/edit/" + mockRenovation.getId())
                        .param("name", "Invalid input!")
                        .param("description", "Edited description")
                        .param("rooms[]", "Edited rooms")
                        .param("action", "Submit"))
                .andExpect(status().isOk())
                .andExpect(view().name("editRenovation"));

        verify(renovationFormRepository, Mockito.never()).save(Mockito.any(RenovationFormResult.class));

    }


    @Test
    void getListRecords_EmptyRepository_Success() throws Exception {
        List<RenovationFormResult> expected = List.of();

        Mockito.when(renovationFormRepository.findAll()).thenReturn(null);
        mockMvc.perform(get("/renovations"))
                .andExpect(status().isOk())
                .andExpect(view().name("recordsList"))
                .andExpect(model().attribute("records", expected));
        Mockito.verify(renovationFormRepository).findAll();
    }

    @Test
    void getListRecords_ValidRepository_Success() throws Exception {
        List<RenovationFormResult> expected = List.of(new RenovationFormResult("Name", "Description", List.of("Room 1")));

        Mockito.when(renovationFormRepository.findAll()).thenReturn(expected);
        mockMvc.perform(get("/renovations"))
                .andExpect(status().isOk())
                .andExpect(view().name("recordsList"))
                .andExpect(model().attribute("records", expected));
        Mockito.verify(renovationFormRepository).findAll();
    }

}


