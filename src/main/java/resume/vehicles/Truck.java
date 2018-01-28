package resume.vehicles;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Truck extends Vehicle {
    private double cargoWeight;

    @XmlElement(name = "cargoWeight")
    public void setCargoWeight(double cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    @Override
    public void messageWithParameters() {
        super.messageWithParameters();
        System.out.println("Количество груза: " + cargoWeight + "\n");
    }
}
