package com.multi.racket.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.StadiumDTO;
@Repository
public class ManageDAOImpl implements ManageDAO {
//	private EntityManager entityManager;
//	@Autowired
//	public ManageDAOImpl(EntityManager entityManager) {
//		super();
//		this.entityManager = entityManager;
//	}
	
	private ManageRepository repository;
	@Autowired
	public ManageDAOImpl(ManageRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<StadiumDTO> findAll() {
		return repository.findByStadiumStatus("1");
	}
	
	@Override
	public List<StadiumDTO> find_grant() {
		// TODO Auto-generated method stub
		return null;
	}
	
//	@Override
//	public List<StadiumDTO> find_grant() {
//		String jpql = "select stadium from StadiumDTO as stadium where stadiumStatus='1'";
//		List<StadiumDTO> list = entityManager.createQuery(jpql,StadiumDTO.class)
//			         					   .getResultList();
//		return list;
//	}
//
//	@Override
//	public List<StadiumDTO> findAll() {
//		String jpql = "select stadium from StadiumDTO as stadium";
//		List<StadiumDTO> list = entityManager.createQuery(jpql,StadiumDTO.class)
//			         					   .getResultList();
//		return list;
//	}
	
}
