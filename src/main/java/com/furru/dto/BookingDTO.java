package com.furru.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.furru.entity.Destination;
import com.furru.entity.User;

public class BookingDTO {

private int bookingId;
private LocalDate CheckIn;
private LocalDate CheckOut;
private int noOfPeople;
private float totalCost;
private LocalDateTime timeOfBooking;
private User user;
private Destination destination;
public int getBookingId() {
	return bookingId;
}
public void setBookingId(int bookingId) {
	this.bookingId = bookingId;
}
public LocalDate getCheckIn() {
	return CheckIn;
}
public void setCheckIn(LocalDate checkIn) {
	CheckIn = checkIn;
}
public LocalDate getCheckOut() {
	return CheckOut;
}
public void setCheckOut(LocalDate checkOut) {
	CheckOut = checkOut;
}
public int getNoOfPeople() {
	return noOfPeople;
}
public void setNoOfPeople(int noOfPeople) {
	this.noOfPeople = noOfPeople;
}
public float getTotalCost() {
	return totalCost;
}
public void setTotalCost(float totalCost) {
	this.totalCost = totalCost;
}
public LocalDateTime getTimeOfBooking() {
	return timeOfBooking;
}
public void setTimeOfBooking(LocalDateTime timeOfBooking) {
	this.timeOfBooking = timeOfBooking;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Destination getDestination() {
	return destination;
}
public void setDestination(Destination destination) {
	this.destination = destination;
}
}