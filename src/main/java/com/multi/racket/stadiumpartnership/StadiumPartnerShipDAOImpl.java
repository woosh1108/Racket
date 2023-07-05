package com.multi.racket.stadiumpartnership;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.CourtoperatinghoursDTO;
import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumFileDTO;
import com.multi.racket.domain.StadiumcourtDTO;
import com.multi.racket.repository.CourtOperatingHoursRepository;
import com.multi.racket.repository.StadiumCourtRepository;
import com.multi.racket.repository.StadiumFileRepository;
@Repository
public class StadiumPartnerShipDAOImpl implements StadiumPartnerShipDAO {
	private StadiumPartnerShipRepository repository;
	private StadiumFileRepository filerepository;
	private StadiumCourtRepository courtrepository;
	private CourtOperatingHoursRepository hourrepository;
	@Autowired
	public StadiumPartnerShipDAOImpl(StadiumPartnerShipRepository repository, StadiumFileRepository filerepository,
			StadiumCourtRepository courtrepository, CourtOperatingHoursRepository hourrepository) {
		super();
		this.repository = repository;
		this.filerepository = filerepository;
		this.courtrepository = courtrepository;
		this.hourrepository = hourrepository;
	}

	@Override
	public StadiumDTO partnership_insert(StadiumDTO associate) {
		repository.save(associate);
		return associate;
	}

	

	@Override
	public void partnershipfile_insert(List<StadiumFileDTO> associate) {
		filerepository.saveAll(associate);
	}

	@Override
	public List<StadiumFileDTO> find_file_grant(StadiumDTO stadium) {
		List<StadiumFileDTO> files = filerepository.findByStadiumNoAndStadiumFileNum(stadium.getStadiumNo(), "1");
        if (!files.isEmpty()) {
        	return files;
        }
        return null;
	}

	@Override
	public List<StadiumFileDTO> find_file(int stadiumNo) {
		List<StadiumFileDTO> files = filerepository.findByStadiumNo(stadiumNo);
		if (!files.isEmpty()) {
        	return files;
        }
		return null;
	}

	@Override
	public StadiumcourtDTO court_insert(StadiumDTO stadium,StadiumcourtDTO court) {
		court.setStadiumNo(stadium);
		courtrepository.save(court);
		return court;
	}

	@Override
	public CourtoperatinghoursDTO hours_insert(CourtoperatinghoursDTO hours, StadiumcourtDTO court) {
//		hours.setCourtNo(court);
		hours.setCourtNo(court.getCourtNo());
		System.out.println(court);
		System.out.println(hours);
		hourrepository.save(hours);
		return null;
	}

	@Override
	public StadiumcourtDTO court_insert(StadiumDTO stadium, List<StadiumcourtDTO> court) {
		for(StadiumcourtDTO courtlist:court) {
			courtlist.setStadiumNo(stadium);
			courtrepository.save(courtlist);
		}
		return null;
	}

	

	

}
