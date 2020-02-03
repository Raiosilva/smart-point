package com.example.demo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.example.demo.enums.ProfileEnum;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String name;
  private String email;
  private String password;
  private String cpf;
  private BigDecimal valueHour;
  private Float qttHourWorkDay;
  private Float qttHourLunch;
  private ProfileEnum profile;
  private Date dateCreate;
  private Date dateUpdate;
  private Company company;
  private List<Launch> launch;

  public Employee() {

  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "name", nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "email", nullable = false)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "password", nullable = false)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "cpf", nullable = false)
  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  @Column(name = "value_hour", nullable = false)
  public BigDecimal getValueHour() {
    return valueHour;
  }

  // @Transient
  // public Optional<BigDecimal> getValueHourOpt() {
  //   return Optional.ofNullable(valueHour);
  // }

  public void setValueHour(BigDecimal valueHour) {
    this.valueHour = valueHour;
  }

  @Column(name = "qtt_hour_work_day", nullable = false)
  public Float getQttHourWorkDay() {
    return qttHourWorkDay;
  }

  // @Transient
  // public Optional<Float> getQttHourWorkDayOpt() {
  //   return Optional.ofNullable(qttHourWorkDay);
  // }

  public void setQttHourWorkDay(Float qttHourWorkDay) {
    this.qttHourWorkDay = qttHourWorkDay;
  }

  @Column(name = "qtt_hour_lunch", nullable = false)
  public Float getQttHourLunch() {
    return qttHourLunch;
  }

  // @Transient
  // public Optional<Float> getQttHourLunchOpt() {
  //   return Optional.ofNullable(qttHourLunch);
  // }

  public void setQttHourLunch(Float qttHourLunch) {
    this.qttHourLunch = qttHourLunch;
  }

  @Enumerated(EnumType.STRING)
  @Column(name = "profile", nullable = false)
  public ProfileEnum getProfile() {
    return profile;
  }

  public void setProfile(ProfileEnum profile) {
    this.profile = profile;
  }

  @Column(name = "date_create", nullable = false)
  public Date getDateCreate() {
    return dateCreate;
  }

  public void setDateCreate(Date dateCreate) {
    this.dateCreate = dateCreate;
  }

  @Column(name = "date_update", nullable = false)
  public Date getDateUpdate() {
    return dateUpdate;
  }

  public void setDateUpdate(Date dateUpdate) {
    this.dateUpdate = dateUpdate;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  public List<Launch> getLaunch() {
    return launch;
  }

  public void setLaunch(List<Launch> launch) {
    this.launch = launch;
  }

  @PreUpdate
  public void preUpdate() {
    dateUpdate = new Date();
  }

  @PrePersist
  public void prePersist() {
    final Date now = new Date();
    dateCreate = now;
    dateUpdate = now;
  }

  @Override
  public String toString() {
    return "Employee [company=" + company + ", cpf=" + cpf + ", dateCreate=" + dateCreate + ", dateUpdate=" + dateUpdate
        + ", email=" + email + ", id=" + id + ", launch=" + launch + ", name=" + name + ", password=" + password
        + ", profile=" + profile + ", qttHourLunch=" + qttHourLunch + ", qttHourWorkDay=" + qttHourWorkDay + ", valueHour=" + valueHour + "]";
  }
}