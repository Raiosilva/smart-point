package com.example.demo.java.com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.demo.java.com.examplo.demo.enums.TypeEnum;

@Entity
@Table(name = "launch")
public class Launch implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private Date date;
  private String description;
  private String localization;
  private Date dateCreate;
  private Date dateUpdate;
  private TypeEnum type;
  private Employee employee;

  public Launch() {

  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date", nullable = false)
  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Column(name = "description", nullable = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "localization", nullable = false)
  public String getLocalization() {
    return localization;
  }

  public void setLocalization(String localization) {
    this.localization = localization;
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

  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false)
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
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
    return "Launch [date=" + date + ", dateCreate=" + dateCreate + ", dateUpdate=" + dateUpdate + ", description="
        + description + ", employee=" + employee + ", id=" + id + ", localization=" + localization + ", type="
        + type + "]";
  }

}