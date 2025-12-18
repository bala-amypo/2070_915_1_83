public class VendorServiceImpl implements VendorService {

    private final VendorRepository repo;

    public VendorServiceImpl(VendorRepository repo) {
        this.repo = repo;
    }

    public Vendor createVendor(Vendor v) {
        if (repo.existsByName(v.getName()))
            throw new IllegalArgumentException("unique");
        return repo.save(v);
    }
}
