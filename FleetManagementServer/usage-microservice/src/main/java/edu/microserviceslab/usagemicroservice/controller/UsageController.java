package edu.microserviceslab.usagemicroservice.controller;

import edu.microserviceslab.usagemicroservice.dto.PidRequest;
import edu.microserviceslab.usagemicroservice.dto.UsageStatisticRequest;
import edu.microserviceslab.usagemicroservice.entity.UsageStatistic;
import edu.microserviceslab.usagemicroservice.service.interfaces.UsageService;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/usage")
public class UsageController {

    private UsageService usageService;

    public UsageController(UsageService usageService) {
        this.usageService = usageService;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<UsageStatistic> listAllUsageStatistics() {
        return usageService.getAllUsageStatistics();
    }

    @ResponseBody
    @RequestMapping("/vehicle/{vehicleId}")
    public List<UsageStatistic> listAllUsageStatisticsForVehicle(@PathVariable("vehicleId") Long vehicleId) {
        return usageService.getUsageStatisticsPerVehicle(vehicleId);
    }

    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public List<UsageStatistic> addUsageStatistic(@RequestBody UsageStatisticRequest usageStatisticRequest) {
        List<UsageStatistic> usageStatistics = new LinkedList<>();
        for (PidRequest pidRequest: usageStatisticRequest.getPids()) {
            UsageStatistic usageStatistic = new UsageStatistic();
            usageStatistic.setVehicleId(usageStatisticRequest.getVehicleId());
            usageStatistic.setPid(pidRequest.getPid());
            usageStatistic.setData(pidRequest.getData());
            usageStatistics.add(usageService.addUsageStatistic(usageStatistic));
        }

        return usageStatistics;
    }
}
