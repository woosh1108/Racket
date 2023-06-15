package com.multi.racket.stadiumpartnership;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.AssociateDTO;
import com.multi.racket.domain.StadiumDTO;
//spring data jpa에서 제공하는 JpaRepository인터페이스를 상속받으면 spring data내부에서 자동으로 SpringDataDeptRepository를
//구현하는 클래스를 만들어서 매핑한다.
//따라서 이 repository가 어떤 엔티티를 관리할 것인지 기본키의 타입이 무엇인지는 지정해야 한다.
@Repository
public interface StadiumPartnerShipRepository extends JpaRepository<AssociateDTO, String>{

}
