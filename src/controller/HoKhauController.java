// HoKhauController.java
package controller;

import dao.HoKhauDAO;
import dao.ThuongTruDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.HoKhauWithInfo;
import model.ThuongTruWithTen;

public class HoKhauController implements Initializable {

    @FXML
    private BorderPane paneDSHoKhau;
    @FXML
    private TextField textTimKiem;
    @FXML
    private TableView<HoKhauWithInfo> tvDSHoKhau;
    @FXML
    private TableColumn<HoKhauWithInfo, String> colSoHoKhau;
    @FXML
    private TableColumn<HoKhauWithInfo, String> colTenChuHo;
    @FXML
    private TableColumn<HoKhauWithInfo, String> colDiaChi;
    @FXML
    private TableColumn<HoKhauWithInfo, Integer> colSLThanhVien;
    @FXML
    private BorderPane paneChiTietHoKhau;
    @FXML
    private TableView<ThuongTruWithTen> tvChiTietHoKhau;
    @FXML
    private TableColumn<ThuongTruWithTen, String> colHoTenThanhVien;
    @FXML
    private TableColumn<ThuongTruWithTen, String> colQHChuHo;

    private ObservableList<HoKhauWithInfo> dsHoKhau = FXCollections.observableArrayList();
    private ObservableList<ThuongTruWithTen> dsChiTiet = FXCollections.observableArrayList();

    private HoKhauDAO hoKhauDAO = new HoKhauDAO();
    private ThuongTruDAO thuongTruDAO = new ThuongTruDAO();

    private String selectedSoHoKhau;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTableDS();
        setupTableChiTiet();
        loadDSHoKhau();

        tvDSHoKhau.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                xemChiTietHoKhau(event);
            }
        });

        paneChiTietHoKhau.setVisible(false);
    }    

    private void setupTableDS() {
        colSoHoKhau.setCellValueFactory(new PropertyValueFactory<>("soHoKhau"));
        colTenChuHo.setCellValueFactory(new PropertyValueFactory<>("tenChuHo"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        colSLThanhVien.setCellValueFactory(new PropertyValueFactory<>("slThanhVien"));
        tvDSHoKhau.setItems(dsHoKhau);
    }

    private void setupTableChiTiet() {
        tvChiTietHoKhau.setEditable(true);

        colHoTenThanhVien.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        colQHChuHo.setCellValueFactory(new PropertyValueFactory<>("quanHeChuHo"));
        colQHChuHo.setCellFactory(TextFieldTableCell.forTableColumn());
        colQHChuHo.setOnEditCommit(event -> {
            ThuongTruWithTen tt = event.getRowValue();
            tt.setQuanHeChuHo(event.getNewValue());
        });

        tvChiTietHoKhau.setItems(dsChiTiet);
    }

    private void loadDSHoKhau() {
        try {
            dsHoKhau.setAll(hoKhauDAO.getAllWithInfo());
        } catch (SQLException e) {
            showAlert("Lỗi", "Không thể tải danh sách hộ khẩu: " + e.getMessage());
        }
    }

    @FXML
    private void timKiem(ActionEvent event) {
        String keyword = textTimKiem.getText().trim();
        if (keyword.isEmpty()) {
            loadDSHoKhau();
            return;
        }
        try {
            dsHoKhau.setAll(hoKhauDAO.searchWithInfo(keyword));
        } catch (SQLException e) {
            showAlert("Lỗi", "Tìm kiếm thất bại: " + e.getMessage());
        }
    }

    @FXML
    private void xemChiTietHoKhau(MouseEvent event) {
        HoKhauWithInfo selected = tvDSHoKhau.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selectedSoHoKhau = selected.getSoHoKhau();
            try {
                dsChiTiet.setAll(thuongTruDAO.getBySoHoKhauWithTen(selectedSoHoKhau));
                paneDSHoKhau.setVisible(false);
                paneChiTietHoKhau.setVisible(true);
            } catch (SQLException e) {
                showAlert("Lỗi", "Không thể tải chi tiết hộ: " + e.getMessage());
            }
        }
    }

    @FXML
    private void quayLaiDSHoKhau(ActionEvent event) {
        paneChiTietHoKhau.setVisible(false);
        paneDSHoKhau.setVisible(true);
        loadDSHoKhau();
    }

    @FXML
    private void chinhSuaHoKhau(ActionEvent event) {
        int chuHoCount = 0;
        for (ThuongTruWithTen tt : dsChiTiet) {
            if (tt.getQuanHeChuHo().equals("Chủ hộ")) chuHoCount++;
        }
        if (chuHoCount != 1) {
            showAlert("Lỗi", "Phải có đúng một chủ hộ!");
            return;
        }

        try {
            for (ThuongTruWithTen ttWithTen : dsChiTiet) {
                if (ttWithTen.getQuanHeChuHo().isEmpty()) {
                    showAlert("Lỗi", "Quan hệ chủ hộ không được để trống!");
                    return;
                }
                thuongTruDAO.updateQuanHeChuHo(selectedSoHoKhau, ttWithTen.getCccd(), ttWithTen.getQuanHeChuHo());
            }
            showAlert("Thành công", "Chỉnh sửa thành công!");
        } catch (SQLException e) {
            showAlert("Lỗi", "Chỉnh sửa thất bại: " + e.getMessage());
        }
    }

    @FXML
    private void xoaHoKhau(ActionEvent event) {
        try {
            if (hoKhauDAO.canDelete(selectedSoHoKhau)) {
                hoKhauDAO.delete(selectedSoHoKhau);
                showAlert("Thành công", "Xóa hộ khẩu thành công!");
                quayLaiDSHoKhau(event);
            } else {
                showAlert("Lỗi", "Vẫn còn nhân khẩu đang thường trú!");
            }
        } catch (SQLException e) {
            showAlert("Lỗi", "Xóa thất bại: " + e.getMessage());
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}