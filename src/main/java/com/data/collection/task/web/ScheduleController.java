package com.data.collection.task.web;

import com.data.collection.common.entity.ResponseEntity;
import com.data.collection.task.entity.ScheduleEntity;
import com.data.collection.task.service.ScheduleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 任务分配
 *
 * @author tianslc
 */
@RestController
@RequestMapping("/collection/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * 获取任务分配列表
     *
     * @return
     */
    @RequestMapping(value = "/getScheduleList", method = RequestMethod.GET)
    public ResponseEntity<List<ScheduleEntity>> getScheduleList() {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setResult(scheduleService.getScheduleList());
        responseEntity.setCode(ResponseEntity.OK);
        return responseEntity;
    }

    /**
     * 添加任务
     *
     * @param scheduleEntity
     * @return
     */
    @RequestMapping(value = "/saveSchedule", method = RequestMethod.POST)
    public ResponseEntity<Boolean> saveSchedule(@RequestBody ScheduleEntity scheduleEntity) {
        ResponseEntity<Boolean> responseEntity = new ResponseEntity();
        try {
            scheduleService.saveSchedule(scheduleEntity);
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
    @RequestMapping(value = "/removeSchedule", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> removeSchedule(Integer id) {
        ResponseEntity<Boolean> responseEntity = new ResponseEntity();
        try {
            scheduleService.removeSchedule(id);
            responseEntity.setResult(true);
            responseEntity.setCode(ResponseEntity.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity.setResult(false);
        }
        return responseEntity;
    }

}
