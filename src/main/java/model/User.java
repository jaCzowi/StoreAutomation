package model;

import com.google.common.base.Objects;

import java.util.StringJoiner;

public class User {

    private String fName;
    private String lName;
    private String address;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    private String phoneNumb;
    private String email;

    public User(String fName, String lName, String address, String city, String province, String country, String postalCode, String phoneNumb, String email) {
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postalCode = postalCode;
        this.phoneNumb = phoneNumb;
        this.email = email;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("fName='" + fName + "'")
                .add("lName='" + lName + "'")
                .add("address='" + address + "'")
                .add("city='" + city + "'")
                .add("province='" + province + "'")
                .add("country='" + country + "'")
                .add("postalCode='" + postalCode + "'")
                .add("phoneNumb='" + phoneNumb + "'")
                .add("email='" + email + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equal(getfName(), user.getfName()) &&
                Objects.equal(getlName(), user.getlName()) &&
                Objects.equal(getAddress(), user.getAddress()) &&
                Objects.equal(getCity(), user.getCity()) &&
                Objects.equal(getProvince(), user.getProvince()) &&
                Objects.equal(getCountry(), user.getCountry()) &&
                Objects.equal(getPostalCode(), user.getPostalCode()) &&
                Objects.equal(getPhoneNumb(), user.getPhoneNumb()) &&
                Objects.equal(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getfName(), getlName(), getAddress(), getCity(), getProvince(), getCountry(), getPostalCode(), getPhoneNumb(), getEmail());
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumb() {
        return phoneNumb;
    }

    public void setPhoneNumb(String phoneNumb) {
        this.phoneNumb = phoneNumb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
