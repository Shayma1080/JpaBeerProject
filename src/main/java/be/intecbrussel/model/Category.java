package be.intecbrussel.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@ToString(exclude = "beers")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Category_id")
    private Long id;
    @Column(name = "Category_name")
    private String name;
    @Column(name = "Category_description")
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Beer> beers;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
