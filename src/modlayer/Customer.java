package modlayer;

public class Customer {
	
	private int id;
	private String name;
	private String address;
	private String zipcode;
	private String city;
	private String country;
	private String phone;
	private String email;
	private boolean isPrivate;
	
	public Customer(String name, String address, String zipcode, String city) {
		this.name = name;
		this.address = address;
		this.zipcode = zipcode;
		this.city = city;
	}
	public Customer(int id) {
		this.id = id;
	}
	public Customer() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isPrivate() {
		return isPrivate;
	}
	public void setPrivate(boolean is_private) {
		this.isPrivate = is_private;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

}
