package com.multi.racket.stadiumpartnership;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.CourtoperatinghoursDTO;
import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumFileDTO;
import com.multi.racket.domain.StadiumcourtDTO;
import com.multi.racket.dto.CourtOperatingHoursListDTO;
import com.multi.racket.dto.StadiumCourtListDTO;
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
	public StadiumcourtDTO court_insert(StadiumDTO stadium, StadiumCourtListDTO courtlist) {
//		List<StadiumcourtDTO> courts = new ArrayList<StadiumcourtDTO>();
		
		for (StadiumcourtDTO court : courtlist.getCourtlist()) {
//			courts.add(new StadiumcourtDTO(0,court.getCourtName(),stadium));
			court.setStadiumNo(stadium);
			System.out.println("for문으로 받아온 court : "+court);
			courtrepository.save(court);
			
		}
		return null;
	}
	
	@Override
	public CourtoperatinghoursDTO hours_insert(CourtOperatingHoursListDTO hours, StadiumCourtListDTO courtlist) {
		List<StadiumcourtDTO> stadiumCourtList = courtlist.getCourtlist();
		System.out.println("넘어오는 코트 리스트값 : "+stadiumCourtList);
		List<CourtoperatinghoursDTO> courtHoursList = hours.getCourtHour();
		for (int i = 0; i < stadiumCourtList.size(); i++) {
			StadiumcourtDTO stadiumCourt = stadiumCourtList.get(i);
			hours.setCourtNo(stadiumCourt.getCourtNo());
			System.out.println(hours.getCourtNo());
			
			System.out.println("넘어오는 코트 시간리스트값 : "+courtHoursList);
			System.out.println("코트값 넣기 전 코트리스트"+stadiumCourt);
	        for (int j = 0; j < courtHoursList.size(); j++) {
	            CourtoperatinghoursDTO courtHours = courtHoursList.get(j);
	            System.out.println("뜯어낸 코트 시간값"+courtHours);
	            // courtNo 매칭 및 설정
	            courtHours.setCourtNo(hours.getCourtNo());
	            hourrepository.save(courtHours);
	            System.out.println("마지막 courtHours가 등록되는 값"+courtHours);
	        }
	    }
		
		return null;
	}

	@Override
	public StadiumDTO find_stadiumno(int stadiumNo) {
		return repository.findByStadiumNo(stadiumNo);
	}

	@Override
	public StadiumcourtDTO addcourt(int stadiumNo, StadiumcourtDTO court) {
		court.getStadiumNo().setStadiumNo(stadiumNo);
		System.out.println("============="+court.getStadiumNo().getStadiumNo());
		System.out.println(stadiumNo);
		System.out.println(court);
		courtrepository.save(court);
		return null;
	}

	@Override
	public CourtoperatinghoursDTO addcourthour(CourtOperatingHoursListDTO hours, StadiumcourtDTO court) {
		List<CourtoperatinghoursDTO> courtHoursList = hours.getCourtHour();
		for(CourtoperatinghoursDTO hour : courtHoursList) {
			hour.setCourtNo(court.getCourtNo());
			hourrepository.save(hour);
		}
		return null;
	}

	

	

	

}
