package coordinate.controller;

import coordinate.model.Figure;
import coordinate.model.FigureFactory;
import coordinate.model.Point;
import coordinate.model.PointMapper;
import coordinate.view.InputView;
import coordinate.dto.CoordinateInfo;
import coordinate.view.ResultView;

import java.util.List;

public class CoordinateCalculator {
    private final InputView inputView;
    private final ResultView resultView;
    private final PointMapper pointMapper;
    private final FigureFactory figureFactory;

    public CoordinateCalculator(InputView inputView, ResultView resultView, PointMapper pointMapper, FigureFactory figureFactory) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.pointMapper = pointMapper;
        this.figureFactory = figureFactory;
    }

    public void run() {
        try {
            CoordinateInfo coordinateInfo = inputView.askCoordinateInfo();
            List<Point> points = pointMapper.toPointList(coordinateInfo);
            Figure figure = figureFactory.create(points);
            resultView.showArea(figure);

        } catch (Exception ex) {
            resultView.showErrorMessage(ex);
            run();
        }
    }
}
