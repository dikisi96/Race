package resume;

import resume.vehicles.Car;
import resume.vehicles.Motorbike;
import resume.vehicles.Truck;
import resume.vehicles.Vehicle;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
public class Parameters {
    private double length;
    private List<Vehicle> vehicles;


    public double getLength() {
        return length;
    }

    @XmlElement(name = "length")
    public void setLength(double length) {
        this.length = length;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    @XmlElementWrapper(name = "vehicles")
    @XmlElements({
            @XmlElement(name = "car", type = Car.class),
            @XmlElement(name = "motorbike", type = Motorbike.class),
            @XmlElement(name = "truck", type = Truck.class),
    })
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
