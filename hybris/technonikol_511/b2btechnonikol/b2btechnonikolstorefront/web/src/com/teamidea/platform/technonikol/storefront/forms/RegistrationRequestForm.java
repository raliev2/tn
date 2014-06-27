package com.teamidea.platform.technonikol.storefront.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationRequestForm
{
    private String firstName;
    private String surName;
    private String lastName;
    private String company;
    private String inn;
    private String kpp;
    private String phone;
    private String email;
    private boolean subscribedToSmsAndEmailNotification;

    @NotEmpty(message = "{registrationRequestForm.firstName.notEmpty}")
    @Size(max = 255, message = "{registrationRequestForm.firstName.wrongSize}")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotEmpty(message = "{registrationRequestForm.surName.notEmpty}")
    @Size(max = 255, message = "{registrationRequestForm.surName.wrongSize}")
    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    @NotEmpty(message = "{registrationRequestForm.lastName.notEmpty}")
    @Size(max = 255, message = "{registrationRequestForm.lastName.wrongSize}")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotEmpty(message = "{registrationRequestForm.company.notEmpty}")
    @Size(max = 1024, message = "{registrationRequestForm.company.wrongSize}")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @NotEmpty(message = "{registrationRequestForm.inn.notEmpty}")
    @Size(min = 10, max = 12, message = "{registrationRequestForm.inn.wrongSize}")
    @Length(min = 10, max = 12, message = "{registrationRequestForm.inn.wrongSize}")
    @Pattern(regexp = "[0-9]*", message = "{registrationRequestForm.inn.onlyDigitsAllowed}")
    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @NotEmpty(message = "{registrationRequestForm.kpp.notEmpty}")
    @Size(min = 9, max = 9, message = "{registrationRequestForm.kpp.wrongSize}")
    @Length(min = 9, max = 9, message = "{registrationRequestForm.kpp.wrongSize}")
    @Pattern(regexp = "[0-9]*", message = "{registrationRequestForm.kpp.onlyDigitsAllowed}")
    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    @NotEmpty(message = "{registrationRequestForm.phone.notEmpty}")
    @Size(max = 255, message = "{registrationRequestForm.phone.wrongSize}")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NotEmpty(message = "{registrationRequestForm.email.notEmpty}")
    @Email(message = "{registrationRequestForm.email.wrongFormat}")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSubscribedToSmsAndEmailNotification() {
        return subscribedToSmsAndEmailNotification;
    }

    public void setSubscribedToSmsAndEmailNotification(boolean subscribedToSmsAndEmailNotification) {
        this.subscribedToSmsAndEmailNotification = subscribedToSmsAndEmailNotification;
    }
}
