package com.kaishengit.entity;

public class User {
	 private Integer id;
	    private String firstName;
	    private String lastName;

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
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

	    public String getName() {
	        return this.firstName + " . " + this.lastName;
	    }
	
}
