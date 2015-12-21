package dataLayer;

public class Address {
	
	private String street;
	private String city;
	private String postCode;
	
	public Address(String streetRef, String cityRef, String postCodeRef) {
		street = streetRef;
		city = cityRef;
		postCode = postCodeRef;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", postCode="
				+ postCode + "]";
	}

}
