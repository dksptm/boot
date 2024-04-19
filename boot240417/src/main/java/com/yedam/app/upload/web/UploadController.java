package com.yedam.app.upload.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@GetMapping("upload") 
	public void getUploadPage() {
		// void => return "upload" 
		// => classpath:/templates/upload.html
	}
	
	@PostMapping("formUpload")
	public String formUploadFile(@RequestPart MultipartFile[] images) {
		// @RequestPart MultipartFile 은 한쌍이다.
		// 그런데 경우에 따라서 하나의 클래스로 넘어올수도 있다.
		// 하나의 클래스안에 이미지에 대한 정보가 넘어올수있는데 -> 필드에 데이터타입을 MultipartFile하고
		// @RequestPart은 필요없음. => 물론 이 방식은 잘 사용안함..
		String uri = "home";
		for(MultipartFile image : images) {
			if(image.getContentType().startsWith("image") == false) {
				System.out.println("No Image");
				return "home";
			}
			String originalName = image.getOriginalFilename(); // 경로포함된 이름이.. 아닌듯
			System.out.println("original : " + originalName);
			String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
			System.out.println("fileName : " + fileName);
			
			//String uploadPath = "C:\\upload"; -- 위의 value 어노테이션 등록후 삭제.
			String savaName = uploadPath + File.separator + fileName;
			// File.separator => 자바는 \\ 인식을 못함. 
			// 그래서 File.separator 자바가 인식하는 경로구분자 사용
			System.out.println("savaName : " + savaName);
			
			Path savePath = Paths.get(savaName); // 실제 경로로 변환하는 작업(문자열을 가지고 실제 경로로 변환)
			System.out.println("savePath : " + savePath);
			
			try {
				// transferTo() : 실제 파일을 저장하는 MultipartFile의 메서드.
				// MultipartFile이 스트림을 열고.. 바이트를 다 이동시키고..
				image.transferTo(savePath); 
				System.out.println("종료.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			uri = "redirect:/images/" + fileName;
		}
		System.out.println("uri : " + uri);
		
		return uri;
	}
	
	/**
	 * 아작스 이용해서 업로드.
	 **/
	
	@PostMapping("/uploadsAjax")
	@ResponseBody
	public List<String> uploadFile(@RequestPart MultipartFile[] uploadFiles) {
	    
		List<String> imageList = new ArrayList<>();
		
	    for(MultipartFile uploadFile : uploadFiles){
	    	if(uploadFile.getContentType().startsWith("image") == false){
	    		System.err.println("this file is not image type");
	    		return null;
	        }
	  
	        String originalName = uploadFile.getOriginalFilename();
	        String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
	        
	        System.out.println("fileName : " + fileName);
	    
	        // 서버 내부에서 멀티미디어 폴더를 만들경우, 날짜별로 폴더 만드는 경우가 많다.
	        // 날짜 폴더 생성
	        String folderPath = makeFolder();
	        //UUID
	        String uuid = UUID.randomUUID().toString();
	        // 시간을 기준으로(밀리초) 랜덤값을 가져옴.
	        
	        // 저장할 파일 이름 중간에 "_"를 이용하여 구분
	        String uploadFileName = folderPath +File.separator + uuid + "_" + fileName;
	        System.out.println("uploadFileName : " + uploadFileName);
	        
	        String saveName = uploadPath + File.separator + uploadFileName;
	        
	        Path savePath = Paths.get(saveName);
	        //Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
	        System.out.println("path : " + saveName);
	        try{
	        	uploadFile.transferTo(savePath);
	            //uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
	        } catch (IOException e) {
	             e.printStackTrace();	             
	        }
	        // DB에 해당 경로 저장
	        // 1) 사용자가 업로드할 때 사용한 파일명
	        // 2) 실제 서버에 업로드할 때 사용한 경로
	        imageList.add(setImagePath(uploadFileName)); // 미리보기.. 서버에안보내고 js에서도 가능한 방법이 있다고함..
	     }
	    
	    return imageList;
	}
	
	/** makeFolder() **/
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// LocalDate를 문자열로 포멧
		String folderPath = str.replace("/", File.separator);
		// java가 인식할수있도록 /를 자바방식으로..
		
		File uploadPathFoler = new File(uploadPath, folderPath);
		// File newFile= new File("파일명", dir);
		if (uploadPathFoler.exists() == false) {
			uploadPathFoler.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		
		return folderPath;
	}
	 
	private String setImagePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
	}

}
