package coordinate.view;

import coordinate.model.Figure;

public class ResultView {

    public void showArea(Figure figure) {
        System.out.println(figure.reportArea());
    }

    public void showErrorMessage(Exception ex) {
        System.out.println(ex.getMessage());
    }
}
