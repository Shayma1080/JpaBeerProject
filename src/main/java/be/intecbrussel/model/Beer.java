package be.intecbrussel.model;
import jakarta.persistence.*;
import lombok.*;
@Data
@NoArgsConstructor
@Entity
@Table(name = "Beer")
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Beer_id")
    private Long id; //PK
    @Column(name = "Beer_name")
    private String name;
    @Column(name = "Beer_AlcPercentage")
    private int alcoholPercentage;
    @Column(name = "Beer_price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "Brewer_id")
    private Brewer brewer;
    @ManyToOne
    @JoinColumn(name = "Category_id")
    private Category category;

    public Beer(String name, int alcoholPercentage, double price) {
        this.id = id;
        this.name = name;
        this.alcoholPercentage = alcoholPercentage;
        this.price = price;
    }
}
