package com.multi.racket.bulletin_board_reply;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BulletinBoardReplyDAOImpl implements BulletinBoardReplyDAO {

	BulletinBoardReplyRepository repository;

	@Autowired
	public BulletinBoardReplyDAOImpl(BulletinBoardReplyRepository repository) {
		super();
		this.repository = repository;
	}

	// 댓글등록
	@Override
	public String bbReply_insert(BulletinBoardReplyDTO bulletinBoardReply) {
		repository.save(bulletinBoardReply);
		return Integer.toString(bulletinBoardReply.getBbReplyNo());
	}

	// 전체댓글목록
	@Override
	public List<BulletinBoardReplyDTO> bbReply_list(int bbNo) {
		List<BulletinBoardReplyDTO> bbReply = repository.getRepliesByBbNo(bbNo);

		List<BulletinBoardReplyDTO> bbNoresult = new ArrayList<BulletinBoardReplyDTO>();
		bbNoresult.addAll(bbReply);
		return bbNoresult;
	}

	// 해당 게시글에서 작성한 댓글이 그 게시글에서만 보일 수 있도록
	@Override
	public void addComment(int bbNo, String content, String memberId) {
		// 댓글 등록 로직 구현
		BulletinBoardReplyDTO reply = new BulletinBoardReplyDTO();
		reply.setBbNo(bbNo);
		reply.setBbReplyContent(content);
		reply.setMemberId(memberId);
		// 필요한 필드 설정

		repository.save(reply);
	}

	// 댓글 개수를 가져오는 메소드
	@Override
	public int countByBbNo(int bbNo) {
		return repository.countByBbNo(bbNo);
	}

	// 댓글 삭제
	@Override
	public void delete(int bbReplyNo) {
		BulletinBoardReplyDTO bbReply = repository.findById(bbReplyNo).orElse(null);

		if (bbReply != null) {
			repository.deleteById(bbReplyNo);
		} else {
			System.out.println("이미 삭제되었거나 댓글을 찾을 수 없습니다.");
		}
	}

	// 댓글 수정
	@Override
	public void update(BulletinBoardReplyDTO updatedataReply) {
		BulletinBoardReplyDTO bbReply = repository.findById(updatedataReply.getBbReplyNo())
				.orElseThrow(() -> new RuntimeException());
		bbReply.setBbReplyContent(updatedataReply.getBbReplyContent());
		repository.save(bbReply);
	}

	// 댓글 수정폼
	@Override
	public BulletinBoardReplyDTO getbbReply(int bbReplyNo) {
		return repository.findById(bbReplyNo).orElseThrow();
	}

}
