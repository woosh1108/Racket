package com.multi.racket.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.racket.announcement.AnnouncementDTO;
import com.multi.racket.bulletin_board.BulletinBoardDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.StadiumDTO;
@Service
@Transactional
public class ManageServiceImpl implements ManageService {
	ManageDAO dao;
	@Autowired
	public ManageServiceImpl(ManageDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public List<StadiumDTO> findAll() {
		return dao.findAll();
	}

	@Override
	public List<StadiumDTO> find_grant(int pageno) {
		return dao.find_grant(pageno);
	}
	@Override
	public void update(List<StadiumDTO> stadiums) {
		dao.update(stadiums);
	}

	@Override
	public List<MemberDTO> findUser() {
		return dao.findUser();
	}

	@Override
	public List<StadiumDTO> search(String data) {
		return dao.search(data);
	}

	@Override
	public StadiumDTO find_stadiumno(int stadiumNo) {
		return dao.find_stadiumno(stadiumNo);
	}

	@Override
	public List<StadiumDTO> stadiumlist(int pageNo) {
		// TODO Auto-generated method stub
		return dao.stadiumlist(pageNo);
	}

	@Override
	public long getTotalPages(int pageSize) {
		return dao.getTotalPages(pageSize);
	}

	@Override
	public List<StadiumDTO> find_grant() {
		return dao.find_grant();
	}

	@Override
	public long getTotalPages(int pageSize, int stadiumstatus) {
		return dao.getTotalPages(pageSize, stadiumstatus);
	}

	@Override
	public List<StadiumDTO> search_name(String data) {
		return dao.search_name(data);
	}


}
