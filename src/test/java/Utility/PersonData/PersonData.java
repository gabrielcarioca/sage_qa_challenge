package Utility.PersonData;

public class PersonData {

    private String userName;
    private String userEmail;
    private String userDocument;
    private String motherName;
    private String fatherName;
    private String zipCode;
    private String number;

    public String getUserName() {
        return userName;
    }
    public String getUserEmail() {
        return userEmail;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getNumber() {
        return number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setUserEmail(String email) {
        this.userEmail = email;
    }
    public void setUserDocument(String document) {
        this.userDocument = document;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getFormattedUserDocument() {
        // In case user document was already saved with dots and hyphen
        if(userDocument.length() > 11) {
            return userDocument;
        }

        return userDocument.substring(0,3) + "." +
                userDocument.substring(3, 6) + "." +
                userDocument.substring(6,9) + "-" +
                userDocument.substring(9);
    }

    public String getUserDocument() {
        return userDocument;
    }
}
