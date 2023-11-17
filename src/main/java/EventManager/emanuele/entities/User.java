package EventManager.emanuele.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String surname;
    private String password;
    private String nickname;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private List<Event> eventList;
}
