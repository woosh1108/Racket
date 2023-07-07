package com.multi.racket.controller;

import java.io.File;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.multi.racket.announcement.AnnouncementDTO;
import com.multi.racket.announcement.AnnouncementService;
import com.multi.racket.domain.MemberDTO;

@Controller
public class AnnouncementController {
	AnnouncementService announcementService;

	@Autowired
	public AnnouncementController(AnnouncementService announcementService) {
		super();
		this.announcementService = announcementService;
	}

	@GetMapping("/nextPage/announcement")
	public String nextPage(@RequestParam(defaultValue = "0") int currentPage, Model model) {
		int nextPage = currentPage + 1;
		return "redirect:/announcement?pageNo=" + nextPage;
	}

	// 페이징 자동처리
	@GetMapping("/pagingdata/announcement")
	public String announcementPage(Model model) {
		int pageSize = 10; // 페이지 당 공지사항 수 // 추가
		AnnouncementDTO announcelist = (AnnouncementDTO) announcementService.announcementlist(0); // 0추가
		long totalPages = announcementService.getTotalPages(pageSize); //
		model.addAttribute("announcementlist", announcelist);
		model.addAttribute("totalPages", totalPages); //
		return "thymeleaf/announcement/announcement";
	}

	// 공지사항 페이지 - 공지사항 리스트
	// 동적으로 페이지번호 생성함.
	@GetMapping("/announcement")
	public String announcementPage_page(@RequestParam(defaultValue = "0") String pageNo, Model model,
			HttpSession session) {
		MemberDTO member = (MemberDTO) session.getAttribute("user");
		int pageNumber = Integer.parseInt(pageNo);
		int pageSize = 10; // 페이지 당 공지사항 수
		if (pageNo == null) {
			pageNo = "0"; // 기본 페이지 번호 설정
			pageNumber = Integer.parseInt(pageNo);
		}

		if (pageNumber > announcementService.getTotalPages(pageSize)) {
			pageNumber = 0; // 페이지 번호가 총 페이지 수를 초과하는 경우 첫 페이지로 설정
		}

		List<AnnouncementDTO> announcelist = announcementService.announcementlist(pageNumber);
		long totalPages = announcementService.getTotalPages(pageSize);
		model.addAttribute("announcementlist", announcelist);
		model.addAttribute("totalPages", totalPages);

		// 현재 페이지가 10을 초과할 경우 ">>" 버튼 추가
		if (pageNumber > 10) {
			model.addAttribute("hasNextPage", true);
		} else {
			model.addAttribute("hasNextPage", false);
		}

		// 관리자 아이디 또는 멤버 권한이 2인 경우에 글쓰기 버튼을 보이도록 설정
		if (member != null && member.getMemberAuth()==2) {
			model.addAttribute("isAdmin", true);
		}

		return "thymeleaf/announcement/announcement";
	}

	// 게시글 등록
	@PostMapping("/announcement")
	public String announcementWrite(AnnouncementDTO announcement) {
		// 제목을 입력하지 않으면 자동으로 제목없음으로 들어감.
		String title = announcement.getAnnouncementTitle();

		if (title == null || title.isEmpty()) {
			announcement.setAnnouncementTitle("제목없음");
		}

		announcementService.Announcement_insert(announcement);
		return "redirect:/announcement";
	}

	@ResponseBody
	@RequestMapping(value = "/announcement/uploadSummernoteImageFiles", method = RequestMethod.POST)
	public List<Map<String, Object>> uploadSummernoteImageFiles(@RequestParam("files") MultipartFile[] multipartFiles,
	        HttpServletRequest request) {
		
		// jsonObjcet로하면 에러나서 Map으로 바꿔서 작업..
	    List<Map<String, Object>> resultList = new ArrayList<>();

	    // 파일저장 외부 경로, 파일명, 저장할 파일명
	    try {
	        String root = request.getSession().getServletContext().getRealPath("resources");
	        // 해당 경로는 밑에 url과 똑같이 작업해줘야 에러가 안 뜬다..
	        // 해보니까 기본 webapp로 잡히고 거기에서 root + savapath + 이미지파일이 된다.
	        // 그러니까 결국 src/webapp/resources/announcement_fileupload에 파일이 저장된다.
	        
	        // **
	        // 지현씨 src/webapp/resources/announcement_fileupload/ 파일을 이렇게 만들어주시고 보내드린 이미지 전부 이 디렉토리 안에 넣으시면 게시판 사진 전부 보일거에요!!!
	        // **
	        
	        // **추가로 해당 경로 그러니까 src/webapp/resources/announcement_fileupload/에 존재하는 파일들을 삭제하면 기존
	        // 게시물에 있는 사진들이 안 보이게 된다. 꼭 필요한 경우만 삭제하던가 아니면 게시글 자체를 삭제하는 게 나으려나..?
	        String savePath = root + "/announcement_fileupload/";
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	        
	        // 기존에는 이미지파일 1개만 변환돼서 올라갔기에 for문으로 이미지파일이 여러개가 되어도 전부 변환해서 좀 더 잘 돌아가도록 작성함.
	        // 와 확실히 이렇게 작업하니까 쾌적하네... 430000글자는 좀... 선 넘었지...
	        for (MultipartFile multipartFile : multipartFiles) {
	            Map<String, Object> jsonObject = new HashMap<>();

	            String originalFileName = multipartFile.getOriginalFilename();
	            String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
	            String boardFileRename = sdf.format(new Date(System.currentTimeMillis())) + "." + extension;
	            File targetFile = new File(savePath);

	            if (!targetFile.exists()) {
	                targetFile.mkdir();
	            }

	            multipartFile.transferTo(new File(savePath + boardFileRename));
	            String contextPath = request.getContextPath();
	            jsonObject.put("url", contextPath + "/resources/announcement_fileupload/" + boardFileRename);
	            jsonObject.put("originName", originalFileName);
	            jsonObject.put("responseCode", "success");

	            resultList.add(jsonObject);
	        }

	    } catch (IllegalStateException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return resultList;
	}



	// 게시글 상세보기
	@RequestMapping("/announcement_read")
	public String announcementRead(Integer announcementNo, String state, Model model, HttpSession session) {
		MemberDTO member = (MemberDTO) session.getAttribute("user");
		AnnouncementDTO announcement = announcementService.read(announcementNo);
		// 조회수 증가
		int views = announcement.getAnnouncementViews();
		announcement.setAnnouncementViews(views + 1);
		announcementService.save(announcement);
		String view = "";
		if (state != null && state.equals("READ")) {
			view = "thymeleaf/announcement/announcement_read";
		} else {
			view = "";
		}

		// 관리자 아이디 또는 멤버 권한이 2인 경우에 글쓰기 버튼을 보이도록 설정
		if (member != null && member.getMemberAuth()==2) {
			model.addAttribute("isAdmin", true);
		}

		model.addAttribute("announcement", announcement);
		return view;
	}

	// 게시글 등록 페이지
	@GetMapping("/announcement_write")
	public String announcementWrite(Model model, HttpSession session) {
		// 로그인이 되어있어야 글쓰기를 할 수 있음.
		MemberDTO member = (MemberDTO) session.getAttribute("user");
		if (member != null) {
			String id = member.getMemberId();
			model.addAttribute("id", id);
			return "thymeleaf/announcement/announcement_write";
		} else {
			// 로그인되지 않은 경우에 대한 처리
			return "redirect:/login"; // 로그인 페이지로 리다이렉트
		}
	}

	// 게시글 삭제
	@PostMapping("/announcement_delete")
	public String announcementDelete(@RequestParam("announcementNo") int announcementNo) {
		announcementService.delete(announcementNo);
		return "redirect:/announcement";
	}

	// 공지사항 수정 페이지로 이동
	@GetMapping("/announcement_update/{announcementNo}")
	public String showUpdateForm(@PathVariable("announcementNo") int announcementNo, Model model) {
		// 공지사항 수정을 위해 해당 공지사항 정보를 가져온다
		AnnouncementDTO updatedata = announcementService.getAnnouncement(announcementNo);
		model.addAttribute("updatedata", updatedata);
		return "thymeleaf/announcement/announcement_update";
	}

	// 공지사항 수정 처리
	@PostMapping("/announcement_update")
	public String updateAnnouncement(@ModelAttribute("updatedata") AnnouncementDTO updatedata) {
		announcementService.update(updatedata);
		return "redirect:/announcement"; // 수정 완료 후 공지사항 목록 페이지로 이동
	}

	// 게시글 검색기능
	@PostMapping("/announcement_search")
	public String search(String data, Model model, HttpSession session) {
		MemberDTO member = (MemberDTO) session.getAttribute("user");
		List<AnnouncementDTO> searchlist = announcementService.search(data);
		model.addAttribute("searchlist", searchlist);
		if (member != null && member.getMemberId().equals("admin")) {
			model.addAttribute("isAdmin", true);
		}
		return "thymeleaf/announcement/announcement_search";

	}

}
