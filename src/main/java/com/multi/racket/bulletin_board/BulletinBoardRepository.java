package com.multi.racket.bulletin_board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BulletinBoardRepository extends JpaRepository<BulletinBoardDTO, Integer>{
		//해당 코드는 DTO에 있는 필드명을 정확히 적어줘야 에러가 안 뜬다.
		List<BulletinBoardDTO> findBymemberIdContaining(String keyword);
		List<BulletinBoardDTO> findBybbTitleContaining(String keyword);
		List<BulletinBoardDTO> findBybbContentContaining(String keyword);
}
