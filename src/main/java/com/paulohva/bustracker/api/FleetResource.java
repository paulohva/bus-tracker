package com.paulohva.bustracker.api;

import com.paulohva.bustracker.dto.Fleet;
import com.paulohva.bustracker.services.DublinkedDataService;
import com.paulohva.bustracker.util.ConverterUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/fleet")
public class FleetResource {

    private DublinkedDataService service;

    public FleetResource(DublinkedDataService service) {
        this.service = service;
    }

    @ApiOperation(value = "Find running fleet by a time frame", nickname = "find")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Fleet>> find(
            @RequestParam(value = "date", required = true) String date,
            @RequestParam(value = "startTime", required = true) String startTime,
            @RequestParam(value = "endTime", required = true) String endTime) {
        LocalDateTime startDateTime = ConverterUtils.getLocalDateTimeFromString(date, startTime);
        LocalDateTime endDateTime = ConverterUtils.getLocalDateTimeFromString(date, endTime);
        List<Fleet> obj = service.findOperators(ConverterUtils.getMicrosecondsFromLocalDateTime(startDateTime)
                , ConverterUtils.getMicrosecondsFromLocalDateTime(endDateTime));
        return ResponseEntity.ok().body(obj);
    }
}
