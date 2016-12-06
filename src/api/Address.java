package api;

public class Address {

	private String address;
	private String lat;
	private String lng;

	public Address(String address, String lat, String lng) {
		super();
		this.address = address;
		this.lat = lat;
		this.lng = lng;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

}
