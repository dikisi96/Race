package resume.vehicles;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Motorbike extends Vehicle {
    private boolean sidecar;

    @XmlElement(name = "sidecar")
    public void setSidecar(boolean sidecar) {
        this.sidecar = sidecar;
    }

    @Override
    public void messageWithParameters() {
        super.messageWithParameters();
        System.out.println("Коляска: " + (sidecar ? "Да" : "Нет")+ "\n");
    }
}
