@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "tierName"))
public class VendorTier {
    @Id @GeneratedValue
    private Long id;
    private String tierName;
    private Double minScoreThreshold;
    private Boolean active = true;
}
