package com.aeradron.enigma.datasource.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "USER")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_key")
    private Address address;

    @Column(name = "image_key")
    private UUID imageKey;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "profession")
    private String profession;

    @Column(name = "about")
    private String about;

    @Column(name = "company")
    private String company;

    @Column(name = "designation")
    private String designation;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "interests_key")
    private List<Interests> interests;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "skills_key")
    private List<Skills> skills;

    @Column(name = "work_experience")
    private Integer workExperience;

    @Column(name = "investments_done")
    private Integer investmentsDone;

    public User(Long id, UUID uuid, String userType, String firstName, String lastName, String email, String password, String gender,
                Date dateOfBirth, Address address, UUID imageKey, String phoneNumber, String profession, String about,
                String company, String designation, List<Interests> interests, List<Skills> skills, Integer workExperience,
                Integer investmentsDone) {
        this.id = id;
        this.uuid = uuid;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.imageKey = imageKey;
        this.phoneNumber = phoneNumber;
        this.profession = profession;
        this.about = about;
        this.company = company;
        this.designation = designation;
        this.interests = interests;
        this.skills = skills;
        this.workExperience = workExperience;
        this.investmentsDone = investmentsDone;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public UUID getImage() {
        return imageKey;
    }

    public void setImage(UUID image) {
        this.imageKey = image;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<Interests> getInterests() {
        return interests;
    }

    public void setInterests(List<Interests> interests) {
        this.interests = interests;
    }

    public List<Skills> getSkills() {
        return skills;
    }

    public void setSkills(List<Skills> skills) {
        this.skills = skills;
    }

    public Integer getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(Integer workExperience) {
        this.workExperience = workExperience;
    }

    public Integer getInvestmentsDone() {
        return investmentsDone;
    }

    public void setInvestmentsDone(Integer investmentsDone) {
        this.investmentsDone = investmentsDone;
    }
}
