package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.*;

public class Controller {
    public Canvas canvas;
    @FXML
    private Text name;

    public void setText(String text) {
        name.setText(text);
    }
    public GraphicsContext gr;
    public ListView<AbstractProductArrow> viewArrow;
    private ObservableList<AbstractProductArrow> items;
    private AbstractProductArrow currentArrow1;
    private boolean start;
    public void initialize() {
        start = true;
        ArrowDependence currentArrow = new FactoryDependence().CreateArrow(1, 5, 55, 50);
        ArrowAssociation arrowAssociation = new FactoryAssociation().CreateArrow(1, 5, 55, 50);
        ArrowImpl arrowIml = new FactoryImpl().CreateArrow(1, 5, 55, 50);
        ArrowInherit arrowInherit = new FactoryInherit().CreateArrow(1, 5, 55, 50);
        ArrowComp arrowComp = new FactoryComp().CreateArrow(1, 5, 55, 50);
        items = FXCollections.observableArrayList(currentArrow, arrowAssociation, arrowIml, arrowInherit, arrowComp);
        viewArrow.setItems(items);
        viewArrow.getSelectionModel().select(0);
        gr = canvas.getGraphicsContext2D();
        currentArrow1 = null;
        viewArrow.setCellFactory(new Callback<ListView<AbstractProductArrow>, ListCell<AbstractProductArrow>>() {
            @Override
            public ListCell<AbstractProductArrow> call(ListView<AbstractProductArrow> listView) {
                return (ListCell<AbstractProductArrow>) new ListCell() {
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        AbstractProductArrow abstractArrow = ((AbstractProductArrow) item);
                        if (abstractArrow != null) {
                            Canvas cnv = new Canvas();
                            cnv.setHeight(60);// ?????????????? ?????????????? ???????????????? ??????????????????????
                            cnv.setWidth(100);
                            GraphicsContext gr1 = cnv.getGraphicsContext2D();
                            abstractArrow.setStartX(10);
                            abstractArrow.setEndX(55);
                            abstractArrow.setStartY(10);
                            abstractArrow.setEndY(55);
                            abstractArrow.draw(gr1);
                            setGraphic(cnv);
                        }
                    }
                };
            }
        });
    }

    public void mousePressed(MouseEvent mouseEvent) {
        if (start) {
            currentArrow1 = (AbstractProductArrow) items.get(viewArrow.getSelectionModel().getSelectedIndex()).clone();
            currentArrow1.setStartX(mouseEvent.getX());
            currentArrow1.setStartY(mouseEvent.getY());
            Controller.this.setText(currentArrow1.toString());
            start = false;
        } else {
            if (currentArrow1 != null) {
                currentArrow1.setEndX(mouseEvent.getX());
                currentArrow1.setEndY(mouseEvent.getY());
                currentArrow1.draw(gr);
                start = true;
            }
        }
    }

    public void mouseReleased(MouseEvent mouseEvent) {

    }
}