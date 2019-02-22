package edu.microserviceslab.usagemicroservice.controller;

import edu.microserviceslab.usagemicroservice.entity.UsageStatistic;
import edu.microserviceslab.usagemicroservice.service.interfaces.UsageService;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping("/driver/{driverId}")
    public List<UsageStatistic> listAllUsageStatisticsForDriver(@PathVariable("driverId") Long driverId) {
        return usageService.getUsageStatisticsPerDriver(driverId);
    }

    @ResponseBody
    @RequestMapping("/vehicle/{vehicleId}")
    public List<UsageStatistic> listAllUsageStatisticsForVehicle(@PathVariable("vehicleId") Long vehicleId) {
        return usageService.getUsageStatisticsPerVehicle(vehicleId);
    }

    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public UsageStatistic addUsageStatistic(@RequestBody UsageStatistic driver) {
        if (driver == null) {
            throw new IllegalStateException("Please submit statistics to add.");
        }
        if (driver.getVehicleId() == null) {
            throw new IllegalStateException("The driver needs to have a vehicle assigned to him.");
        }
        if(usageService.getUsageStatisticsPerVehicle(driver.getVehicleId()) != null){
            return usageService.getUsageStatisticsPerVehicle(driver.getVehicleId()).get(0);
        }

        return usageService.addUsageStatistic(driver);
    }
}
