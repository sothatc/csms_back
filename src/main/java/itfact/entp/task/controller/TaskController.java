package itfact.entp.task.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import itfact.common.response.dto.ResponseDTO;
import itfact.common.response.enums.ResponseCode;
import itfact.common.util.CommonConstant;
import itfact.common.util.FileUtils;
import itfact.common.util.ResponseUtil;
import itfact.common.util.StringUtils;
import itfact.entp.enterprise.dto.EnterpriseCustDTO;
import itfact.entp.enterprise.dto.EnterpriseDTO;
import itfact.entp.enterprise.service.EnterpriseService;
import itfact.entp.task.dto.TaskAtchDTO;
import itfact.entp.task.dto.TaskDTO;
import itfact.entp.task.dto.TaskMembDTO;
import itfact.entp.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/entp/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @PostMapping("/getTaskList")
    public ResponseDTO getTaskList(@RequestBody TaskDTO taskDTO) {
        List<TaskDTO> taskList = taskService.getTaskList(taskDTO);

        HashMap<String, Object> taskListMap = new HashMap<>();
        taskListMap.put("taskList", taskList);

        return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_SEARCH, taskListMap);
    }

    @PostMapping("/getTaskMembList")
    public ResponseDTO getTaskMembList() {
        List<TaskMembDTO> taskMembList = taskService.getTaskMembList();

        return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_SEARCH, taskMembList);
    }

    @PostMapping("/setTaskInfo")
    public ResponseDTO setTaskInfo(@RequestParam(value = "taskData") String taskData, @RequestParam(value="files", required = false)List<MultipartFile> files) throws JsonProcessingException {

        if (!FileUtils.isPermisionFileMimeType(files)) {
            return ResponseUtil.ERROR(ResponseCode.FILE_PERMISION_FILE_MIME_TYPE);
        }

        ObjectMapper objectMapper = new ObjectMapper();

        TaskDTO taskDTO = objectMapper.readValue(taskData, TaskDTO.class);

        if (StringUtils.isEmpty(taskDTO.getFlag())){
            return ResponseUtil.SUCCESS(ResponseCode.INVALID_INPUT_VALUE);
        }


        boolean result = taskService.setTaskInfo(taskDTO, files);

        if (result) {
            if (StringUtils.equals(taskDTO.getFlag(), "I") || StringUtils.equals(taskDTO.getFlag(), "S")) {
                return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_SAVE);

            } else if (StringUtils.equals(taskDTO.getFlag(), "U")) {
                return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_UPDATE);

            } else if (StringUtils.equals(taskDTO.getFlag(), "D")) {
                return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_DELETE);
            }
        }

        return ResponseUtil.SUCCESS(ResponseCode.FAIL_SAVE);
    }

    @PostMapping("/getTaskDtlInfo")
    public ResponseDTO getTaskDtlInfo(@RequestBody TaskDTO reqDTO) {
        TaskAtchDTO taskAtchDTO = new TaskAtchDTO();

        int task_unq = reqDTO.getTask_unq();
        taskAtchDTO.setTask_unq(task_unq);

        HashMap<String, Object> taskDtlMap = new HashMap<>();

        TaskDTO taskDTO = taskService.getTaskInfo(task_unq);
        taskDtlMap.put("taskData", taskDTO);

        EnterpriseDTO enterpriseDTO = enterpriseService.getEnterprise(taskDTO.getEntp_unq());
        taskDtlMap.put("enterpriseData", enterpriseDTO);

        List<TaskAtchDTO> taskAtchDTOList = taskService.getTaskAtchList(taskAtchDTO);
        taskDtlMap.put("taskAtchData", taskAtchDTOList);

        EnterpriseCustDTO enterpriseCustDTO = enterpriseService.getEnterpriseCustOneInfo(taskDTO.getCust_unq());
        taskDtlMap.put("enterpriseCustData", enterpriseCustDTO);

        return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_SEARCH, taskDtlMap);
    }

    @PostMapping("/atch/{atchFileUnq}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable("atchFileUnq") int atchFileUnq) throws MalformedURLException {

        TaskAtchDTO taskAtchDTO = new TaskAtchDTO();
        taskAtchDTO.setAtch_file_unq(atchFileUnq);

        List<TaskAtchDTO> taskAtchDTOList = taskService.getTaskAtchList(taskAtchDTO);

        if (taskAtchDTOList.size() == 0) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        UrlResource resource = new UrlResource("file", uploadPath + "/" + CommonConstant.DEFAULT_UPLOAD_TASK_DIR + "/" + taskAtchDTOList.get(0).getAtch_file_nm());
        String encodedFileName = UriUtils.encode(taskAtchDTOList.get(0).getAtch_file_org_nm(), StandardCharsets.UTF_8);

        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
    }
}
