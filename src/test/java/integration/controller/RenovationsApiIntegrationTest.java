package nz.ac.canterbury.seng302.homehelper.integration.controller;

import nz.ac.canterbury.seng302.homehelper.controller.RenovationsApiController;
import nz.ac.canterbury.seng302.homehelper.entity.RenovationFormResult;
import nz.ac.canterbury.seng302.homehelper.service.RenovationFormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
public class RenovationsApiIntegrationTest {

    @Autowired
    private RenovationsApiController renovationsApiController;


    @Autowired
    private RenovationFormService renovationFormService;


    MockMvc mockMvc;


    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(renovationsApiController).build();
    }

    @Test
    void deleteRecord_recordIdDoesNotExist_FailsSilently() throws Exception {
        mockMvc.perform(delete("/renovations/1000000")).andExpect(status().isOk());
    }

    @Test
    void deleteRecord_recordIdDoesExist_redirectedToListAndRecordDelete() throws Exception {
        var record = new RenovationFormResult("Name", "Description", List.of("Room 1"));
        var id = renovationFormService.addRenovationFormResult(record).getId();
        mockMvc.perform(delete("/renovations/" + id)).andExpect(status().isOk());
        assertFalse(renovationFormService.getRenovationRecords().iterator().hasNext());
    }

}


