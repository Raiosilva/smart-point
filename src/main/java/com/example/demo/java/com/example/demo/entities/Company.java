package com.example.demo.java.com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.example.demo.java.com.example.demo.entities.Employee;

@Entity
@Table(name = "company")
public class Company implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String companyName;
  private String cnpj;
  private Date dateCreate;
  private Date dateUpdate;
  private List<Employee> employee;

  public Company() {

  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  @Column(name = "company_name", nullable = false)
  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(final String companyName) {
    this.companyName = companyName;
  }

  @Column(name = "cnpj", nullable = false)
  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(final String cnpj) {
    this.cnpj = cnpj;
  }

  @Column(name = "data_create", nullable = false)
  public Date getDateCreate() {
    return dateCreate;
  }

  public void setDateCreate(final Date dateCreate) {
    this.dateCreate = dateCreate;
  }

  @Column(name = "data_update", nullable = false)
  public Date getDateUpdate() {
    return dateUpdate;
  }

  public void setDateUpdate(final Date dateUpdate) {
    this.dateUpdate = dateUpdate;
  }

  @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  public List<Employee> getEmployee() {
    return employee;
  }

  public void setEmployee(final List<Employee> employee) {
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
    return "Company [id=" + id + " , companyName=" + companyName + ", cnpj=" + cnpj + ", dateCreate=" + dateCreate
              + ", dateUpdate=" + dateUpdate + "]";
  }

}