package ru.shaa.sbt.shoppingmngr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shaa.sbt.shoppingmngr.entities.ScheduleType;
import ru.shaa.sbt.shoppingmngr.repositories.IScheduleTypeRepository;

import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {


    private final String sharedKey = "SHARED_KEY";

    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    private static final int CODE_SUCCESS = 100;
    private static final int AUTH_FAILURE = 102;

    @GetMapping
    public BaseResponse showStatus() {
        return new BaseResponse(SUCCESS_STATUS, 1);
    }

    @PostMapping("/Add")
    public BaseResponse Add(@RequestParam(value = "key") String key, @RequestBody PlanRequest request) {

        final BaseResponse response;

        if (sharedKey.equalsIgnoreCase(key)) {
            int idGood = request.getIdGood();
            int idSchedule = request.getIdSchedule();
            response = new BaseResponse(SUCCESS_STATUS, CODE_SUCCESS);
        } else {
            response = new BaseResponse(ERROR_STATUS, AUTH_FAILURE);
        }
        return response;
    }

    @Autowired
    IScheduleTypeRepository scheduleTypeRepository;

    @GetMapping("ScheduleTypes")
    public List<ScheduleType> scheduleTypeGetAll()
    {
        List<ScheduleType> scheduleTypesList = scheduleTypeRepository.getAll();
        return scheduleTypesList;
    }
}