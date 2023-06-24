package com.multi.racket.manage;

import java.util.List;

import com.multi.racket.bulletin_board.BulletinBoardDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.StadiumDTO;

public interface ManageService {
	public List<StadiumDTO> findAll() ;
	public List<MemberDTO> findUser();
	public List<StadiumDTO> find_grant() ;
	public void update(List<StadiumDTO> stadiums);
	// 검색하여 전체게시판 게시글 찾기
	public List<BulletinBoardDTO> search(String data);
}
