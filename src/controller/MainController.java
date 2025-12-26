package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainController implements Initializable {

    @FXML
    private Label arrowGiayTo;
    @FXML
    private VBox giayToMenu;
    
    private boolean giayToExpanded = false;
    @FXML
    private Pane contentPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void toggleGiayTo(MouseEvent event) {
        giayToExpanded = !giayToExpanded;

        giayToMenu.setVisible(giayToExpanded);
        giayToMenu.setManaged(giayToExpanded);

        arrowGiayTo.setText(giayToExpanded ? "▼" : "▶");
    }

    private void loadView(String path) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        contentPane.getChildren().setAll(root);
    }
    
    @FXML
    private void loadNhanKhau(ActionEvent event) throws IOException {
        loadView("/view/NhanKhau.fxml");
    }

    @FXML
    private void loadHoKhau(ActionEvent event) throws IOException {
        loadView("/view/HoKhau.fxml");
    }

    @FXML
    private void loadDKThuongTru(ActionEvent event) throws IOException {
        loadView("/view/form/DangKyThuongTru.fxml");
    }

    @FXML
    private void loadDKTamTru(ActionEvent event) throws IOException {
        loadView("/view/form/DangKyTamTru.fxml");
    }

    @FXML
    private void loadDKTamVang(ActionEvent event) throws IOException {
        loadView("/view/form/DangKyTamVang.fxml");
    }

    @FXML
    private void loadPhi(ActionEvent event) throws IOException {
        loadView("/view/Phi.fxml");
    }

    @FXML
    private void loadTrangChu(ActionEvent event) throws IOException {
        loadView("/view/TrangChu.fxml");
    }
}
