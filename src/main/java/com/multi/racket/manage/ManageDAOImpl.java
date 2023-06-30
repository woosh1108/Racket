package com.multi.racket.manage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public List<StadiumDTO> find_grant(int pageNo) {
		PageRequest pageRequest = PageRequest.of(pageNo, 10, Sort.by(Sort.Direction.DESC,"stadiumNo")); 
		Page<StadiumDTO> page = repository.findAll(pageRequest);
		List<StadiumDTO> list = page.getContent();
		List<StadiumDTO> statuslist;
		
//		return repository.findByStadiumStatus(1);  //status가 1인것들만(사용자가 권한 등록을 시켜줬을 때)
		return list;
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

	@Override
	public List<StadiumDTO> stadiumlist(int pageNo) {
		PageRequest pageRequest = PageRequest.of(pageNo, 10, Sort.by(Sort.Direction.DESC,"stadiumNo")); 
		Page<StadiumDTO> page = repository.findAllByStadiumStatus(1,pageRequest);
		System.out.println("page값 : "+page);
		System.out.println("findallbystadium값 : "+repository.findAllByStadiumStatus(1, pageRequest));
		List<StadiumDTO> list = page.getContent();
		List<StadiumDTO> statuslist = new ArrayList<StadiumDTO>();
	    for(StadiumDTO stadium : list) {
	        if(stadium.getStadiumStatus()==0) {
	        	System.out.println("adssasadsad"+repository.findByStadiumStatus(1));
	            statuslist.add(stadium);
	        }
	    }
	    return list;
	}

	@Override
	public long getTotalPages(int pageSize,int stadiumstatus) {
		long totalItems = repository.countByStadiumStatus(stadiumstatus);
		System.out.println("status가 1인 것들의 합계"+totalItems);
		return (totalItems + pageSize - 1) / pageSize;
	}

	@Override
	public List<StadiumDTO> find_grant() {
		return repository.findByStadiumStatus(1); 
	}

	@Override
	public long getTotalPages(int pageSize) {
		long totalItems = repository.count();
		return (totalItems + pageSize - 1) / pageSize;
	}


	
}
