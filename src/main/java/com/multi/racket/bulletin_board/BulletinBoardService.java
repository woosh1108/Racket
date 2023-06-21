package com.multi.racket.bulletin_board;

import java.util.List;

public interface BulletinBoardService {

	// 게시글 등록
	public String Bulletin_insert(BulletinBoardDTO bulletinBoard);

	// 전체게시판 전체 목록
	// 페이징
	public List<BulletinBoardDTO> bulletinBoardlist(int pageNo);
	
	public List<BulletinBoardDTO> bulletinBoardlist();

	// 게시글 상세보기
	public BulletinBoardDTO read(int bbNo);

	// 조회수 저장
	public BulletinBoardDTO save(BulletinBoardDTO bulletin);

	// 게시글 삭제
	public void delete(int bbNo);

	// 게시글 수정
	public void update(BulletinBoardDTO updatedata);

	// 게시글 수정페이지 - 수정버튼을 누르면 글을 가져옴
	public BulletinBoardDTO getBulletinBoard(int bbNo);

	// 페이지번호를 동적으로 생성하기 위한 메소드
	public long getTotalPages(int pageSize);

	// 검색하여 전체게시판 게시글 찾기
	public List<BulletinBoardDTO> search(String data);
}
