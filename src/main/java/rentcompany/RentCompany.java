package rentcompany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 우리 회사는 렌터카를 운영하고 있다.
 * 현재 보유하고 있는 차량은 Sonata 2대, Avante 1대, K5 2대로 총 5대의 차량을 보유하고 있다.
 * 우리 회사는 고객이 인터넷으로부터 예약할 때 여행할 목적지의 대략적인 이동거리를 입력 받는다.
 * 이 이동거리를 활용해 차량 별로 필요한 연료를 주입한다.
 * 차량 별로 주입해야 할 연료량을 확인할 수 있는 보고서를 생성해야 한다.
 *
 * 각 차량별 연비는 다음과 같다.
 * * Sonata : 10km/리터
 * * Avante : 15km/리터
 * * K5 : 13km/리터
 */
public class RentCompany {
    private static final String NEWLINE = System.getProperty("line.separator");

    private static List<Car> carList = new ArrayList<>();

    public static RentCompany create() {
        return new RentCompany();
    }

    void addCar(Car car) {
        carList.add(car);
    }


    String generateReport() {
        return carList.stream()
                .map(car -> car.getName() + " : " + (int)car.getChargeQuantity() + "리터")
                .collect(Collectors.joining(NEWLINE)) + NEWLINE;
    }
}
