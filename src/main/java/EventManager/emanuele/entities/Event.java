package EventManager.emanuele.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "eventi")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Event {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String description;
    private LocalDate eventDay;
    private String location;
    private int audienceCapacity;
}
