@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Vendor {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String contactEmail;
    private String contactPhone;
    private Boolean active = true;
}
