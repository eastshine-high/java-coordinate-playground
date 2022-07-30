package coordinate.controller;

import coordinate.dto.CoordinateInfo;
import coordinate.model.Figure;
import coordinate.model.FigureFactory;
import coordinate.view.InputView;
import coordinate.view.ResultView;

public class CoordinateCalculator {
    private final InputView inputView;
    private final ResultView resultView;
    private final FigureFactory figureFactory;

    public CoordinateCalculator(InputView inputView, ResultView resultView, FigureFactory figureFactory) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.figureFactory = figureFactory;
    }

    public void run() {
        try {
            CoordinateInfo coordinateInfo = inputView.askCoordinateInfo();
            Figure figure = figureFactory.create(coordinateInfo.toPointList());
            resultView.showArea(figure);

        } catch (Exception ex) {
            resultView.showErrorMessage(ex);
            run();
        }
    }
}
