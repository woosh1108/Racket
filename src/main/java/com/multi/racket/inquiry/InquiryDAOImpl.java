package com.multi.racket.inquiry;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.multi.racket.domain.InquiryDTO;
@Repository
public class InquiryDAOImpl implements InquiryDAO {
	private EntityManager entityManager;
	
	@Autowired
	public InquiryDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public InquiryDTO insert(InquiryDTO inquiry) {
		entityManager.persist(inquiry);
		return inquiry;
	}

	@Override
	public List<InquiryDTO> findAll() {
		String jpql = "select inquiry from InquiryDTO as inquiry";
		List<InquiryDTO> list = entityManager.createQuery(jpql,InquiryDTO.class)
				                           .getResultList();
		return list;
	}

	@Override
	public InquiryDTO read(int inquiryNo) {
		InquiryDTO inquiry = entityManager.find(InquiryDTO.class, inquiryNo);
		return inquiry;
	}

}
