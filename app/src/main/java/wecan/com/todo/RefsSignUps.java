package wecan.com.todo;

/**
 * Created by owner on 8/6/2016.
 */

public class RefsSignUps {
    private String firstName, lastName, storeName, email, phoneNumber;
    private boolean madeSales = false;

    public RefsSignUps(){   }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isMadeSales() {
        return madeSales;
    }

    public void setMadeSales(boolean madeSales) {
        this.madeSales = madeSales;
    }
}
