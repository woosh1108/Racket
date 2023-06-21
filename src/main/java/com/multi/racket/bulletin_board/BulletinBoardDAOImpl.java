package com.multi.racket.bulletin_board;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class BulletinBoardDAOImpl implements BulletinBoardDAO {
	BulletinBoardDTO bulletinBoard;
	private BulletinBoardRepository repository;
	
	
	@Autowired
	public BulletinBoardDAOImpl(BulletinBoardDTO bulletinBoard, BulletinBoardRepository repository) {
		super();
		this.bulletinBoard = bulletinBoard;
		this.repository = repository;
	}
	
	//게시글 등록
	@Override
	public String Bulletin_insert(BulletinBoardDTO bulletinBoard) {
		repository.save(bulletinBoard);
		return Integer.toString(bulletinBoard.getBbNo());
	}
	
	// 전체게시판 전체목록
	@Override
	public List<BulletinBoardDTO> bulletinBoardlist(int pageNo) {
		PageRequest pageRequest = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "bbNo");
		Page<BulletinBoardDTO> page = repository.findAll(pageRequest);
		List<BulletinBoardDTO> list = page.getContent();
		
		return list;
	}
	
	// 게시글 상세보기
	@Override
	public BulletinBoardDTO read(int bbNo) {
		Optional<BulletinBoardDTO> bulletinBoardOptional = repository.findById(bbNo);
		BulletinBoardDTO bulletinBoard = bulletinBoardOptional.orElse(null);
		return bulletinBoard;
	}
	
	// 게시글 삭제
	@Override
	public void delete(int bbNo) {
		BulletinBoardDTO bulletinBoard = repository.findById(bbNo).orElse(null);
		
		if(bulletinBoard != null) {
			repository.deleteById(bbNo);
		}
		else {
			System.out.println(bbNo + "번의 게시글을 찾을 수 없습니다.");
		}
	}
	
	// 게시글 수정
	@Override
	public void update(BulletinBoardDTO updatedata) {
		BulletinBoardDTO bulletinBoard = repository.findById(updatedata.getBbNo())
				.orElseThrow(() -> new RuntimeException());
		bulletinBoard.setBbTitle(updatedata.getBbTitle());
		bulletinBoard.setBbContent(updatedata.getBbContent());
		repository.save(bulletinBoard);
	}
	
	// 게시글 수정페이지를 가져옴
	@Override
	public BulletinBoardDTO getBulletinBoard(int bbNo) {
		return repository.findById(bbNo).orElseThrow();
	}
	
	// 페이지번호를 동적으로 생성하기 위한 메소드
	@Override
	public long getTotalPages(int pageSize) {
		long totalItems = repository.count();
		return (totalItems + pageSize - 1) / pageSize;
	}
	
	// 검색하여 전체게시판 게시글 찾기
	@Override
	public List<BulletinBoardDTO> search(String data) {
		List<BulletinBoardDTO> bulletinBoardMemberId = repository.findBymemberIdContaining(data);
		List<BulletinBoardDTO> bulletinBoardTitle = repository.findBybbTitleContaining(data);
		List<BulletinBoardDTO> bulletinBoardContent = repository.findBybbContentContaining(data);
		
		List<BulletinBoardDTO> result = new ArrayList<>();
		result.addAll(bulletinBoardMemberId);
		result.addAll(bulletinBoardTitle);
		result.addAll(bulletinBoardContent);
		
		return result;
	}

}
