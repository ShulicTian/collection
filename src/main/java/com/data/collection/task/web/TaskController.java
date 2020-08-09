package com.data.collection.task.web;

import com.data.collection.common.entity.ResponseEntity;
import com.data.collection.task.entity.TaskEntity;
import com.data.collection.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collection/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 根据用户ID获取任务列表
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getTaskList", method = RequestMethod.GET)
    public ResponseEntity<List<TaskEntity>> getCommunityList(Integer userId) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setResult(taskService.getCommunityList(userId));
        responseEntity.setCode(ResponseEntity.OK);
        return responseEntity;
    }

    /**
     * 添加任务
     *
     * @param taskEntity
     * @return
     */
    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addTask(@RequestBody TaskEntity taskEntity) {
        ResponseEntity<Boolean> responseEntity = new ResponseEntity();
        try {
            taskService.addTask(taskEntity);
            responseEntity.setResult(true);
            responseEntity.setCode(ResponseEntity.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity.setResult(false);
        }
        return responseEntity;
    }

    /**
     * 删除任务
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeTask", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> removeTask(Integer id) {
        ResponseEntity<Boolean> responseEntity = new ResponseEntity();
        try {
            taskService.removeTask(id);
            responseEntity.setResult(true);
            responseEntity.setCode(ResponseEntity.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity.setResult(false);
        }
        return responseEntity;
    }

}
