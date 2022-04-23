package coordinate.view;

import coordinate.dto.CoordinateInfo;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * 사용자 문자열을 입력받아 좌표 정보를 반환한다.
     *
     * @return 좌표 정보
     */
    public CoordinateInfo askCoordinateInfo() {
        System.out.println("좌표를 입력하세요.");

        String inputString = scanner.nextLine();
        return new CoordinateInfo(inputString);
    }
}
