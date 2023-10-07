package itfact.common.file.service;

import itfact.common.file.dto.FileDTO;
import itfact.common.util.CommonConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @Override
    public FileDTO saveFile(MultipartFile files) {
        return this.saveFile(files, CommonConstant.DEFAULT_UPLOAD_ENTERPRISE_DIR);
    }

    @Override
    public FileDTO saveFile(MultipartFile files, String uploadFileDir) {
        FileDTO fileDto = new FileDTO();

        try{

            if (files == null || files.isEmpty()) {
                return fileDto;
            }

            File directory = new File(uploadPath + "/" + uploadFileDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            //오리지널 파일 이름 추출
            String origName = files.getOriginalFilename();

            //파일 이름으로 쓸 uuid생성
            String uuid = UUID.randomUUID().toString();

            //파일 확장자 추출
            String extension = origName.substring(origName.lastIndexOf("."));
            
            //uuid와 확장자 결합
            String saveName = uuid + extension;

            //파일을 불러올 때 사용할 파일 경로
            String savePath = uploadPath + "/" + uploadFileDir + "/" + saveName;

            fileDto.setAtch_file_org_nm(origName);
            fileDto.setAtch_file_path(savePath);
            fileDto.setAtch_file_nm(saveName);
            
            //실제 로컬에 uuid를 파일명으로 저장
            files.transferTo(new File(savePath));

        }catch (Exception e) {
            e.printStackTrace();
        }

        return fileDto;
    }

    @Override
    public List<FileDTO> saveFile(List<MultipartFile> files, String uploadFileDir) {
        List<FileDTO> atchFileList = new ArrayList();

        try {

            if(files == null || files.isEmpty())
            {
                return atchFileList;
            }

            for (MultipartFile file : files) {
                //폴더생성
                File directory = new File(uploadPath + "/" + uploadFileDir);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // 원래 파일 이름 추출
                String origName = file.getOriginalFilename();

                // 파일 이름으로 쓸 uuid 생성
                String uuid = UUID.randomUUID().toString();

                // 확장자 추출(ex : .png)
                String extension = origName.substring(origName.lastIndexOf("."));

                // uuid와 확장자 결합
                String savedName = uuid + extension;

                // 파일을 불러올 때 사용할 파일 경로
                String savedPath = uploadPath + "/" + uploadFileDir + "/" + savedName;

                // 실제로 로컬에 uuid를 파일명으로 저장
                file.transferTo(new File(savedPath));

                atchFileList.add(new FileDTO(savedName ,origName, savedPath, file.getSize()));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return atchFileList;
    }
}
