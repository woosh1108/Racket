package com.multi.racket.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="stadium")
public class StadiumDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stadium_no")
	private int stadiumNo;

	@ManyToOne
    @JoinColumn(name = "member_id")
    private MemberDTO member;
	
	private String stadiumName;
	private String stadiumAddr;
	private String stadiumTime;
	private String stadiumFee;
	private int stadiumStatus;
	private String stadiumContent;
	private String stadiumTel;
	
    @OneToMany(mappedBy = "stadium", cascade = CascadeType.ALL)
    private List<StadiumFileDTO> files;

    @OneToMany(mappedBy = "stadium", cascade = CascadeType.ALL)
    private List<StadiumcourtDTO> courts;

    @Override
    public String toString() {
        return "StadiumDTO{" +
                "stadiumNo=" + stadiumNo +
                ", member=" + (member != null ? member.getMemberId() : null) +
                ", stadiumName='" + stadiumName + '\'' +
                ", stadiumAddr='" + stadiumAddr + '\'' +
                ", stadiumTime='" + stadiumTime + '\'' +
                ", stadiumFee='" + stadiumFee + '\'' +
                ", stadiumStatus=" + stadiumStatus +
                ", stadiumContent='" + stadiumContent + '\'' +
                ", stadiumTel='" + stadiumTel + '\'' +
                '}';
    }
    
}
