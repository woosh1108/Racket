package com.multi.racket.bulletin_board_reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BulletinBoardReplyServiceImpl implements BulletinBoardReplyService {
	
	BulletinBoardReplyDAO bbReplydao;
	
	
	@Autowired
	public BulletinBoardReplyServiceImpl(BulletinBoardReplyDAO bbReplydao) {
		super();
		this.bbReplydao = bbReplydao;
	}



	// 댓글등록
	@Override
	public String bbReply_insert(BulletinBoardReplyDTO bulletinBoardReply) {
		return bbReplydao.bbReply_insert(bulletinBoardReply);
	}


	//전체댓글목록
	@Override
	public List<BulletinBoardReplyDTO> bbReply_list(int bbNo) {
		return bbReplydao.bbReply_list(bbNo);
	}


	// 해당 게시글에서 작성한 댓글이 그 게시글에서만 보일 수 있도록
	@Override
	public void addComment(int bbNo, String content, String memberId) {
		bbReplydao.addComment(bbNo, content, memberId);
	}


	// 댓글 개수를 가져오는 메소드
	@Override
	public int countByBbNo(int bbNo) {
		return bbReplydao.countByBbNo(bbNo);
	}


	
	// 댓글 삭제
	@Override
	public void delete(int bbReplyNo) {
		bbReplydao.delete(bbReplyNo);
	}


	// 댓글 수정
	@Override
	public void update(BulletinBoardReplyDTO updatedataReply) {
		bbReplydao.update(updatedataReply);
	}


	// 댓글 수정폼
	@Override
	public BulletinBoardReplyDTO getbbReply(int bbReplyNo) {
		return bbReplydao.getbbReply(bbReplyNo);
	}

}
