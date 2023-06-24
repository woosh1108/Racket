package com.multi.racket.bulletin_board_reply;

import java.util.List;

import com.multi.racket.bulletin_board.BulletinBoardDTO;

public interface BulletinBoardReplyDAO {

	// 댓글등록
	public String bbReply_insert(BulletinBoardReplyDTO bulletinBoardReply);

	// 전체댓글목록
	public List<BulletinBoardReplyDTO> bbReply_list(int bbNo);

	// 해당 게시글에서 작성한 댓글이 그 게시글에서만 보일 수 있도록
	public void addComment(int bbNo, String content, String memberId);

	// 댓글 개수를 가져오는 메소드
	int countByBbNo(int bbNo);

	// 댓글 삭제
	public void delete(int bbReplyNo);

	// 댓글 수정
	public void update(BulletinBoardReplyDTO updatedataReply);
	
	//댓글 수정폼
	public BulletinBoardReplyDTO getbbReply(int bbReplyNo);
	
}
