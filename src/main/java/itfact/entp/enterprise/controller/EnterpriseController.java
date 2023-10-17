package itfact.entp.enterprise.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import itfact.common.response.dto.ResponseDTO;
import itfact.common.response.enums.ResponseCode;
import itfact.common.util.CommonConstant;
import itfact.common.util.FileUtils;
import itfact.common.util.ResponseUtil;
import itfact.common.util.StringUtils;
import itfact.entp.enterprise.dto.EnterpriseAtchDTO;
import itfact.entp.enterprise.dto.EnterpriseCustDTO;
import itfact.entp.enterprise.dto.EnterpriseDTO;
import itfact.entp.enterprise.dto.EnterpriseSvcDTO;
import itfact.entp.enterprise.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.UriUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/entp/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @PostMapping("/getEnterpriseList")
    public ResponseDTO getEnterpriseList(@RequestBody EnterpriseDTO reqDto) {

        List<EnterpriseDTO> enterpriseList = enterpriseService.getEnterpriseList(reqDto);

        HashMap<String, Object> enterpriseMap = new HashMap<>();
        enterpriseMap.put("enterpriseList", enterpriseList);

        return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_SEARCH, enterpriseMap);
    }

    @PostMapping("/getEnterpriseDtlInfo")
    public ResponseDTO getEnterpriseDtlInfo(@RequestBody EnterpriseDTO reqDTO) {

        EnterpriseAtchDTO enterpriseAtchDTO = new EnterpriseAtchDTO();

        int entp_unq = reqDTO.getEntp_unq();
        enterpriseAtchDTO.setEntp_unq(entp_unq);

        HashMap<String, Object> enterpriseDtlMap = new HashMap<>();

        EnterpriseDTO enterpriseDTO = enterpriseService.getEnterprise(entp_unq);
        enterpriseDtlMap.put("enterpriseData", enterpriseDTO);

        List<EnterpriseCustDTO> enterpriseCustDTOList = enterpriseService.getEnterpriseCustListInfo(entp_unq);
        enterpriseDtlMap.put("enterpriseCustData", enterpriseCustDTOList);

        List<EnterpriseSvcDTO> enterpriseSvcDTOList = enterpriseService.getEnterpriseSvcListInfo(entp_unq);
        enterpriseDtlMap.put("enterpriseSvcData", enterpriseSvcDTOList);

        List<EnterpriseAtchDTO> enterpriseAtchDTOList = enterpriseService.getEnterpriseAtchList(enterpriseAtchDTO);
        enterpriseDtlMap.put("enterpriseAtchData", enterpriseAtchDTOList);

        return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_SEARCH, enterpriseDtlMap);

    }

    @PostMapping("/getCustList")
    public ResponseDTO getCustList(@RequestBody EnterpriseCustDTO reqDTO) {
        int entp_unq = reqDTO.getEntp_unq();

        List<EnterpriseCustDTO> enterpriseCustDTO = enterpriseService.getEnterpriseCustListInfo(entp_unq);

        return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_SEARCH, enterpriseCustDTO);
    }

    @PostMapping("/setEnterpriseInfo")
    public ResponseDTO setEnterpriseInfo(@RequestParam(value="enterpriseData") String enterpriseData, @RequestParam(value="custData") String custData, @RequestParam(value="files", required = false) List<MultipartFile> files, @RequestParam(value = "systemData") String systemData) throws JsonProcessingException {

        if (!FileUtils.isPermisionFileMimeType(files)) {
            return ResponseUtil.ERROR(ResponseCode.FILE_PERMISION_FILE_MIME_TYPE);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        EnterpriseDTO enterpriseDTO = objectMapper.readValue(enterpriseData, EnterpriseDTO.class);

        if (StringUtils.isEmpty(enterpriseDTO.getFlag())){
            return ResponseUtil.SUCCESS(ResponseCode.INVALID_INPUT_VALUE);
        }

        EnterpriseCustDTO enterpriseCustDTO = objectMapper.readValue(custData, EnterpriseCustDTO.class);

        List<EnterpriseSvcDTO> enterpriseSvcDTOList = objectMapper.readValue(systemData, new TypeReference<List<EnterpriseSvcDTO>>() {});

        boolean result = enterpriseService.setEnterpriseInfo(enterpriseDTO, enterpriseCustDTO, files, enterpriseSvcDTOList);

        if (result) {
            if (StringUtils.equals(enterpriseDTO.getFlag(), "I") || StringUtils.equals(enterpriseDTO.getFlag(), "S")) {
                return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_SAVE);

            } else if (StringUtils.equals(enterpriseDTO.getFlag(), "U")) {
                return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_UPDATE);

            } else if (StringUtils.equals(enterpriseDTO.getFlag(), "D")) {
                return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_DELETE);
            }
        }

        return ResponseUtil.SUCCESS(ResponseCode.FAIL_SAVE);
    }

    @PostMapping("/setCustInfo")
    public ResponseDTO insertCustInfo(@RequestBody EnterpriseCustDTO enterpriseCustDTO) {

        if (StringUtils.isEmpty(enterpriseCustDTO.getFlag())){
            return ResponseUtil.SUCCESS(ResponseCode.INVALID_INPUT_VALUE);
        }

        boolean result = enterpriseService.insertEnterpriseCustInfo(enterpriseCustDTO);

        return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_SAVE);
    }

    @PostMapping("/atch/{atchFileUnq}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable("atchFileUnq") int atchFileUnq) throws MalformedURLException {

        EnterpriseAtchDTO enterpriseAtchDTO = new EnterpriseAtchDTO();
        enterpriseAtchDTO.setAtch_file_unq(atchFileUnq);

        List<EnterpriseAtchDTO> enterpriseAtchList = enterpriseService.getEnterpriseAtchList(enterpriseAtchDTO);

        if (enterpriseAtchList.size() == 0) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        //db에서 파일 경로 가져오기
        UrlResource resource = new UrlResource("file:" + uploadPath + "/" + CommonConstant.DEFAULT_UPLOAD_ENTERPRISE_DIR + "/" + enterpriseAtchList.get(0).getAtch_file_nm());

        //실제 파일명
        String encodedFileName = UriUtils.encode(enterpriseAtchList.get(0).getAtch_file_org_nm(), StandardCharsets.UTF_8);

        //파일 다운로드 대화상자가 뜨도록 헤더를 설정
        // Content-Disposition 헤더에 attachment; filename="업로드 파일명" 값을 줌.
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
    }

    @PostMapping("/deleteCustInfo")
    public ResponseDTO deleteCustInfo(@RequestBody EnterpriseCustDTO enterpriseCustDTO) {
//        int cust_unq = reqDto.getCust_unq();

        boolean result = enterpriseService.deleteCustInfo(enterpriseCustDTO);
        return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_DELETE);
    }
}
