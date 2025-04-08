//package nz.ac.canterbury.seng302.homehelper.BLIP404.service;
//
//import nz.ac.canterbury.seng302.homehelper.BLIP404.entity.RenovationFormResult;
//import nz.ac.canterbury.seng302.homehelper.BLIP404.repository.RenovationFormRepository;
//import java.util.Optional;
//import nz.ac.canterbury.seng302.homehelper.util.UnicodeUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Service class for handling RenovationFormResults
// */
//@Service
//public class RenovationFormService {
//    private final RenovationFormRepository renovationFormRepository;
//    private final static String validNameRegex = "^[\\p{L}0-9 .,\\-']*$";
//
//    @Autowired
//    public RenovationFormService(RenovationFormRepository renovationFormRepository) {
//        this.renovationFormRepository = renovationFormRepository;
//    }
//
//    /**
//     * Validates names and description of renovation record
//     * @param renovationFormResult object to persist
//     * @return list of errors
//     */
//    private List<String> validateRenovationRecord(RenovationFormResult renovationFormResult) {
//        List<String> errors = new ArrayList<>();
//
//        //Validate Renovation Record Name
//        if (renovationFormResult.getName() == null || renovationFormResult.getName().trim().isEmpty()) {
//            errors.add("Renovation record name cannot be empty");
//        } else if (!renovationFormResult.getName().matches(validNameRegex)) {
//            errors.add("Renovation record name must only include letters, numbers, spaces, dots, hyphens or apostrophes");
//        } else if (!isRenovationUnique(renovationFormResult))
//        {
//            errors.add("Renovation record name already exists");
//        }
//
//        //Validate Renovation Record Description
//        if (renovationFormResult.getDescription() == null || renovationFormResult.getDescription().trim().isEmpty()) {
//            errors.add("Renovation record description cannot be empty");
//        } else if (UnicodeUtils.getGraphemeLength(renovationFormResult.getDescription()) > 512) {
//            errors.add("Renovation record description must be 512 characters or less");
//        }
//
//        return errors;
//    }
//
//    public void validateRoom(List<String> rooms, String roomInput) {
//        if (roomInput == null || roomInput.trim().isEmpty()) {
//            throw new IllegalArgumentException("Renovation record room name cannot be empty");
//        } else if (!roomInput.matches(validNameRegex)) {
//            throw new IllegalArgumentException("Renovation record room names must only include letters, numbers, spaces, dots, hyphens or apostrophes");
//        } else if (rooms.contains(roomInput.trim())) {
//            throw new IllegalArgumentException("Renovation record room name already exists");
//        }
//    }
//
//    public Boolean isRenovationUnique(RenovationFormResult record) {
//        Optional<RenovationFormResult> existingRenovation = renovationFormRepository.findById(record.getId());
//        if(existingRenovation.isPresent() && existingRenovation.get().getName().equals(record.getName())) {
//            return true;
//        }
//        else {
//            return !renovationFormRepository.existsByName(record.getName());
//        }
//    }
//
//    /**
//     * Adds a renovation form result to persistence
//     *
//     * @param renovationFormResult object to persist
//     * @return the renovationFormResult object
//     */
//    public RenovationFormResult addRenovationFormResult(RenovationFormResult renovationFormResult) throws IllegalArgumentException {
//        List<String> renovationRecordErrors = validateRenovationRecord(renovationFormResult);
//
//        if (!renovationRecordErrors.isEmpty()) {
//            throw new IllegalArgumentException(String.join(". ", renovationRecordErrors));
//        }
//        return renovationFormRepository.save(renovationFormResult);
//    }
//
//    /**
//     * Retrieves a renovation form result by id.
//     *
//     * @param recordId the id of the record
//     * @return an optional of the renovationFormResult object
//     */
//    public Optional<RenovationFormResult> getRenovationFormResultById(long recordId) {
//        return renovationFormRepository.findById(recordId);
//    }
//
//    /**
//     * Gets all renovation records
//     * @return a list of all renovation records for the user
//     */
//    public Iterable<RenovationFormResult> getRenovationRecords() {
//        final var result = renovationFormRepository.findAll();
//        return result == null ? List.of() : result;
//    }
//
//    /**
//     * Deletes a renovation record.
//     * @param recordId the id of the record
//     */
//    public void deleteRenovationRecord(Long recordId) {
//        // TODO: Check that the user owns the record
//        renovationFormRepository.deleteById(recordId);
//    }
//
//
//}
