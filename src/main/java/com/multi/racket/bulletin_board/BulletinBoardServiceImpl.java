package com.multi.racket.bulletin_board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BulletinBoardServiceImpl implements BulletinBoardService {
	BulletinBoardDAO bulletinBoarddao;
	BulletinBoardRepository repository;

	@Autowired
	public BulletinBoardServiceImpl(BulletinBoardDAO bulletinBoarddao, BulletinBoardRepository repository) {
		super();
		this.bulletinBoarddao = bulletinBoarddao;
		this.repository = repository;
	}

	// 게시글 등록
	@Override
	public String Bulletin_insert(BulletinBoardDTO bulletinBoard) {
		return bulletinBoarddao.Bulletin_insert(bulletinBoard);
	}

	// 전체게시판 전체목록
	@Override
	public List<BulletinBoardDTO> bulletinBoardlist(int pageNo) {
		return bulletinBoarddao.bulletinBoardlist(pageNo);
	}

	@Override
	public List<BulletinBoardDTO> bulletinBoardlist() {
		// TODO Auto-generated method stub
		return null;
	}

	// 게시글 상세보기
	@Override
	public BulletinBoardDTO read(int bbNo) {
		return bulletinBoarddao.read(bbNo);
	}

	// 조회수 저장
	@Override
	public BulletinBoardDTO save(BulletinBoardDTO bulletin) {
		return repository.save(bulletin);
	}

	// 게시글 삭제
	@Override
	public void delete(int bbNo) {
		bulletinBoarddao.delete(bbNo);
	}

	// 게시글 수정
	@Override
	public void update(BulletinBoardDTO updatedata) {
		 bulletinBoarddao.update(updatedata);
	}

	// 게시글 수정페이지를 가져옴
	@Override
	public BulletinBoardDTO getBulletinBoard(int bbNo) {
		return bulletinBoarddao.getBulletinBoard(bbNo);
	}

	// 페이지번호를 동적으로 생성하기 위한 메소드
	@Override
	public long getTotalPages(int pageSize) {
		return bulletinBoarddao.getTotalPages(pageSize);
	}

	// 검색하여 전체게시판 게시글 찾기
	@Override
	public List<BulletinBoardDTO> search(String data) {
		return bulletinBoarddao.search(data);
	}

}
