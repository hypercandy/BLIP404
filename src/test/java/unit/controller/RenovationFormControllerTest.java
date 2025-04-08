package nz.ac.canterbury.seng302.homehelper.unit.controller;


import nz.ac.canterbury.seng302.homehelper.controller.RenovationFormController;
import nz.ac.canterbury.seng302.homehelper.entity.RenovationFormResult;
import nz.ac.canterbury.seng302.homehelper.service.RenovationFormService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;

public class RenovationFormControllerTest {

    @Test
    void testRenovationFormController_DefaultValues() {
        RenovationFormService renovationFormServiceSpy = Mockito.mock(RenovationFormService.class);
        RenovationFormController renovationFormController = new RenovationFormController(renovationFormServiceSpy);
        Model model = Mockito.mock(Model.class);

        String viewName = renovationFormController.renovationForm("", "",
                null ,null, model);

        Assertions.assertEquals("createRenovationRecord", viewName);
        Mockito.verify(model).addAttribute("name", "");
        Mockito.verify(model).addAttribute("description", "");
        Mockito.verify(model).addAttribute("rooms", new ArrayList<>());
        Mockito.verify(model).addAttribute("errorMessage", null);
    }

    @Test
    void testRenovationFormController_Parameters() {
        RenovationFormService renovationFormServiceSpy = Mockito.mock(RenovationFormService.class);
        RenovationFormController renovationFormController = new RenovationFormController(renovationFormServiceSpy);
        Model model = Mockito.mock(Model.class);


        String[] rooms = {"Kitchen"};
        String viewName = renovationFormController.renovationForm("Kitchen Upgrade", "Renovating the floor",
              rooms ,null, model);

        Assertions.assertEquals("createRenovationRecord", viewName);
        Mockito.verify(model).addAttribute("name", "Kitchen Upgrade");
        Mockito.verify(model).addAttribute("description", "Renovating the floor");
        Mockito.verify(model).addAttribute("rooms", List.of(rooms));
        Mockito.verify(model).addAttribute("errorMessage", null);
    }

    @Test
    void testSubmitRenovationForm_Failure() {
        RenovationFormService renovationFormServiceSpy = Mockito.mock(RenovationFormService.class);
        RenovationFormController renovationFormController = new RenovationFormController(renovationFormServiceSpy);
        Model model = Mockito.mock(Model.class);

        Mockito.doThrow(new IllegalArgumentException("Invalid entry"))
                .when(renovationFormServiceSpy)
                .addRenovationFormResult(Mockito.any());

        String[] rooms = {"Kitchen"};
        String viewName = renovationFormController.postRenovationForm("Invalid Renovation", "Renovating the floor",
                rooms, "Submit", "", model);

        Assertions.assertEquals("createRenovationRecord", viewName);
        Mockito.verify(model).addAttribute("name", "Invalid Renovation");
        Mockito.verify(model).addAttribute("description", "Renovating the floor");
        Mockito.verify(model).addAttribute("rooms", List.of(rooms));
        Mockito.verify(model).addAttribute("errorMessage", "Invalid entry");
    }

    @Test
    void testSubmitRenovationForm_Success() {
        RenovationFormService renovationFormServiceSpy = Mockito.mock(RenovationFormService.class);
        RenovationFormController renovationFormController = new RenovationFormController(renovationFormServiceSpy);
        Model model = Mockito.mock(Model.class);

        Mockito.when(renovationFormServiceSpy.addRenovationFormResult(Mockito.any(RenovationFormResult.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        String[] rooms = {"Kitchen"};
        String viewName = renovationFormController.postRenovationForm("Kitchen Renovation", "Renovating the floor",
                rooms, "Submit", "", model);
        Assertions.assertEquals("redirect:/renovations/0", viewName);
    }

    @Test
    void testAddRooms_Success() {
        String[] rooms = {"Kitchen"};
        RenovationFormService renovationFormServiceSpy = Mockito.mock(RenovationFormService.class);
        RenovationFormController renovationFormController = new RenovationFormController(renovationFormServiceSpy);
        Model model = Mockito.mock(Model.class);
        String viewName = renovationFormController.postRenovationForm("Kitchen Renovation", "Renovating the floor",
                rooms, "Add Room", "Bathroom", model);
        Assertions.assertEquals("createRenovationRecord", viewName);
        Mockito.verify(model).addAttribute("name", "Kitchen Renovation");
        Mockito.verify(model).addAttribute("description", "Renovating the floor");
        Mockito.verify(model).addAttribute("rooms", List.of("Kitchen", "Bathroom"));

    }

    @Test
    void testAddRooms_InputPersist() {
        String[] rooms = {"Kitchen"};
        RenovationFormService renovationFormServiceSpy = Mockito.mock(RenovationFormService.class);
        RenovationFormController renovationFormController = new RenovationFormController(renovationFormServiceSpy);
        Model model = Mockito.mock(Model.class);

        Mockito.doThrow(new IllegalArgumentException("Invalid room name"))
                .when(renovationFormServiceSpy)
                .validateRoom(Mockito.anyList(), Mockito.anyString());

        String viewName = renovationFormController.postRenovationForm("Bathroom Renovation", "New Flooring", rooms, "Add Room", "Bathroom!", model);

        Assertions.assertEquals("createRenovationRecord", viewName);
        Mockito.verify(model).addAttribute("name", "Bathroom Renovation");
        Mockito.verify(model).addAttribute("description", "New Flooring");
        Mockito.verify(model).addAttribute("rooms", List.of("Kitchen"));
        Mockito.verify(model).addAttribute("roomInput", "Bathroom!");
        Mockito.verify(model).addAttribute("errorMessage", "Invalid room name");
    }
}
