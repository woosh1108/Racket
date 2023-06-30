package com.multi.racket.domain;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member2")
@DynamicInsert
public class MemberDTO {
    @Id
    @NonNull
	@Column(name = "member_id")
    private String memberId;
    @NonNull
    private String memberPass;
    @NonNull
    private String memberName;
    @NonNull
    private String memberAge;
    @NonNull
    private String memberPhone;
    @NonNull
    private String memberNick;
    @NonNull
    private String memberGender;
    @NonNull
    private String memberAddr;
    @NonNull
    private String memberGrade;
    @NonNull
    private Integer memberAuth;
    @NonNull
    private Integer memberStatus;
    @NonNull
    private String memberEmail;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @CreatedDate
    private Date memberReg;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<TrainingDTO> trainings;
    
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<ReservationDTO> reservations;
    
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<StadiumDTO> stadiums;
	
}
