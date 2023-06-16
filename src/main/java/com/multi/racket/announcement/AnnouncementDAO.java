package com.multi.racket.announcement;

import java.util.List;

public interface AnnouncementDAO {
	
	//게시글 등록
//	public AnnouncementDTO Announcement_insert(AnnouncementDTO Announcement);
	
	//게시글 등록
	public String Announcement_insert(AnnouncementDTO Announcement);
	
	//공지시항 전체 목록
	public List<AnnouncementDTO> announcementlist();
	
	//게시글 상세보기
	public AnnouncementDTO read(int announcementNo);
	
	//게시글 삭제
	public void delete(int announcementNo);
	
	//게시글 수정
	public AnnouncementDTO update(int announcementNo, AnnouncementDTO announcementDTO);
	
	//게시글 수정페이지 - 수정버튼을 누르면 글을 가져옴
	public AnnouncementDTO getAnnouncement(int announcementNo);
	
}
