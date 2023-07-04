package com.multi.racket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class KakaoData {
	private String email;
	private String nickname;
	private String gender;
}
