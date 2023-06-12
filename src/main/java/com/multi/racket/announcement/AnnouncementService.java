package com.multi.racket.announcement;

import java.util.List;

public interface AnnouncementService {

	// 게시글 등록
	public int Announcement_insert(AnnouncementDTO Announcement);

	// 공지시항 전체 목록
	public List<AnnouncementDTO> announcementlist();
}
