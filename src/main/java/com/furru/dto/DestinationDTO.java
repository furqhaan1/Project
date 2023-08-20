package com.furru.dto;

public class DestinationDTO {
	private String destinationId;
	private String continent;
	private String destinationName;
	private String imageUrl;
	private Integer noOfNights;
	private Float flightCharge;
	private Float chargePerPerson;
	private Float discount;
	private Integer availability;
	private String detailsId;
	private DetailsDTO details;
	public String getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(String destinationId) {
		this.destinationId = destinationId;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getNoOfNights() {
		return noOfNights;
	}
	public void setNoOfNights(Integer noOfNights) {
		this.noOfNights = noOfNights;
	}
	public Float getFlightCharge() {
		return flightCharge;
	}
	public void setFlightCharge(Float flightCharge) {
		this.flightCharge = flightCharge;
	}
	public Float getChargePerPerson() {
		return chargePerPerson;
	}
	public void setChargePerPerson(Float chargePerPerson) {
		this.chargePerPerson = chargePerPerson;
	}
	public Float getDiscount() {
		return discount;
	}
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	public Integer getAvailability() {
		return availability;
	}
	public void setAvailability(Integer availability) {
		this.availability = availability;
	}
	public String getDetailsId() {
		return detailsId;
	}
	public void setDetailsId(String detailsId) {
		this.detailsId = detailsId;
	}
	public DetailsDTO getDetails() {
		return details;
	}
	public void setDetails(DetailsDTO details) {
		this.details = details;
	}
}
