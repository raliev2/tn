package com.teamidea.platform.technonikol.storefront.forms;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationRequestForm
{
    private String firstName;
    private String surName;
    private String lastName;
    private String company;
    private String inn;
    private String phone;
    private String email;
    private boolean subscribedToSmsAndEmailNotification;

    @NotNull(message = "{registrationRequestForm.firstName.notEmpty}")
    @Size(max = 255, message = "{registrationRequestForm.firstName.wrongSize}")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull(message = "{registrationRequestForm.surName.notEmpty}")
    @Size(max = 255, message = "{registrationRequestForm.surName.wrongSize}")
    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    @NotNull(message = "{registrationRequestForm.lastName.notEmpty}")
    @Size(max = 255, message = "{registrationRequestForm.lastName.wrongSize}")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull(message = "{registrationRequestForm.company.notEmpty}")
    @Size(max = 1024, message = "{registrationRequestForm.company.wrongSize}")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @NotNull(message = "{registrationRequestForm.inn.notEmpty}")
    @Size(min = 10, max = 12, message = "{registrationRequestForm.inn.wrongSize}")
    @Pattern(regexp = "[0-9]*", message = "{registrationRequestForm.inn.onlyDigitsAllowed}")
    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @NotNull(message = "{registrationRequestForm.phone.notEmpty}")
    @Size(max = 255, message = "{registrationRequestForm.phone.wrongSize}")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NotNull(message = "{registrationRequestForm.email.notEmpty}")
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
