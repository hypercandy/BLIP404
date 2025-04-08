//package nz.ac.canterbury.seng302.homehelper.unit.service;
//
//import nz.ac.canterbury.seng302.homehelper.entity.RenovationFormResult;
//import nz.ac.canterbury.seng302.homehelper.repository.RenovationFormRepository;
//import nz.ac.canterbury.seng302.homehelper.service.RenovationFormService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class RenovationFormServiceTest {
//    @Mock
//    private RenovationFormRepository renovationFormRepository;
//
//    @InjectMocks
//    private RenovationFormService renovationFormService;
//
//    private RenovationFormResult renovationFormResult;
//
//    @Test
//    public void testAddRenovationFormResult_Accepted() {
//        renovationFormResult = new RenovationFormResult("Bathroom Renovation", "New Flooring", List.of("Bathroom"));
//        when(renovationFormRepository.existsByName(renovationFormResult.getName())).thenReturn(false);
//        when(renovationFormRepository.save(renovationFormResult)).thenReturn(renovationFormResult);
//
//        RenovationFormResult result = renovationFormService.addRenovationFormResult(renovationFormResult);
//
//        assertNotNull(result);
//        assertEquals(renovationFormResult, result);
//    }
//
//    @Test
//    public void testAddRenovationFormResult_EmptyName() {
//        renovationFormResult = new RenovationFormResult(" ", "New Flooring", List.of("Bathroom"));
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> renovationFormService.addRenovationFormResult(renovationFormResult));
//        assertTrue(exception.getMessage().contains("Renovation record name cannot be empty"));
//    }
//
//    @Test
//    public void testAddRenovationFormResult_NullName() {
//        renovationFormResult = new RenovationFormResult(null, "New Flooring", List.of("Bathroom"));
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> renovationFormService.addRenovationFormResult(renovationFormResult));
//        assertTrue(exception.getMessage().contains("Renovation record name cannot be empty"));
//    }
//
//    @Test
//    public void testAddRenovationFormResult_InvalidCharactersName() {
//        renovationFormResult = new RenovationFormResult("Bathroom Renovation!", "New Flooring", List.of("Bathroom"));
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> renovationFormService.addRenovationFormResult(renovationFormResult));
//        assertTrue(exception.getMessage().contains("Renovation record name must only include letters, numbers, spaces, dots, hyphens or apostrophes"));
//    }
//
//    @Test
//    public void testAddRenovationFormResult_DuplicateName() {
//        renovationFormResult = new RenovationFormResult("Bathroom Renovation!", "New Flooring", List.of("Bathroom"));
//        when(renovationFormRepository.existsByName(renovationFormResult.getName())).thenReturn(true);
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> renovationFormService.addRenovationFormResult(renovationFormResult));
//        assertTrue(exception.getMessage().contains("Renovation record name already exists"));
//    }
//
//    @Test
//    public void testIsRenovationUnique_SameName() {
//        renovationFormResult = new RenovationFormResult("Bathroom Renovation", "New Flooring", List.of("Bathroom"));
//        when(renovationFormService.getRenovationFormResultById(renovationFormResult.getId())).thenReturn(Optional.of(renovationFormResult));
//
//    }
//
//    @Test
//    public void testIsRenovationUnique_ChangedName_DuplicateName() {
//        renovationFormResult = new RenovationFormResult("Bathroom Renovation", "New Flooring", List.of("Bathroom"));
//        when(renovationFormRepository.existsByName(renovationFormResult.getName())).thenReturn(true);
//        assertFalse(renovationFormService.isRenovationUnique(renovationFormResult));
//    }
//
//    @Test
//    public void testAddRenovationFormResult_EmptyDescription() {
//        renovationFormResult = new RenovationFormResult("Bathroom Renovation", " ", List.of("Bathroom"));
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> renovationFormService.addRenovationFormResult(renovationFormResult));
//        assertTrue(exception.getMessage().contains("Renovation record description cannot be empty"));
//    }
//
//    @Test
//    public void testAddRenovationFormResult_OutOfCharacterLimit() {
//        renovationFormResult = new RenovationFormResult("Bathroom Renovation", "A".repeat(513), List.of("Bathroom"));
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> renovationFormService.addRenovationFormResult(renovationFormResult));
//        assertTrue(exception.getMessage().contains("Renovation record description must be 512 characters or less"));
//    }
//
//    @Test
//    public void testAddRenovationFormResult_InCharacterLimit() {
//        renovationFormResult = new RenovationFormResult("Bathroom Renovation", "A".repeat(512), List.of("Bathroom"));
//        when(renovationFormRepository.existsByName(renovationFormResult.getName())).thenReturn(false);
//        when(renovationFormRepository.save(renovationFormResult)).thenReturn(renovationFormResult);
//
//        RenovationFormResult result = renovationFormService.addRenovationFormResult(renovationFormResult);
//
//        assertNotNull(result);
//    }
//
//    @Test
//    public void testAddRenovationFormResult_EmojiOutOfCharacterLimit() {
//        renovationFormResult = new RenovationFormResult("Bathroom Renovation", "\uD83C\uDFF3\uFE0F\u200D\uD83C\uDF08".repeat(513), List.of("Bathroom"));
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> renovationFormService.addRenovationFormResult(renovationFormResult));
//        assertTrue(exception.getMessage().contains("Renovation record description must be 512 characters or less"));
//    }
//
//    @Test
//    public void testAddRenovationFormResult_EmojiInCharacterLimit() {
//        renovationFormResult = new RenovationFormResult("Bathroom Renovation", "\uD83C\uDFF3\uFE0F\u200D\uD83C\uDF08".repeat(512), List.of("Bathroom"));
//        when(renovationFormRepository.existsByName(renovationFormResult.getName())).thenReturn(false);
//        when(renovationFormRepository.save(renovationFormResult)).thenReturn(renovationFormResult);
//
//        RenovationFormResult result = renovationFormService.addRenovationFormResult(renovationFormResult);
//
//        assertNotNull(result);
//    }
//
//    @Test
//    public void testAddRenovationFormResult_ValidRoom() {
//        renovationFormResult = new RenovationFormResult("Bathroom Renovation", "New Flooring", List.of("日本語"));
//        when(renovationFormRepository.existsByName(renovationFormResult.getName())).thenReturn(false);
//        when(renovationFormRepository.save(renovationFormResult)).thenReturn(renovationFormResult);
//
//        RenovationFormResult result = renovationFormService.addRenovationFormResult(renovationFormResult);
//
//        assertNotNull(result);
//    }
//
//    @Test
//    public void testAddRenovationFormResult_EmptyRoom() {
//        renovationFormResult = new RenovationFormResult("Bathroom Renovation", "New Flooring", new ArrayList<>());
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> renovationFormService.validateRoom(renovationFormResult.getRooms(), " "));
//        assertTrue(exception.getMessage().contains("Renovation record room name cannot be empty"));
//    }
//
//    @Test
//    public void testAddRenovationFormResult_InvalidRoom() {
//        renovationFormResult = new RenovationFormResult("Bathroom Renovation", "New Flooring", new ArrayList<>());
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> renovationFormService.validateRoom(renovationFormResult.getRooms(), "Bathroom!"));
//        assertTrue(exception.getMessage().contains("Renovation record room names must only include letters, numbers, spaces, dots, hyphens or apostrophes"));
//    }
//
//    @Test
//    public void testAddRenovationFormResult_DuplicateRoomName() {
//        renovationFormResult = new RenovationFormResult("Bathroom Renovation", "New Flooring", new ArrayList<>());
//        renovationFormResult.getRooms().add("Bathroom");
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> renovationFormService.validateRoom(renovationFormResult.getRooms(), "    Bathroom"));
//        assertTrue(exception.getMessage().contains("Renovation record room name already exists"));
//    }
//}