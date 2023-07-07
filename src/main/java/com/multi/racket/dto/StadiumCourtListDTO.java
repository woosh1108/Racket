package com.multi.racket.dto;

import java.util.List;

import com.multi.racket.domain.StadiumcourtDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StadiumCourtListDTO {
	private List<StadiumcourtDTO> courtlist;
	
}
