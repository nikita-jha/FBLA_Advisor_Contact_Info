package sample;

import javafx.scene.control.CheckBox;

public class Advisor {

    private String advisorID;

    private String name;

    private String phoneNumber;

    private String fax;

    private String primaryEmail;

    private String secondaryEmail;

    private String address;

    private String cityStateZip;

    private String homePhone;

    private String cellPhone;

    private CheckBox primaryAdvisor;

    private CheckBox chapter;

    private CheckBox archive;

    private String dateAdded;

    private String password;


        public String getAdvisorID() {
        return advisorID;
    }

    public void setAdvisorID(String advisorID) {
        this.advisorID = advisorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityStateZip() {
        return cityStateZip;
    }

    public void setCityStateZip(String cityStateZip) {
        this.cityStateZip = cityStateZip;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public CheckBox getPrimaryAdvisor() {
        return primaryAdvisor;
    }

    public void setPrimaryAdvisor(CheckBox primaryAdvisor) {
        this.primaryAdvisor = primaryAdvisor;
    }

    public CheckBox getChapter() {
        return chapter;
    }

    public void setChapter(CheckBox chapter) {
        this.chapter = chapter;
    }

    public CheckBox getArchive() {
        return archive;
    }

    public void setArchive(CheckBox archive) {
        this.archive = archive;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
