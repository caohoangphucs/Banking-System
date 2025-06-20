package  dto.db;


public class UserDto {
    private String ID;
    private String name;
    private String address;
    private int age;
    private String email;

    public UserDto(String ID,int age, String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.email = email;
        this.ID = ID;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
