package com.multi.racket.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumFileDTO;

@Service
public class FileUploadLogicService {
	@Value("${upload.path}")
	private String uploadPath;
	
	public String getUploadPath(String fileName) {
		return uploadPath + fileName;
	}

	public List<StadiumFileDTO> uploadFiles(List<MultipartFile> multipartFiles,StadiumDTO stadium)
			throws IllegalStateException, IOException {
		List<StadiumFileDTO> filetolist = new ArrayList<StadiumFileDTO>();
		System.out.println("======================");
		System.out.println(stadium);
		System.out.println("======================");
		int count = 1;
		for (MultipartFile multipartFile : multipartFiles) {
			// stadiumFileDTO 객체 생성방법..
			// filedtolist.add(new StadiumFileDTO(null, 1,multipartFile.getOriginalFilename(), storeFilename, count + ""));
			String storeFilename = uploadFile(multipartFile);
			filetolist.add(new StadiumFileDTO(stadium.getStadiumNo(),multipartFile.getOriginalFilename(),storeFilename,count+""));
			count++;
		}
		return filetolist;
	}

	public String uploadFile(MultipartFile multipartFile) throws IOException {
		String originalFileName = multipartFile.getOriginalFilename();
		String storeFilename = createstoreFilename(originalFileName);
		if (!multipartFile.isEmpty()) {
			multipartFile.transferTo(new File(getUploadPath(storeFilename)));
			System.out.println("원본파일명:" + originalFileName);
			System.out.println("변경된파일명:" + storeFilename);
		}
		return storeFilename;
	}

	private String createstoreFilename(String originalFileName) {
		int pos = originalFileName.lastIndexOf(".");// 시작 인덱스 지정하면 인덱스부터 끝까지 문자열 추출
		String ext = originalFileName.substring(pos + 1);
		String uuid = UUID.randomUUID().toString();
		return uuid + "." + ext;
	}
}
