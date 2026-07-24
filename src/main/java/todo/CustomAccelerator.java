package todo;

public class CustomAccelerator {
    private final String StringAccelerator;
    private final Integer SWTAccelerator;

    public CustomAccelerator() {
        this.StringAccelerator = null;
        this.SWTAccelerator = null;
    }

    public CustomAccelerator(String StringAccelerator, Integer SWTAccelerator) {
        this.StringAccelerator = StringAccelerator;
        this.SWTAccelerator = SWTAccelerator;
    }

    public String getStringAccelerator() {
        return this.StringAccelerator;
    }

    public Integer getSWTAccelerator() {
        return this.SWTAccelerator;
    }
}
