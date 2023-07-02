package com.multi.racket.manage;

import java.util.List;

import com.multi.racket.bulletin_board.BulletinBoardDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.StadiumDTO;

public interface ManageDAO {
	public List<StadiumDTO> findAll() ;
	public List<MemberDTO> findUser();
	public List<StadiumDTO> find_grant(int pageno);
	public List<StadiumDTO> find_grant();
//	public StadiumDTO update(StadiumDTO stadium);
	public void update(List<StadiumDTO> stadiums);
	// 검색하여 전체게시판 게시글 찾기
	public List<StadiumDTO> search(String data);
	public List<StadiumDTO> search_name(String data);
	public StadiumDTO find_stadiumno(int stadiumNo);
	public List<StadiumDTO> stadiumlist(int pageNo);
	public long getTotalPages(int pageSize);
	public long getTotalPages(int pageSize,int stadiumstatus);
}
