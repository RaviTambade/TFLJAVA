package com.tfl.models;

import jakarta.persistence.*;

//ORM : Object Relational Mapping
//framework: Hibernet


@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstname")
    private String firstName;

    // Getters and setters

    @Column(name = "lastname")
    private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "contact")
	private String contact;

    public Employee() {
		super();
	}
	
	public Employee( String firstName, String lastName, String email,String contact) {
		super();
		this.firstName =firstName;
		this.lastName = lastName;
		this.email=email;
		this.contact=contact;
	}

	public Employee(int id,String firstName, String lastName, String email, String contact) {
		super();
		this.id= id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contact = contact;
	}

	public Employee(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName =firstName;
		this.lastName = lastName;
	}

    public int getId() {
		return this.id;
	}

	public void setId(int  id) {
		this.id = id;
	}

    public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firsName) {
		this.firstName = firsName;
	}

    public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.lastName = email;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.lastName = contact;
	}
}
