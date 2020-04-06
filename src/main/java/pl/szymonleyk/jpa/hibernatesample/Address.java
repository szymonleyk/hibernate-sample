package pl.szymonleyk.jpa.hibernatesample;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {
	
	@Id
	private int id;
	
	private String city;
	
	private String street;
	
	@Column(name = "house_number")
	private String houseNumber;
	
	@Column(name = "flat_number")
	private Integer flatNumber;
	
	@Column(name = "post_code")
	private String postCode;
	
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", city=" + city + ", street=" + street + ", houseNumber=" + houseNumber
				+ ", flatNumber=" + flatNumber + ", postCode=" + postCode + "]";
	}
}
