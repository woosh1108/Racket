package com.multi.racket.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.racket.bulletin_board.BulletinBoardDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.member.MemberRepository;
@Repository
public class ManageDAOImpl implements ManageDAO {
	private ManageRepository repository;
	private MemberRepository memberrepository;
	@Autowired
	public ManageDAOImpl(ManageRepository repository, MemberRepository memberrepository) {
		super();
		this.repository = repository;
		this.memberrepository = memberrepository;
	}

	@Override
	public List<StadiumDTO> findAll() {
		return repository.findAll();
	}
	
	@Override
	public List<StadiumDTO> find_grant() {
		return repository.findByStadiumStatus(1);
	}
	@Override
	public void update(List<StadiumDTO> stadiums) {
		 for (StadiumDTO stadium : stadiums) {
		        StadiumDTO stadiumlist = repository.findById(stadium.getStadiumNo()).orElseThrow(() -> new RuntimeException());
		        stadiumlist.setStadiumStatus(stadium.getStadiumStatus());
		        repository.save(stadiumlist);
		    }
	}

	@Override
	public List<MemberDTO> findUser() {
		return memberrepository.findAll();
	}

	@Override
	public List<BulletinBoardDTO> search(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StadiumDTO find_stadiumno(int stadiumNo) {
		return repository.findByStadiumNo(stadiumNo);
	}
	
}
