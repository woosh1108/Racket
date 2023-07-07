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

import com.multi.racket.bulletin_board.BulletinBoardDTO;
import com.multi.racket.bulletin_board.BulletinBoardService;
import com.multi.racket.bulletin_board_reply.BulletinBoardReplyDTO;
import com.multi.racket.bulletin_board_reply.BulletinBoardReplyService;
import com.multi.racket.domain.MemberDTO;

@Controller
public class BulletinBoardController {
	BulletinBoardService bulletinService;
	BulletinBoardReplyService bbReplyService;

	@Autowired
	public BulletinBoardController(BulletinBoardService bulletinService, BulletinBoardReplyService bbReplyService) {
		super();
		this.bulletinService = bulletinService;
		this.bbReplyService = bbReplyService;
	}

	@GetMapping("/nextPage/bulletin")
	public String nextPage(@RequestParam(defaultValue = "0") int currentPage, Model model) {
		int nextPage = currentPage + 1;
		return "redirect:/bulletin?pageNo=" + nextPage;
	}

	// 페이징 자동처리
	@GetMapping("/pagingdata/bulletin")
	public String bulletinPage(Model model) {
		int pageSize = 10; // 페이지 당 공지사항 수 // 추가
		BulletinBoardDTO bulletinlist = (BulletinBoardDTO) bulletinService.bulletinBoardlist(0);// 0추가
		long totalPages = bulletinService.getTotalPages(pageSize); //
		model.addAttribute("announcementlist", bulletinlist);
		model.addAttribute("totalPages", totalPages); //
		return "thymeleaf/bulletin_board/bulletin_board";
	}

	// 전체게시판 페이지
	@GetMapping("/bulletin")
	public String bulletinPage_page(@RequestParam(defaultValue = "0") String pageNo, Model model) {
		int pageNumber = Integer.parseInt(pageNo);
		int pageSize = 10;
		if (pageNo == null) {
			pageNo = "0";
			pageNumber = Integer.parseInt(pageNo);
		}

		if (pageNumber > bulletinService.getTotalPages(pageSize)) {
			pageNumber = 0;
		}

		List<BulletinBoardDTO> bulletinlist = bulletinService.bulletinBoardlist(pageNumber);
		long totalPages = bulletinService.getTotalPages(pageSize);
		model.addAttribute("bulletinlist", bulletinlist);
		model.addAttribute("totalPages", totalPages);

		// 현재 페이지가 10을 초과할 경우 ">>" 버튼 추가
		// 정확하게 구현이 안됨. 이건 나중에 구현하는걸로
		if (pageNumber > 10) {
			model.addAttribute("hasNextPage", true);
		} else {
			model.addAttribute("hasNextPage", false);
		}

		return "thymeleaf/bulletin_board/bulletin_board";
	}

	// 게시글 등록
	@PostMapping("/bulletin")
	public String bulletinWrite(BulletinBoardDTO bulletinBoard) {
		// 제목을 입력하지 않으면 자동으로 제목없음으로 들어감.
		String title = bulletinBoard.getBbTitle();

		if (title == null || title.isEmpty()) {
			bulletinBoard.setBbTitle("제목없음");
		}

		bulletinService.Bulletin_insert(bulletinBoard);
		return "redirect:/bulletin";
	}

	@ResponseBody
	@RequestMapping(value = "/bulletin/uploadSummernoteImageFiles", method = RequestMethod.POST)
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
			// 지현씨 src/webapp/resources/announcement_fileupload/ 파일을 이렇게 만들어주시고 보내드린 이미지 전부
			// 이 디렉토리 안에 넣으시면 게시판 사진 전부 보일거에요!!!
			// **

			// **추가로 해당 경로 그러니까 src/webapp/resources/bulletin_board_fileupload/에 존재하는 파일들을
			// 삭제하면 기존
			// 게시물에 있는 사진들이 안 보이게 된다. 꼭 필요한 경우만 삭제하던가 아니면 게시글 자체를 삭제하는 게 나으려나..?
			String savePath = root + "/bulletin_board_fileupload/";
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
				jsonObject.put("url", contextPath + "/resources/bulletin_board_fileupload/" + boardFileRename);
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
	@RequestMapping("/bulletin_read")
	public String bulletinRead(@RequestParam("bbNo") Integer bbNo, String state, Model model, HttpSession session) {

		BulletinBoardDTO bulletinBoard = bulletinService.read(bbNo);

		// 작성자와 로그인한 사용자 비교 ==> 게시글 수정 삭제버튼에 대한 내용 ==> 댓글에도 적용이 되나?
		String memberId = bulletinBoard.getMemberId(); // 게시글 작성자 아이디
		MemberDTO loginUser = (MemberDTO) session.getAttribute("user"); // 로그인한 사용자 정보
		String loginUserId = loginUser != null ? loginUser.getMemberId() : null; // 로그인한 사용자 아이디

		boolean isAuthor = false;
		boolean isAuthorReply = false;

		if (memberId != null && memberId.equals(loginUserId)) {
			isAuthor = true;
		}

		// 조회수 증가
		int views = bulletinBoard.getBbViews();
		bulletinBoard.setBbViews(views + 1);
		bulletinService.save(bulletinBoard);

		String view = "";
		if (state != null && state.equals("READ")) {
			view = "thymeleaf/bulletin_board/bulletin_board_read";
		} else {

			return "redirect:/error";
		}

		// 댓글 목록 가져오기 - 게시글 번호에 맞게 댓글을 가져옴
		List<BulletinBoardReplyDTO> replyList = bbReplyService.bbReply_list(bbNo);

		// 댓글 개수 가져오기
		int replyCount = bbReplyService.countByBbNo(bbNo);

		// 댓글 작성자와 로그인한 사용자 아이디 비교 및 수정 가능 여부 설정
		List<Boolean> canModifyList = new ArrayList<>();
		for (BulletinBoardReplyDTO reply : replyList) {
			String replyMemberId = reply.getMemberId();
			boolean canModify = (replyMemberId != null && replyMemberId.equals(loginUserId));
			canModifyList.add(canModify);
		}

		// 모델에 replyList와 canModifyList 추가
		model.addAttribute("canModifyList", canModifyList);
		model.addAttribute("bulletinBoard", bulletinBoard);
		model.addAttribute("isAuthor", isAuthor); // 게시글에서만
		model.addAttribute("isAuthorReply", isAuthorReply); // 댓글에서만
		model.addAttribute("replyList", replyList);
		model.addAttribute("replyCount", replyCount);

		return view;
	}

	// 전체게시판 글쓰기 페이지
	@RequestMapping("/bulletin_write")
	public String bulletinBoardWritePage(Model model, HttpSession session) {
		// 로그인이 되어있어야 글쓰기를 할 수 있음.
		MemberDTO member = (MemberDTO) session.getAttribute("user");
		if (member != null) {
			String id = member.getMemberId();
			model.addAttribute("id", id);
			return "thymeleaf/bulletin_board/bulletin_board_write";
		} else {
			// 로그인되지 않은 경우에 대한 처리
			return "redirect:/login"; // 로그인 페이지로 리다이렉트
		}
	}

	// 게시글 삭제
	@PostMapping("/bulletin_delete")
	public String bulletinDelete(@RequestParam("bbNo") int bbNo) {
		bulletinService.delete(bbNo);
		return "redirect:/bulletin";
	}

	// 전체게시판 수정 페이지로 이동
	@GetMapping("/bulletin_update/{bbNo}")
	public String showUpdateForm(@PathVariable("bbNo") int bbNo, Model model) {
		// 공지사항 수정을 위해 해당 공지사항 정보를 가져온다
		BulletinBoardDTO updatedata = bulletinService.getBulletinBoard(bbNo);
		model.addAttribute("updatedata", updatedata);
		return "thymeleaf/bulletin_board/bulletin_board_update";
	}

	// 전체게시판 수정 처리
	@PostMapping("/bulletin_update")
	public String updateBulletinBoard(@ModelAttribute("updatedata") BulletinBoardDTO updatedata) {
		bulletinService.update(updatedata);
		return "redirect:/bulletin"; // 수정 완료 후 전체게시판 목록 페이지로 이동
	}

	// 게시글 검색기능
	@PostMapping("/bulletin_search")
	public String search(String data, Model model) {
		List<BulletinBoardDTO> searchlist = bulletinService.search(data);
		model.addAttribute("searchlist", searchlist);
		return "thymeleaf/bulletin_board/bulletin_board_search";

	}

	/* 댓글 */

	// 댓글 작성
	@PostMapping("/bulletin_reply_insert")
	public String addComment(@RequestParam("bbNo") Integer bbNo, String content, HttpSession session) {

		// 댓글 작성
		BulletinBoardReplyDTO bbReply = new BulletinBoardReplyDTO();
		bbReply.setBbNo(bbNo); // 게시글 번호 설정
		bbReply.setBbReplyContent(content);

		MemberDTO loginUser = (MemberDTO) session.getAttribute("user");
		if (loginUser != null) {
			bbReply.setMemberId(loginUser.getMemberId());
		} else {
			return "redirect:/login";
		}

		bbReplyService.bbReply_insert(bbReply);

		return "redirect:/bulletin_read?bbNo=" + bbNo + "&state=READ";
	}

	// 댓글 삭제
	@PostMapping("/bulletin_reply_delete")
	public String bbReplyDelete(@RequestParam("bbNo") int bbNo, @RequestParam("bbReplyNo") int bbReplyNo) {
		bbReplyService.delete(bbReplyNo);
		return "redirect:/bulletin_read?bbNo=" + bbNo + "&state=READ";
	}

	// 댓글 수정폼
	@GetMapping("bulletin_reply_update/{bbReplyNo}")
	public String showUpdateFormReply(@PathVariable("bbReplyNo") int bbReplyNo, Model model,
			@RequestParam("bbNo") Integer bbNo) {
		BulletinBoardReplyDTO updatedataReply = bbReplyService.getbbReply(bbReplyNo);
		BulletinBoardDTO bulletinBoard = bulletinService.read(bbNo);
		// 댓글 목록 가져오기 - 게시글 번호에 맞게 댓글을 가져옴
		List<BulletinBoardReplyDTO> replyList = bbReplyService.bbReply_list(bbNo);

		// 댓글 개수 가져오기
		int replyCount = bbReplyService.countByBbNo(bbNo);
		model.addAttribute("updatedataReply", updatedataReply);
		model.addAttribute("bulletinBoard", bulletinBoard);
		model.addAttribute("replyList", replyList);
		model.addAttribute("replyCount", replyCount);

		return "thymeleaf/bulletin_board/bulletin_board_reply_update";
	}

	// 댓글 수정
	@PostMapping("bulletin_reply_update")
	public String updatebbReply(@ModelAttribute("updatedataReply") BulletinBoardReplyDTO updatedataReply,
			@RequestParam("bbNo") int bbNo) {
		bbReplyService.update(updatedataReply);
		return "redirect:/bulletin_read?bbNo=" + bbNo + "&state=READ";
	}

}
