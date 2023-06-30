package com.multi.racket.bulletin_board_reply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BulletinBoardReplyRepository extends JpaRepository<BulletinBoardReplyDTO, Integer> {
	 //해당 게시물에서만 해당 댓글이 보일 수 있도록
	 List<BulletinBoardReplyDTO> getRepliesByBbNo(Integer bbNo);
	 //댓글 수
	 int countByBbNo(int bbNo);
}
