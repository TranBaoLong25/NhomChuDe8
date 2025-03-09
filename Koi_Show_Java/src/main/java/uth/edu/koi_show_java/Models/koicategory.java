package uth.edu.koi_show_java.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity

public class koicategory {
    @Id
    private int koicategory_id;
    private String koicategory_name;
    private String koicategory_description;
    private List<koiFish> koiFish = new ArrayList<>();

}
