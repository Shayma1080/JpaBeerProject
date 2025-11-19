package be.intecbrussel.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Brewer")
public class Brewer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Brewer_id")
    private Long id;
    @Column(name = "Brewer_name")
    private String name;
    @Column(name = "Brewer_location")
    private String location;

    @OneToMany(mappedBy = "brewer", fetch = FetchType.EAGER)
    private List<Beer> beers;

    public Brewer(String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
}
