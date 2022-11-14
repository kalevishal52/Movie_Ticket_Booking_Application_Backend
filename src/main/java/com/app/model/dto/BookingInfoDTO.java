package com.app.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.app.enums.BookingStatus;
import com.app.model.Address;
import com.app.model.Seat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingInfoDTO {

	private Integer bookingId;
	private Integer userId;

	private Integer showsId;
	private Integer screenId;
	private String screenName;
	private Integer theatreId;
	private String theatreName;
	private Address theatreAddress;
	private Integer moveiId;
	private String moveiName;
	private String moviePoster;
	
	private List<Seat> seats = new ArrayList<>();
	
	private BookingStatus bookingStatus; 
	private Double totalBillAmount;
	private LocalDateTime showDateTiming;
	
}
