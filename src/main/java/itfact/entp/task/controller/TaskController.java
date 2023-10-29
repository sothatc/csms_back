package itfact.entp.task.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import itfact.common.response.dto.ResponseDTO;
import itfact.common.response.enums.ResponseCode;
import itfact.common.util.ResponseUtil;
import itfact.entp.task.dto.TaskDTO;
import itfact.entp.task.dto.TaskMembDTO;
import itfact.entp.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/entp/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/getTaskMembList")
    public ResponseDTO getTaskMembList() {
        List<TaskMembDTO> taskMembList = taskService.getTaskMembList();

        return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_SEARCH, taskMembList);
    }

    @PostMapping("/setTaskInfo")
    public ResponseDTO setTaskInfo(@RequestParam(value = "taskData") String taskData) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        TaskDTO taskDTO = objectMapper.readValue(taskData, TaskDTO.class);
        boolean result = taskService.setTaskInfo(taskDTO);
        return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_SAVE);
    }
}
