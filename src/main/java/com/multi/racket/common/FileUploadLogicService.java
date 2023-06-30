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
			// stadiumFileDTO 媛앹껜 �깮�꽦諛⑸쾿..
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
			System.out.println("�썝蹂명뙆�씪紐�:" + originalFileName);
			System.out.println("蹂�寃쎈맂�뙆�씪紐�:" + storeFilename);
		}
		return storeFilename;
	}

	private String createstoreFilename(String originalFileName) {
		int pos = originalFileName.lastIndexOf(".");// �떆�옉 �씤�뜳�뒪 吏��젙�븯硫� �씤�뜳�뒪遺��꽣 �걹源뚯� 臾몄옄�뿴 異붿텧
		String ext = originalFileName.substring(pos + 1);
		String uuid = UUID.randomUUID().toString();
		return uuid + "." + ext;
	}
}
