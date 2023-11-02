package itfact.entp.task.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import itfact.common.response.dto.ResponseDTO;
import itfact.common.response.enums.ResponseCode;
import itfact.common.util.FileUtils;
import itfact.common.util.ResponseUtil;
import itfact.common.util.StringUtils;
import itfact.entp.task.dto.TaskAtchDTO;
import itfact.entp.task.dto.TaskDTO;
import itfact.entp.task.dto.TaskMembDTO;
import itfact.entp.task.service.TaskService;
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
@RequestMapping("/entp/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

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
    public ResponseDTO getTaskDtlInfo(@RequestBody TaskDTO taskDTO) {
        TaskAtchDTO taskAtchDTO = new TaskAtchDTO();

        int task_unq = taskDTO.getTask_unq();
        taskAtchDTO.setTask_unq(task_unq);



        return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_SEARCH);
    }
}
