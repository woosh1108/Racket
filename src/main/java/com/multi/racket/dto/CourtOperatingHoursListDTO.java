package com.multi.racket.dto;

import java.util.List;

import com.multi.racket.domain.CourtoperatinghoursDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourtOperatingHoursListDTO {
	private List<CourtoperatinghoursDTO> courtHour;
}
