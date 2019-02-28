package edu.microserviceslab.vehiclemicroservice.controller;

import edu.microserviceslab.vehiclemicroservice.entity.Vehicle;
import edu.microserviceslab.vehiclemicroservice.service.interfaces.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<Vehicle> listAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @ResponseBody
    @RequestMapping("/licensePlate/{vehicleId}")
    public String getVehicleLicensePlate(@PathVariable("vehicleId") Long vehicleId) {
        if (vehicleId == null) {
            throw new IllegalStateException("Vehicle Id should not be null");
        }

        return vehicleService.getVehicleLicensePlate(vehicleId);
    }

    @ResponseBody
    @RequestMapping("/{vehicleID}")
    public Vehicle getVehicleById(@PathVariable("vehicleID") Long vehicleId) {
        if (vehicleId == null) {
            throw new IllegalStateException("Vehicle Id is null");
        }

        return vehicleService.getVehicleById(vehicleId);
    }
    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalStateException("Please submit a vehicle to add.");
        }
        if (vehicle.getRegistration() == null) {
            throw new IllegalStateException("Vehicle should have a registration.");
        }
        return vehicleService.addVehicle(vehicle);
    }
}
