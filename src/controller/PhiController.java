package controller;

import dao.PhiDAO;
import dao.ThuPhiDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.IntegerStringConverter;
import model.Phi;
import model.ThuPhiDetails;

public class PhiController implements Initializable {

    @FXML
    private TextField textMaPhi;
    @FXML
    private TextField textTenPhi;
    @FXML
    private ComboBox<String> cbLoaiPhi;
    @FXML
    private TextField textMucThu;
    @FXML
    private BorderPane paneDSPhi;
    @FXML
    private TextField textTimKiemPhi;
    @FXML
    private TableView<Phi> tvDSPhi;
    @FXML
    private TableColumn<Phi, String> colMaPhi;
    @FXML
    private TableColumn<Phi, String> colTenPhi;
    @FXML
    private TableColumn<Phi, String> colLoaiPhi;
    @FXML
    private TableColumn<Phi, Integer> colMucThu;
    @FXML
    private BorderPane paneChiTietPhi;
    @FXML
    private Label lblTongThu;
    @FXML
    private TextField textTimKiemThuPhi;
    @FXML
    private TableView<ThuPhiDetails> tvDSThuPhi;
    @FXML
    private TableColumn<ThuPhiDetails, String> colSoHoKhau;
    @FXML
    private TableColumn<ThuPhiDetails, String> colCCCD;
    @FXML
    private TableColumn<ThuPhiDetails, String> colHoTen;
    @FXML
    private TableColumn<ThuPhiDetails, String> colDiaChi;
    @FXML
    private TableColumn<ThuPhiDetails, Integer> colSoTienDaDong;

    private ObservableList<Phi> dsPhi = FXCollections.observableArrayList();
    private ObservableList<ThuPhiDetails> dsThuPhi = FXCollections.observableArrayList();

    private PhiDAO phiDAO = new PhiDAO();
    private ThuPhiDAO thuPhiDAO = new ThuPhiDAO();

    private String selectedMaPhi;
    private int selectedMucThu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbLoaiPhi.getItems().addAll("Bắt buộc", "Không bắt buộc");

        setupTablePhi();
        setupTableThuPhi();
        loadDSPhi();

        tvDSPhi.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                xemChiTietPhi(event);
            }
        });

        paneChiTietPhi.setVisible(false);
    }    

    private void setupTablePhi() {
        colMaPhi.setCellValueFactory(new PropertyValueFactory<>("maPhi"));
        colTenPhi.setCellValueFactory(new PropertyValueFactory<>("tenPhi"));
        colLoaiPhi.setCellValueFactory(new PropertyValueFactory<>("loaiPhi"));
        colMucThu.setCellValueFactory(new PropertyValueFactory<>("mucThu"));
        tvDSPhi.setItems(dsPhi);
    }

    private void setupTableThuPhi() {
        tvDSThuPhi.setEditable(true);

        colSoHoKhau.setCellValueFactory(new PropertyValueFactory<>("soHoKhau"));
        colCCCD.setCellValueFactory(new PropertyValueFactory<>("cccd"));
        colHoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        colSoTienDaDong.setCellValueFactory(new PropertyValueFactory<>("soTien"));
        colSoTienDaDong.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colSoTienDaDong.setOnEditCommit(event -> {
            ThuPhiDetails detail = event.getRowValue();
            int soTienMoi = event.getNewValue();

            if (soTienMoi < selectedMucThu) {
                showAlert(
                    "Dữ liệu không hợp lệ",
                    "Số tiền đã đóng phải ≥ mức thu (" + selectedMucThu + ")"
                );

                detail.setSoTien(event.getOldValue());
                tvDSThuPhi.refresh();
                return;
            }

            detail.setSoTien(soTienMoi);

            try {
                thuPhiDAO.update(selectedMaPhi, detail.getCCCD(), soTienMoi);
                lblTongThu.setText(String.valueOf(
                        thuPhiDAO.getTongThu(selectedMaPhi)
                ));
            } catch (SQLException e) {
                showAlert("Lỗi", "Cập nhật thất bại: " + e.getMessage());

                detail.setSoTien(event.getOldValue());
                tvDSThuPhi.refresh();
            }
        });
        tvDSThuPhi.setItems(dsThuPhi);
    }

    private void loadDSPhi() {
        try {
            dsPhi.setAll(phiDAO.getAll());
        } catch (SQLException e) {
            showAlert("Lỗi", "Không thể tải danh sách phí: " + e.getMessage());
        }
    }

    @FXML
    private void dangKy(ActionEvent event) {
        if (!validatePhi()) return;

        try {
            Phi phi = new Phi();
            phi.setMaPhi(textMaPhi.getText());
            phi.setTenPhi(textTenPhi.getText());
            phi.setLoaiPhi(cbLoaiPhi.getValue());
            if (phi.getLoaiPhi().equals("Bắt buộc")) {
                phi.setMucThu(Integer.parseInt(textMucThu.getText()));
            }
            phiDAO.insert(phi);
            thuPhiDAO.insert(phi.getMaPhi());
            showAlert("Thành công", "Đăng ký phí thành công!");
            lamMoi(event);
            loadDSPhi();
        } catch (SQLException e) {
            showAlert("Lỗi", "Đăng ký phí thất bại: " + e.getMessage());
        } catch (NumberFormatException e) {
            showAlert("Lỗi", "Mức thu phải là số!");
        }
    }

    private boolean validatePhi() {
        if (textMaPhi.getText().isEmpty() || textMaPhi.getText().length() != 4) {
            showAlert("Lỗi", "Mã phí phải là 4 ký tự!");
            return false;
        }
        if (textTenPhi.getText().isEmpty()) {
            showAlert("Lỗi", "Tên phí không được để trống!");
            return false;
        }
        if (cbLoaiPhi.getValue() == null) {
            showAlert("Lỗi", "Vui lòng chọn loại phí!");
            return false;
        }
        if (cbLoaiPhi.getValue().equals("Bắt buộc") && (textMucThu.getText().isEmpty() || Integer.parseInt(textMucThu.getText()) <= 0)) {
            showAlert("Lỗi", "Phí bắt buộc phải có mức thu > 0!");
            return false;
        }
        return true;
    }

    @FXML
    private void lamMoi(ActionEvent event) {
        textMaPhi.clear();
        textTenPhi.clear();
        cbLoaiPhi.setValue(null);
        textMucThu.clear();
    }

    @FXML
    private void timKiemPhi(ActionEvent event) {
        String keyword = textTimKiemPhi.getText().trim();
        if (keyword.isEmpty()) {
            loadDSPhi();
            return;
        }
        try {
            dsPhi.setAll(phiDAO.search(keyword));
        } catch (SQLException e) {
            showAlert("Lỗi", "Tìm kiếm thất bại: " + e.getMessage());
        }
    }

    @FXML
    private void xemChiTietPhi(MouseEvent event) {
        Phi selected = tvDSPhi.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selectedMaPhi = selected.getMaPhi();
            selectedMucThu = selected.getMucThu();
            try {
                dsThuPhi.setAll(thuPhiDAO.getByMaPhi(selectedMaPhi));
                lblTongThu.setText(String.valueOf(thuPhiDAO.getTongThu(selectedMaPhi)));
                paneDSPhi.setVisible(false);
                paneChiTietPhi.setVisible(true);
            } catch (SQLException e) {
                showAlert("Lỗi", "Không thể tải chi tiết phí: " + e.getMessage());
            }
        }
    }

    @FXML
    private void quayLaiDanhSachPhi(ActionEvent event) {
        paneChiTietPhi.setVisible(false);
        paneDSPhi.setVisible(true);
        loadDSPhi();
    }

    @FXML
    private void timKiemThuPhi(ActionEvent event) {
        String keyword = textTimKiemThuPhi.getText().trim();
        if (keyword.isEmpty()) {
            try {
                dsThuPhi.setAll(thuPhiDAO.getByMaPhi(selectedMaPhi));
            } catch (SQLException e) {
                showAlert("Lỗi", "Tìm kiếm thất bại: " + e.getMessage());
            }
            return;
        }
        try {
            dsThuPhi.setAll(thuPhiDAO.searchByMaPhi(selectedMaPhi, keyword));
        } catch (SQLException e) {
            showAlert("Lỗi", "Tìm kiếm thất bại: " + e.getMessage());
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void xoaPhi(ActionEvent event) {
        if (selectedMaPhi == null) {
            showAlert("Thông báo", "Vui lòng chọn một loại phí để xóa");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận xóa");
        alert.setHeaderText("Bạn có chắc chắn muốn xóa phí này?");
        alert.setContentText("Mã phí: " + selectedMaPhi + "\nHành động này không thể hoàn tác!");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                phiDAO.delete(selectedMaPhi);
                showAlert("Thành công", "Đã xóa phí thành công");

                quayLaiDanhSachPhi(event);
                
                selectedMaPhi = null;
                selectedMucThu = 0;
            } catch (SQLException e) {
                showAlert("Lỗi", "Không thể xóa phí: " + e.getMessage());
            }
        }
    }
}