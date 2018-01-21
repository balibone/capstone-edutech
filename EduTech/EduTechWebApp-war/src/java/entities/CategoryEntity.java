package entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Category")
public class CategoryEntity implements Serializable {
    @Id
    private String name;
    private String description;
    private ArrayList<String> subcategories;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(ArrayList<String> subcategories) {
        this.subcategories = subcategories;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}