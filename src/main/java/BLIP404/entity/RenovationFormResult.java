//package BLIP404.entity;
//
//import jakarta.persistence.*;
//import nz.ac.canterbury.seng302.homehelper.BLIP404.controller.RenovationsApiController;
//
//import java.util.List;
//
///**
// * Entity to represent a renovation: name, description and rooms
// */
//@Entity
//public class RenovationFormResult {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(nullable = false, length = 6000)
//    private String description;
//
//    @ElementCollection
//    private List<String> rooms;
//
//    protected  RenovationFormResult() {}
//
//    /**
//     * Creates a new renovation form result
//     * @param name name of the renovation
//     * @param description description of the renovation
//     * @param rooms rooms included in the renovation
//     */
//    public RenovationFormResult(String name, String description, List<String> rooms) {
//        this.name = name;
//        this.description = description;
//        this.rooms = rooms;
//    }
//
//    /**
//     * Creates a new renovation form result with id
//     * @param name name of the renovation
//     * @param description description of the renovation
//     * @param rooms rooms included in the renovation
//     */
//    public RenovationFormResult(Long id, String name, String description, List<String> rooms) {
//        this(name, description, rooms);
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getDescription() { return description; }
//
//    public List<String> getRooms() { return rooms; }
//
//    @Override
//    public String toString() {
//        return "RenovationFormResult [id=" + id + ", name=" + name + ", description=" + description + ", rooms=" + rooms + "]";
//    }
//
//}
