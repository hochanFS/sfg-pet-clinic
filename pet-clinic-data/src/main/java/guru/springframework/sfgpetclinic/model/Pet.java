package guru.springframework.sfgpetclinic.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")

public class Pet extends BaseEntity {

    @Builder
    public Pet(Long id, PetType petType, Owner owner, LocalDate birthDate, String name, Set<Visit> visits) {
        super(id);
        this.petType = petType;
        this.owner = owner;
        this.birthDate = birthDate;
        this.name = name;

        if (visits == null || visits.size() > 0)  {
            this.visits = visits;
        }
    }


    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id") // have owner id in pet record
    private Owner owner;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

}
