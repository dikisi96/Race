package resume;

import resume.vehicles.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Main {
    private static Logger fileLogger = LoggerFactory.getLogger("FILE");
    private static String VEHICLES_FILE_PATH = "Vehicles.xml";
    private static String PARAMETERS_FILE_PATH = "Parameters.xsd";

    public static void main(String[] args) throws IOException {
        File xmlFile = new File(ClassLoader.getSystemClassLoader().getResource(VEHICLES_FILE_PATH).getFile());
        File schemaFile = new File(ClassLoader.getSystemClassLoader().getResource(PARAMETERS_FILE_PATH).getFile());
        Parameters parameters = XMLParser.parse(xmlFile, schemaFile);
        List<Vehicle> list = parameters.getVehicles();
        double circleSize = parameters.getLength();
        for (Vehicle vehicle : list) {
            vehicle.setCircleSize(circleSize);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                for (Vehicle vehicle : list) {
                    vehicle.messageWithParameters();
                }
                try {
                    ExecutorService executorService = Executors.newFixedThreadPool(list.size());
                    List<Future<Result>> futureList = executorService.invokeAll(list);
                    executorService.shutdown();
                    futureList.sort((o1, o2) -> {
                        int compareResult = 0;
                        try {
                            compareResult = Double.compare(o1.get().getTime(), o2.get().getTime());
                        } catch (ExecutionException | InterruptedException e) {
                            fileLogger.error(e.getMessage());
                        }
                        return compareResult;
                    });
                    System.out.println();
                    System.out.println("Транспорт | Время |");
                    for (Future<Result> resultFuture : futureList) {
                        System.out.println(resultFuture.get().getName() + " | " + resultFuture.get().getTime() + " |");
                    }
                    System.out.println("Нажмите Y, чтобы повторить заезд");
                } catch (InterruptedException | ExecutionException e) {
                    fileLogger.error(e.getMessage());
                }
            } while (reader.readLine().equals("Y"));
        }
    }
}