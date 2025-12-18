@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String role;
}
