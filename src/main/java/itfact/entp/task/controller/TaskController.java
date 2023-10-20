package itfact.entp.task.controller;


import itfact.common.response.dto.ResponseDTO;
import itfact.common.response.enums.ResponseCode;
import itfact.common.util.ResponseUtil;
import itfact.entp.task.dto.TaskMembDTO;
import itfact.entp.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
