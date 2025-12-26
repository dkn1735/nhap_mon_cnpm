package controller.form;

import dao.HoKhauDAO;
import dao.NhanKhauDAO;
import dao.ThuongTruDAO;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.DateStringConverter;
import model.HoKhau;
import model.NhanKhau;
import model.ThuongTru;
import model.ThuongTruWithTen;

public class DangKyThuongTruController implements Initializable {

    @FXML
    private TextField txtSoHKMoi;
    @FXML
    private TextField txtDiaChiMoi;
    @FXML
    private DatePicker dpNgayDenMoi;
    @FXML
    private TextField txtNoiTrcKhiDenMoi;
    @FXML
    private TableView<NhanKhauWithQH> tvDSNhanKhauMoi;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colCCCDMoi;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colHotenMoi;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colGioiTinhMoi;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colBiDanhMoi;
    @FXML
    private TableColumn<NhanKhauWithQH, Date> colNgaySinhMoi;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colQueQuanMoi;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colDanTocMoi;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colTonGiaoMoi;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colNgheNghiepMoi;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colQHChuHoMoi;
    @FXML
    private ComboBox<String> cbSoHKNhap;
    @FXML
    private TextField txtDiaChiNhap;
    @FXML
    private TextField txtChuHoNhap;
    @FXML
    private TableView<NhanKhauWithQH> tvDSNhanKhauNhap;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colCCCDNhap;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colHotenNhap;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colGioiTinhNhap;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colBiDanhNhap;
    @FXML
    private TableColumn<NhanKhauWithQH, Date> colNgaySinhNhap;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colQueQuanNhap;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colDanTocNhap;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colTonGiaoNhap;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colNgheNghiepNhap;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colQHChuHoNhap;
    @FXML
    private TableColumn<NhanKhauWithQH, Date> colNgayDenNhap;
    @FXML
    private TableColumn<NhanKhauWithQH, String> colNoiTrcKhiDenNhap;
    @FXML
    private ComboBox<String> cbSoHoKhauTach;
    @FXML
    private TextField txtSoHoKhauTachMoi;
    @FXML
    private TextField txtDiaChiTachMoi;
    @FXML
    private TableView<ThuongTruWithTen> tvNKHoCu;
    @FXML
    private TableColumn<ThuongTruWithTen, String> colNKCu;
    @FXML
    private TableColumn<ThuongTruWithTen, String> colQHCu;
    @FXML
    private TableView<ThuongTruWithTen> tvNKHoMoi;
    @FXML
    private TableColumn<ThuongTruWithTen, String> colNKMoi;
    @FXML
    private TableColumn<ThuongTruWithTen, String> colQHMoi;

    private ObservableList<NhanKhauWithQH> dsNhanKhauMoi = FXCollections.observableArrayList();
    private ObservableList<NhanKhauWithQH> dsNhanKhauNhap = FXCollections.observableArrayList();
    private ObservableList<ThuongTruWithTen> dsNKHoCu = FXCollections.observableArrayList();
    private ObservableList<ThuongTruWithTen> dsNKHoMoi = FXCollections.observableArrayList();

    private HoKhauDAO hoKhauDAO = new HoKhauDAO();
    private NhanKhauDAO nhanKhauDAO = new NhanKhauDAO();
    private ThuongTruDAO thuongTruDAO = new ThuongTruDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTableMoi();
        setupTableNhap();
        setupTableTach();

        try {
            reloadCombobox();
        } catch (SQLException e) {
            showAlert("Lỗi", "Không thể tải danh sách hộ khẩu: " + e.getMessage());
        }

        cbSoHKNhap.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    txtDiaChiNhap.setText(hoKhauDAO.getDiaChiBySoHoKhau(newValue));
                    txtChuHoNhap.setText(hoKhauDAO.getChuHoBySoHoKhau(newValue));
                } catch (SQLException e) {
                    showAlert("Lỗi", "Không thể tải thông tin hộ khẩu: " + e.getMessage());
                }
            }
        });

        cbSoHoKhauTach.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    List<ThuongTruWithTen> list = thuongTruDAO.getBySoHoKhauWithTen(newValue);
                    dsNKHoCu.setAll(list);
                } catch (SQLException e) {
                    showAlert("Lỗi", "Không thể tải danh sách nhân khẩu: " + e.getMessage());
                }
            }
        });

        // Thêm event cho chuyển NK giữa cu và moi
        tvNKHoCu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) { // Double click
                    chuyenNKCuSangMoi();
                }
            }
        });

        tvNKHoMoi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) { // Double click
                    chuyenNKMoiSangCu();
                }
            }
        });
    }

    private void reloadCombobox() throws SQLException {
        cbSoHKNhap.getItems().clear();
        cbSoHoKhauTach.getItems().clear();
        cbSoHKNhap.getItems().addAll(hoKhauDAO.getAllSoHoKhau());
        cbSoHoKhauTach.getItems().addAll(hoKhauDAO.getAllSoHoKhau());
    }

    private void setupTableMoi() {
        tvDSNhanKhauMoi.setEditable(true);

        colCCCDMoi.setCellValueFactory(cellData -> cellData.getValue().getNhanKhau().cccdProperty());
        colCCCDMoi.setCellFactory(TextFieldTableCell.forTableColumn());
        colCCCDMoi.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.getNhanKhau().setCccd(event.getNewValue());
        });

        colHotenMoi.setCellValueFactory(cellData -> cellData.getValue().getNhanKhau().hoTenProperty());
        colHotenMoi.setCellFactory(TextFieldTableCell.forTableColumn());
        colHotenMoi.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.getNhanKhau().setHoTen(event.getNewValue());
        });

        colGioiTinhMoi.setCellValueFactory(cellData -> cellData.getValue().getNhanKhau().gioiTinhProperty());
        colGioiTinhMoi.setCellFactory(TextFieldTableCell.forTableColumn());
        colGioiTinhMoi.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.getNhanKhau().setGioiTinh(event.getNewValue());
        });

        colBiDanhMoi.setCellValueFactory(cellData -> cellData.getValue().getNhanKhau().biDanhProperty());
        colBiDanhMoi.setCellFactory(TextFieldTableCell.forTableColumn());
        colBiDanhMoi.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.getNhanKhau().setBiDanh(event.getNewValue());
        });

        colNgaySinhMoi.setCellValueFactory(cellData -> cellData.getValue().getNhanKhau().ngaySinhProperty());
        colNgaySinhMoi.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter("MM/dd/yyyy")));
        colNgaySinhMoi.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.getNhanKhau().setNgaySinh(event.getNewValue());
        });

        colQueQuanMoi.setCellValueFactory(cellData -> cellData.getValue().getNhanKhau().queQuanProperty());
        colQueQuanMoi.setCellFactory(TextFieldTableCell.forTableColumn());
        colQueQuanMoi.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.getNhanKhau().setQueQuan(event.getNewValue());
        });

        colDanTocMoi.setCellValueFactory(cellData -> cellData.getValue().getNhanKhau().danTocProperty());
        colDanTocMoi.setCellFactory(TextFieldTableCell.forTableColumn());
        colDanTocMoi.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.getNhanKhau().setDanToc(event.getNewValue());
        });

        colTonGiaoMoi.setCellValueFactory(cellData -> cellData.getValue().getNhanKhau().tonGiaoProperty());
        colTonGiaoMoi.setCellFactory(TextFieldTableCell.forTableColumn());
        colTonGiaoMoi.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.getNhanKhau().setTonGiao(event.getNewValue());
        });

        colNgheNghiepMoi.setCellValueFactory(cellData -> cellData.getValue().getNhanKhau().ngheNghiepProperty());
        colNgheNghiepMoi.setCellFactory(TextFieldTableCell.forTableColumn());
        colNgheNghiepMoi.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.getNhanKhau().setNgheNghiep(event.getNewValue());
        });

        colQHChuHoMoi.setCellValueFactory(cellData -> cellData.getValue().quanHeChuHoProperty());
        colQHChuHoMoi.setCellFactory(TextFieldTableCell.forTableColumn());
        colQHChuHoMoi.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.setQuanHeChuHo(event.getNewValue());
        });

        tvDSNhanKhauMoi.setItems(dsNhanKhauMoi);
    }

    private void setupTableNhap() {
        tvDSNhanKhauNhap.setEditable(true);

        colCCCDNhap.setCellValueFactory(cellData -> cellData.getValue().getNhanKhau().cccdProperty());
        colCCCDNhap.setCellFactory(TextFieldTableCell.forTableColumn());
        colCCCDNhap.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.getNhanKhau().setCccd(event.getNewValue());
        });

        colHotenNhap.setCellValueFactory(cellData -> cellData.getValue().getNhanKhau().hoTenProperty());
        colHotenNhap.setCellFactory(TextFieldTableCell.forTableColumn());
        colHotenNhap.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.getNhanKhau().setHoTen(event.getNewValue());
        });

        colGioiTinhNhap.setCellValueFactory(cellData -> cellData.getValue().getNhanKhau().gioiTinhProperty());
        colGioiTinhNhap.setCellFactory(TextFieldTableCell.forTableColumn());
        colGioiTinhNhap.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.getNhanKhau().setGioiTinh(event.getNewValue());
        });

        colBiDanhNhap.setCellValueFactory(cellData -> cellData.getValue().getNhanKhau().biDanhProperty());
        colBiDanhNhap.setCellFactory(TextFieldTableCell.forTableColumn());
        colBiDanhNhap.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.getNhanKhau().setBiDanh(event.getNewValue());
        });

        colNgaySinhNhap.setCellValueFactory(cellData -> cellData.getValue().getNhanKhau().ngaySinhProperty());
        colNgaySinhNhap.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter("MM/dd/yyyy")));
        colNgaySinhNhap.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.getNhanKhau().setNgaySinh(event.getNewValue());
        });

        colQueQuanNhap.setCellValueFactory(cellData -> cellData.getValue().getNhanKhau().queQuanProperty());
        colQueQuanNhap.setCellFactory(TextFieldTableCell.forTableColumn());
        colQueQuanNhap.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.getNhanKhau().setQueQuan(event.getNewValue());
        });

        colDanTocNhap.setCellValueFactory(cellData -> cellData.getValue().getNhanKhau().danTocProperty());
        colDanTocNhap.setCellFactory(TextFieldTableCell.forTableColumn());
        colDanTocNhap.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.getNhanKhau().setDanToc(event.getNewValue());
        });

        colTonGiaoNhap.setCellValueFactory(cellData -> cellData.getValue().getNhanKhau().tonGiaoProperty());
        colTonGiaoNhap.setCellFactory(TextFieldTableCell.forTableColumn());
        colTonGiaoNhap.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.getNhanKhau().setTonGiao(event.getNewValue());
        });

        colNgheNghiepNhap.setCellValueFactory(cellData -> cellData.getValue().getNhanKhau().ngheNghiepProperty());
        colNgheNghiepNhap.setCellFactory(TextFieldTableCell.forTableColumn());
        colNgheNghiepNhap.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.getNhanKhau().setNgheNghiep(event.getNewValue());
        });

        colQHChuHoNhap.setCellValueFactory(cellData -> cellData.getValue().quanHeChuHoProperty());
        colQHChuHoNhap.setCellFactory(TextFieldTableCell.forTableColumn());
        colQHChuHoNhap.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.setQuanHeChuHo(event.getNewValue());
        });

        colNgayDenNhap.setCellValueFactory(cellData -> cellData.getValue().ngayChuyenDenProperty());
        colNgayDenNhap.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter("MM/dd/yyyy")));
        colNgayDenNhap.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.setNgayChuyenDen(event.getNewValue());
        });

        colNoiTrcKhiDenNhap.setCellValueFactory(cellData -> cellData.getValue().noiTruocKhiChuyenDenProperty());
        colNoiTrcKhiDenNhap.setCellFactory(TextFieldTableCell.forTableColumn());
        colNoiTrcKhiDenNhap.setOnEditCommit(event -> {
            NhanKhauWithQH wrapper = event.getRowValue();
            wrapper.setNoiTruocKhiChuyenDen(event.getNewValue());
        });

        tvDSNhanKhauNhap.setItems(dsNhanKhauNhap);
    }

    private void setupTableTach() {
        tvNKHoCu.setEditable(true);
        tvNKHoMoi.setEditable(true);

        colNKCu.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        colQHCu.setCellValueFactory(new PropertyValueFactory<>("quanHeChuHo"));
        colQHCu.setCellFactory(TextFieldTableCell.forTableColumn());
        colQHCu.setOnEditCommit(event -> {
            ThuongTruWithTen tt = event.getRowValue();
            tt.setQuanHeChuHo(event.getNewValue());
        });
        tvNKHoCu.setItems(dsNKHoCu);

        colNKMoi.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        colQHMoi.setCellValueFactory(new PropertyValueFactory<>("quanHeChuHo"));
        colQHMoi.setCellFactory(TextFieldTableCell.forTableColumn());
        colQHMoi.setOnEditCommit(event -> {
            ThuongTruWithTen tt = event.getRowValue();
            tt.setQuanHeChuHo(event.getNewValue());
        });
        tvNKHoMoi.setItems(dsNKHoMoi);
    }

    @FXML
    private void dangKyHoMoi(ActionEvent event) {
        if (!validateHoMoi()) return;

        Date ngayDen = dpNgayDenMoi.getValue() != null ? Date.from(dpNgayDenMoi.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()) : new Date();

        // Validate all before insert
        boolean hasChuHo = false;
        boolean allValid = true;
        for (NhanKhauWithQH wrapper : dsNhanKhauMoi) {
            NhanKhau nk = wrapper.getNhanKhau();
            if (!validateNhanKhau(nk)) {
                allValid = false;
                break;
            }
            if (wrapper.getQuanHeChuHo().equals("Chủ hộ")) hasChuHo = true;
        }

        if (!allValid) return;

        if (!hasChuHo) {
            showAlert("Lỗi", "Phải có ít nhất một chủ hộ!");
            return;
        }

        try {
            HoKhau hoKhau = new HoKhau(txtSoHKMoi.getText(), txtDiaChiMoi.getText());
            hoKhauDAO.insert(hoKhau);

            for (NhanKhauWithQH wrapper : dsNhanKhauMoi) {
                NhanKhau nk = wrapper.getNhanKhau();
                nhanKhauDAO.insert(nk);

                ThuongTru tt = new ThuongTru();
                tt.setSoHoKhau(hoKhau.getSoHoKhau());
                tt.setCccd(nk.getCccd());
                tt.setQuanHeChuHo(wrapper.getQuanHeChuHo());
                tt.setNgayChuyenDen(ngayDen);
                tt.setNoiTruocKhiChuyenDen(txtNoiTrcKhiDenMoi.getText());
                thuongTruDAO.insert(tt);
            }

            showAlert("Thành công", "Đăng ký hộ mới thành công!");
            reloadCombobox();
            lamMoi(event);
        } catch (SQLException e) {
            showAlert("Lỗi", "Đăng ký hộ mới thất bại: " + e.getMessage());
        }
    }

    private boolean validateHoMoi() {
        if (txtSoHKMoi.getText().isEmpty() || txtDiaChiMoi.getText().isEmpty() || dpNgayDenMoi.getValue() == null || txtNoiTrcKhiDenMoi.getText().isEmpty()) {
            showAlert("Lỗi", "Vui lòng điền đầy đủ thông tin hộ mới!");
            return false;
        }
        if (dsNhanKhauMoi.isEmpty()) {
            showAlert("Lỗi", "Phải có ít nhất một nhân khẩu!");
            return false;
        }
        return true;
    }

    private boolean validateNhanKhau(NhanKhau nk) {
        if (nk.getCccd().isEmpty() || nk.getCccd().length() != 12 || !nk.getCccd().matches("\\d+")) {
            showAlert("Lỗi", "CCCD phải là 12 chữ số!");
            return false;
        }
        if (nk.getHoTen().isEmpty()) {
            showAlert("Lỗi", "Họ tên không được để trống!");
            return false;
        }
        if (!nk.getGioiTinh().equals("Nam") && !nk.getGioiTinh().equals("Nữ")) {
            showAlert("Lỗi", "Giới tính phải là Nam hoặc Nữ!");
            return false;
        }
        if (nk.getNgaySinh() == null || nk.getNgaySinh().after(new Date())) {
            showAlert("Lỗi", "Ngày sinh phải nhỏ hơn hoặc bằng ngày hiện tại!");
            return false;
        }
        if (nk.getQueQuan().isEmpty() || nk.getDanToc().isEmpty()) {
            showAlert("Lỗi", "Quê quán và dân tộc không được để trống!");
            return false;
        }
        // Thêm các validation khác nếu cần
        return true;
    }

    @FXML
    private void lamMoi(ActionEvent event) {
        txtSoHKMoi.clear();
        txtDiaChiMoi.clear();
        dpNgayDenMoi.setValue(null);
        txtNoiTrcKhiDenMoi.clear();
        dsNhanKhauMoi.clear();

        cbSoHKNhap.setValue(null);
        txtDiaChiNhap.clear();
        txtChuHoNhap.clear();
        dsNhanKhauNhap.clear();

        txtSoHoKhauTachMoi.clear();
        txtDiaChiTachMoi.clear();
        cbSoHoKhauTach.setValue(null);
        dsNKHoCu.clear();
        dsNKHoMoi.clear();
    }

    @FXML
    private void themNhanKhauMoi(ActionEvent event) {
        NhanKhau nk = new NhanKhau();
        NhanKhauWithQH wrapper = new NhanKhauWithQH(nk, "");
        dsNhanKhauMoi.add(wrapper);
        tvDSNhanKhauMoi.edit(dsNhanKhauMoi.size() - 1, colCCCDMoi);
    }

    @FXML
    private void nhapNKVaoHK(ActionEvent event) {
        String soHoKhau = cbSoHKNhap.getValue();
        if (soHoKhau == null || dsNhanKhauNhap.isEmpty()) {
            showAlert("Lỗi", "Vui lòng chọn hộ khẩu và thêm nhân khẩu!");
            return;
        }

        // Validate all before insert
        boolean allValid = true;
        for (NhanKhauWithQH wrapper : dsNhanKhauNhap) {
            NhanKhau nk = wrapper.getNhanKhau();
            if (!validateNhanKhau(nk)) {
                allValid = false;
                break;
            }
            // Additional validation for ngayChuyenDen and noiTruoc if needed
            if (wrapper.getNgayChuyenDen() == null) {
                showAlert("Lỗi", "Ngày chuyển đến không được để trống!");
                allValid = false;
                break;
            }
        }

        if (!allValid) return;

        try {
            for (NhanKhauWithQH wrapper : dsNhanKhauNhap) {
                NhanKhau nk = wrapper.getNhanKhau();
                nhanKhauDAO.insert(nk);

                ThuongTru tt = new ThuongTru();
                tt.setSoHoKhau(soHoKhau);
                tt.setCccd(nk.getCccd());
                tt.setQuanHeChuHo(wrapper.getQuanHeChuHo());
                tt.setNgayChuyenDen(wrapper.getNgayChuyenDen());
                tt.setNoiTruocKhiChuyenDen(wrapper.getNoiTruocKhiChuyenDen());
                thuongTruDAO.insert(tt);
            }

            showAlert("Thành công", "Nhập nhân khẩu vào hộ khẩu thành công!");
            lamMoi(event);
        } catch (SQLException e) {
            showAlert("Lỗi", "Nhập nhân khẩu thất bại: " + e.getMessage());
        }
    }

    @FXML
    private void themNhanKhauNhap(ActionEvent event) {
        NhanKhau nk = new NhanKhau();
        NhanKhauWithQH wrapper = new NhanKhauWithQH(nk, "");
        wrapper.setNgayChuyenDen(new Date());
        wrapper.setNoiTruocKhiChuyenDen("");
        dsNhanKhauNhap.add(wrapper);
        tvDSNhanKhauNhap.edit(dsNhanKhauNhap.size() - 1, colCCCDNhap);
    }

    @FXML
    private void tachHo(ActionEvent event) {
        if (txtSoHoKhauTachMoi.getText().isEmpty() || txtDiaChiTachMoi.getText().isEmpty()) {
            showAlert("Lỗi", "Vui lòng điền thông tin hộ mới!");
            return;
        }

        String soHoKhauCu = cbSoHoKhauTach.getValue();
        if (soHoKhauCu == null) return;

        // Validate hasChuHoMoi
        boolean hasChuHoMoi = false;
        for (ThuongTruWithTen tt : dsNKHoMoi) {
            if (tt.getQuanHeChuHo().equals("Chủ hộ")) hasChuHoMoi = true;
        }

        if (!hasChuHoMoi) {
            showAlert("Lỗi", "Hộ mới phải có chủ hộ!");
            return;
        }

        try {
            HoKhau hoMoi = new HoKhau(txtSoHoKhauTachMoi.getText(), txtDiaChiTachMoi.getText());
            hoKhauDAO.insert(hoMoi);

            for (ThuongTruWithTen tt : dsNKHoMoi) {
                thuongTruDAO.delete(soHoKhauCu, tt.getCccd());
                tt.setSoHoKhau(hoMoi.getSoHoKhau());
                thuongTruDAO.insert(tt);
            }

            showAlert("Thành công", "Tách hộ thành công!");
            reloadCombobox();
            lamMoi(event);
        } catch (SQLException e) {
            showAlert("Lỗi", "Tách hộ thất bại: " + e.getMessage());
        }
    }

    private void chuyenNKCuSangMoi() {
        ThuongTruWithTen selected = tvNKHoCu.getSelectionModel().getSelectedItem();
        if (selected != null) {
            dsNKHoCu.remove(selected);
            dsNKHoMoi.add(selected);
        }
    }

    private void chuyenNKMoiSangCu() {
        ThuongTruWithTen selected = tvNKHoMoi.getSelectionModel().getSelectedItem();
        if (selected != null) {
            dsNKHoMoi.remove(selected);
            dsNKHoCu.add(selected);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Wrapper class for NhanKhau with quanHeChuHo, ngayChuyenDen, noiTruocKhiChuyenDen
    public static class NhanKhauWithQH {
        private NhanKhau nhanKhau;
        private final SimpleStringProperty quanHeChuHo = new SimpleStringProperty();
        private final SimpleObjectProperty<Date> ngayChuyenDen = new SimpleObjectProperty<>();
        private final SimpleStringProperty noiTruocKhiChuyenDen = new SimpleStringProperty();

        public NhanKhauWithQH(NhanKhau nhanKhau, String quanHeChuHo) {
            this.nhanKhau = nhanKhau;
            this.quanHeChuHo.set(quanHeChuHo);
        }

        public NhanKhau getNhanKhau() {
            return nhanKhau;
        }

        public String getQuanHeChuHo() {
            return quanHeChuHo.get();
        }

        public void setQuanHeChuHo(String quanHeChuHo) {
            this.quanHeChuHo.set(quanHeChuHo);
        }

        public SimpleStringProperty quanHeChuHoProperty() {
            return quanHeChuHo;
        }

        public Date getNgayChuyenDen() {
            return ngayChuyenDen.get();
        }

        public void setNgayChuyenDen(Date ngayChuyenDen) {
            this.ngayChuyenDen.set(ngayChuyenDen);
        }

        public ObjectProperty<Date> ngayChuyenDenProperty() {
            return ngayChuyenDen;
        }

        public String getNoiTruocKhiChuyenDen() {
            return noiTruocKhiChuyenDen.get();
        }

        public void setNoiTruocKhiChuyenDen(String noiTruocKhiChuyenDen) {
            this.noiTruocKhiChuyenDen.set(noiTruocKhiChuyenDen);
        }

        public SimpleStringProperty noiTruocKhiChuyenDenProperty() {
            return noiTruocKhiChuyenDen;
        }
    }
}