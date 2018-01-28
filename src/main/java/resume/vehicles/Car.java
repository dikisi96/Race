package resume.vehicles;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Car extends Vehicle {
    private int peopleCount;

    @Override
    public void messageWithParameters() {
        super.messageWithParameters();
        System.out.println("Количество людей в машине: " + peopleCount + "\n");
    }

    @XmlElement(name = "peopleCount")
    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }
}
