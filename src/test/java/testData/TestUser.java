package testData;

public class TestUser {
    private String name;
    private String lastName;
    private String address;
    private String phoneNumber;

    public TestUser(
            String name,
            String lastName,
            String address,
            String phoneNumber
    ) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
}
