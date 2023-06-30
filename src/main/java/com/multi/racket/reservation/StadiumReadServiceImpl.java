//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.multi.racket.domain.AccountDTO;
//import com.multi.racket.domain.CourtoperatinghoursDTO;
//import com.multi.racket.domain.StadiumDTO;
//import com.multi.racket.domain.StadiumFileDTO;
//import com.multi.racket.domain.StadiumcourtDTO;
//import com.multi.racket.repository.AccountRepository;
//import com.multi.racket.repository.CorutOperationHoursRepository;
//import com.multi.racket.repository.StadiumCorutRepository;
//import com.multi.racket.repository.StadiumFileRepository;
//import com.multi.racket.repository.StadiumRepository;
//
//@Service
//public class StadiumReadServiceImpl implements StadiumReadService {
//
//    private final StadiumRepository stadiumRepository;
//    private final StadiumCorutRepository stadiumCorutRepository;
//    private final CorutOperationHoursRepository corutOperationHoursRepository;
//    private final StadiumFileRepository stadiumFileRepository;
//    private final AccountRepository accountRepository;
//
//    @Autowired
//    public StadiumReadServiceImpl(StadiumRepository stadiumRepository,
//                                  StadiumCorutRepository stadiumCorutRepository,
//                                  CorutOperationHoursRepository corutOperationHoursRepository,
//                                  StadiumFileRepository stadiumFileRepository,
//                                  AccountRepository accountRepository) {
//        this.stadiumRepository = stadiumRepository;
//        this.stadiumCorutRepository = stadiumCorutRepository;
//        this.corutOperationHoursRepository = corutOperationHoursRepository;
//        this.stadiumFileRepository = stadiumFileRepository;
//        this.accountRepository = accountRepository;
//    }
//    
//    @Override
//    public StadiumDTO getStadiumData(int stadiumNo) {
//        Stadium stadium = stadiumRepository.findByStadiumNo(stadiumNo);
//        StadiumDTO stadiumDTO = new StadiumDTO();
//        stadiumDTO.setStadiumNo(stadium.getStadiumNo());
//        stadiumDTO.setStadiumName(stadium.getStadiumName());
//        // ... (다른 필드들 설정)
//
//        List<StadiumcourtDTO> stadiumCorutDTOs = stadiumReadService.getStadiumCorutsByStadiumNo(stadiumNo);
//        stadiumDTO.setStadiumCoruts(stadiumCorutDTOs);
//
//        List<StadiumFileDTO> stadiumFileDTOs = stadiumReadService.getStadiumFilesByStadiumNo(stadiumNo);
//        stadiumDTO.setStadiumFiles(stadiumFileDTOs);
//
//        List<AccountDTO> accountDTOs = stadiumReadService.getAccountsByStadiumNo(stadiumNo);
//        stadiumDTO.setAccounts(accountDTOs);
//
//        return stadiumDTO;
//    }
//    
//    
//}
