package com.multi.racket.announcement;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

import com.multi.racket.announcement.AnnouncementDTO;

public interface AnnouncementDAO {

	// 게시글 등록
	public String Announcement_insert(AnnouncementDTO Announcement);

	// 공지시항 전체 목록
	// 페이징
	public List<AnnouncementDTO> announcementlist(int pageNo);

	// 게시글 상세보기
	public AnnouncementDTO read(int announcementNo);

	// 게시글 삭제
	public void delete(int announcementNo);

	// 게시글 수정
	public void update(AnnouncementDTO updatedata);

	// 게시글 수정페이지 - 수정버튼을 누르면 글을 가져옴
	public AnnouncementDTO getAnnouncement(int announcementNo);

	// 페이지번호를 동적으로 생성하기 위한 메소드
	public long getTotalPages(int pageSize);

	// 검색하여 공지사항 게시글 찾기
	public List<AnnouncementDTO> search(String data);

}
