package itfact.entp.enterprise.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import itfact.common.response.dto.ResponseDTO;
import itfact.common.response.enums.ResponseCode;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/entp/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @PostMapping("/getEnterpriseList")
    public ResponseDTO getEnterpriseList(@RequestBody EnterpriseDTO reqDto) {

        List<EnterpriseDTO> enterpriseList = enterpriseService.getEnterpriseList(reqDto);

        HashMap<String, Object> enterpriseMap = new HashMap<>();
        enterpriseMap.put("enterpriseList", enterpriseList);

        return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_SEARCH, enterpriseMap);
    }

    @PostMapping("/getEnterpriseDtlInfo")
    public ResponseDTO getEnterpriseDtlInfo(@RequestBody EnterpriseDTO reqDTO) {

        int entp_unq = reqDTO.getEntp_unq();

        HashMap<String, Object> enterpriseDtlMap = new HashMap<>();

        EnterpriseDTO enterpriseDTO = enterpriseService.getEnterprise(entp_unq);
        enterpriseDtlMap.put("enterpriseData", enterpriseDTO);

        List<EnterpriseCustDTO> enterpriseCustDTOList = enterpriseService.getEnterpriseCustListInfo(entp_unq);
        enterpriseDtlMap.put("enterpriseCustData", enterpriseCustDTOList);

        List<EnterpriseSvcDTO> enterpriseSvcDTOList = enterpriseService.getEnterpriseSvcListInfo(entp_unq);
        enterpriseDtlMap.put("enterpriseSvcData", enterpriseSvcDTOList);

        List<EnterpriseAtchDTO> enterpriseAtchDTOList = enterpriseService.getEnterpriseAtchList(entp_unq);
        enterpriseDtlMap.put("enterpriseAtchData", enterpriseAtchDTOList);

        return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_SEARCH, enterpriseDtlMap);

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

//    @PostMapping("/setEnterpriseSvcInfo")
//    public EnterpriseSvcDTO setEnterpriseSvcInfo(EnterpriseSvcDTO enterpriseSvcDTO) {
//        boolean result = enterpriseService.setEnterpriseSvcInfo(enterpriseSvcDTO);
//
//        return ResponseUtil.SUCCESS(ResponseCode.INVALID_INPUT_VALUE);
//    }

}
