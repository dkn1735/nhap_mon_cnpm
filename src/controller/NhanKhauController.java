package controller;

import dao.NhanKhauDAO;
import dao.ThuongTruDAO;
import dao.TamTruDAO;
import dao.TamVangDAO;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.NhanKhauThuongTru;
import model.NhanKhauTamTru;
import model.NhanKhauTamVang;

public class NhanKhauController implements Initializable {

    @FXML
    private BorderPane paneDSThuongTru;
    @FXML
    private TextField textTimKiemThuongTru;
    @FXML
    private TableView<NhanKhauThuongTru> tvDSThuongTru;
    @FXML
    private TableColumn<NhanKhauThuongTru, String> colCCCDThuongTru;
    @FXML
    private TableColumn<NhanKhauThuongTru, String> colHoTenThuongTru;
    @FXML
    private TableColumn<NhanKhauThuongTru, String> colGioiTinhThuongTru;
    @FXML
    private TableColumn<NhanKhauThuongTru, String> colBiDanhThuongTru;
    @FXML
    private TableColumn<NhanKhauThuongTru, Date> colNgaySinhThuongTru;
    @FXML
    private TableColumn<NhanKhauThuongTru, String> colQueQuanThuongTru;
    @FXML
    private TableColumn<NhanKhauThuongTru, String> colDanTocThuongTru;
    @FXML
    private TableColumn<NhanKhauThuongTru, String> colTonGiaoThuongTru;
    @FXML
    private TableColumn<NhanKhauThuongTru, String> colNgheNghiepThuongTru;
    @FXML
    private BorderPane paneChiTietThuongTru;
    @FXML
    private TextField textCCCDThuongTru;
    @FXML
    private TextField textHoTenThuongTru;
    @FXML
    private TextField textGioiTinhThuongTru;
    @FXML
    private TextField textBiDanhThuongTru;
    @FXML
    private TextField textQueQuanThuongTru;
    @FXML
    private TextField textDanTocThuongTru;
    @FXML
    private TextField textTonGiaoThuongTru;
    @FXML
    private TextField textNgheNghiepThuongTru;
    @FXML
    private DatePicker dpNgaySinhThuongTru;
    @FXML
    private TextField textSoHoKhau;
    @FXML
    private TextField textNoiTrcKhiDen;
    @FXML
    private TextField textQHChuHo;
    @FXML
    private TextField textNoiChuyenDi;
    @FXML
    private DatePicker dpNgayChuyenDen;
    @FXML
    private DatePicker dpNgayChuyenDi;
    @FXML
    private BorderPane paneDSTamTru;
    @FXML
    private TextField textTimKiemTamTru;
    @FXML
    private TableView<NhanKhauTamTru> tvDSTamTru;
    @FXML
    private TableColumn<NhanKhauTamTru, String> colCCCDTamTru;
    @FXML
    private TableColumn<NhanKhauTamTru, String> colHoTenTamTru;
    @FXML
    private TableColumn<NhanKhauTamTru, String> colGioiTinhTamTru;
    @FXML
    private TableColumn<NhanKhauTamTru, String> colBiDanhTamTru;
    @FXML
    private TableColumn<NhanKhauTamTru, Date> colNgaySinhTamTru;
    @FXML
    private TableColumn<NhanKhauTamTru, String> colQueQuanTamTru;
    @FXML
    private TableColumn<NhanKhauTamTru, String> colDanTocTamtru;
    @FXML
    private TableColumn<NhanKhauTamTru, String> colTonGiaoTamTru;
    @FXML
    private TableColumn<NhanKhauTamTru, String> colNgheNghiepTamTru;
    @FXML
    private BorderPane paneChiTietTamTru;
    @FXML
    private TextField textCCCDTamTru;
    @FXML
    private TextField textHoTenTamTru;
    @FXML
    private TextField textGioiTinhTamTru;
    @FXML
    private TextField textBiDanhTamTru;
    @FXML
    private TextField textQueQuanTamTru;
    @FXML
    private TextField textDanTocTamTru;
    @FXML
    private TextField textTonGiaoTamTru;
    @FXML
    private TextField textNgheNghiepTamTru;
    @FXML
    private DatePicker dpNgaySinhTamTru;
    @FXML
    private TextField textMaTamTru;
    @FXML
    private DatePicker dpNgayBatDauTamTru;
    @FXML
    private DatePicker dpNgayKetThucTamTru;
    @FXML
    private TextField textNoiTamTru;
    @FXML
    private TextField textLyDoTamTru;
    @FXML
    private BorderPane paneDSTamVang;
    @FXML
    private TextField textTimKiemTamVang;
    @FXML
    private TableView<NhanKhauTamVang> tvDSTamVang;
    @FXML
    private TableColumn<NhanKhauTamVang, String> colCCCDTamVang;
    @FXML
    private TableColumn<NhanKhauTamVang, String> colHoTenTamVang;
    @FXML
    private TableColumn<NhanKhauTamVang, String> colGioiTinhTamVang;
    @FXML
    private TableColumn<NhanKhauTamVang, String> colBiDanhTamVang;
    @FXML
    private TableColumn<NhanKhauTamVang, Date> colNgaySinhTamVang;
    @FXML
    private TableColumn<NhanKhauTamVang, String> colQueQuanTamVang;
    @FXML
    private TableColumn<NhanKhauTamVang, String> colDanTocTamVang;
    @FXML
    private TableColumn<NhanKhauTamVang, String> colTonGiaoTamVang;
    @FXML
    private TableColumn<NhanKhauTamVang, String> colNgheNghiepTamVang;
    @FXML
    private BorderPane paneChiTietTamVang;
    @FXML
    private TextField textCCCDTamVang;
    @FXML
    private TextField textHoTenTamVang;
    @FXML
    private TextField textGioiTinhTamVang;
    @FXML
    private TextField textBiDanhTamVang;
    @FXML
    private TextField textQueQuanTamVang;
    @FXML
    private TextField textDanTocTamVang;
    @FXML
    private TextField textTonGiaoTamVang;
    @FXML
    private TextField textNgheNghiepTamVang;
    @FXML
    private DatePicker dpNgaySinhTamVang;
    @FXML
    private TextField textMaTamVang;
    @FXML
    private DatePicker dpNgayBatDauTamVang;
    @FXML
    private DatePicker dpNgayKetThucTamVang;
    @FXML
    private TextField textNoiTamVang;
    @FXML
    private TextField textLyDoTamVang;

    private ObservableList<NhanKhauThuongTru> dsThuongTru = FXCollections.observableArrayList();
    private ObservableList<NhanKhauTamTru> dsTamTru = FXCollections.observableArrayList();
    private ObservableList<NhanKhauTamVang> dsTamVang = FXCollections.observableArrayList();

    private ThuongTruDAO thuongTruDAO = new ThuongTruDAO();
    private TamTruDAO tamTruDAO = new TamTruDAO();
    private TamVangDAO tamVangDAO = new TamVangDAO();
    private NhanKhauDAO nhanKhauDAO = new NhanKhauDAO();

    private NhanKhauThuongTru selectedThuongTru;
    private NhanKhauThuongTru thuongTruCu;
    private NhanKhauTamTru selectedTamTru;
    private NhanKhauTamVang selectedTamVang;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTableThuongTru();
        setupTableTamTru();
        setupTableTamVang();
        loadDSThuongTru();
        loadDSTamTru();
        loadDSTamVang();

        paneChiTietThuongTru.setVisible(false);
        paneChiTietTamTru.setVisible(false);
        paneChiTietTamVang.setVisible(false);

        tvDSThuongTru.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                xemChiTietThuongTru(event);
            }
        });

        tvDSTamTru.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                xemChiTietTamTru(event);
            }
        });

        tvDSTamVang.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                xemChiTietTamVang(event);
            }
        });
    }    

    private void setupTableThuongTru() {
        colCCCDThuongTru.setCellValueFactory(new PropertyValueFactory<>("cccd"));
        colHoTenThuongTru.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        colGioiTinhThuongTru.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        colBiDanhThuongTru.setCellValueFactory(new PropertyValueFactory<>("biDanh"));
        colNgaySinhThuongTru.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        colQueQuanThuongTru.setCellValueFactory(new PropertyValueFactory<>("queQuan"));
        colDanTocThuongTru.setCellValueFactory(new PropertyValueFactory<>("danToc"));
        colTonGiaoThuongTru.setCellValueFactory(new PropertyValueFactory<>("tonGiao"));
        colNgheNghiepThuongTru.setCellValueFactory(new PropertyValueFactory<>("ngheNghiep"));
        tvDSThuongTru.setItems(dsThuongTru);
    }

    private void setupTableTamTru() {
        colCCCDTamTru.setCellValueFactory(new PropertyValueFactory<>("cccd"));
        colHoTenTamTru.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        colGioiTinhTamTru.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        colBiDanhTamTru.setCellValueFactory(new PropertyValueFactory<>("biDanh"));
        colNgaySinhTamTru.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        colQueQuanTamTru.setCellValueFactory(new PropertyValueFactory<>("queQuan"));
        colDanTocTamtru.setCellValueFactory(new PropertyValueFactory<>("danToc"));
        colTonGiaoTamTru.setCellValueFactory(new PropertyValueFactory<>("tonGiao"));
        colNgheNghiepTamTru.setCellValueFactory(new PropertyValueFactory<>("ngheNghiep"));
        tvDSTamTru.setItems(dsTamTru);
    }

    private void setupTableTamVang() {
        colCCCDTamVang.setCellValueFactory(new PropertyValueFactory<>("cccd"));
        colHoTenTamVang.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        colGioiTinhTamVang.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        colBiDanhTamVang.setCellValueFactory(new PropertyValueFactory<>("biDanh"));
        colNgaySinhTamVang.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        colQueQuanTamVang.setCellValueFactory(new PropertyValueFactory<>("queQuan"));
        colDanTocTamVang.setCellValueFactory(new PropertyValueFactory<>("danToc"));
        colTonGiaoTamVang.setCellValueFactory(new PropertyValueFactory<>("tonGiao"));
        colNgheNghiepTamVang.setCellValueFactory(new PropertyValueFactory<>("ngheNghiep"));
        tvDSTamVang.setItems(dsTamVang);
    }

    private void loadDSThuongTru() {
        try {
            dsThuongTru.setAll(thuongTruDAO.getAll());
        } catch (SQLException e) {
            showAlert("Lỗi", "Không thể tải danh sách thường trú: " + e.getMessage());
        }
    }

    private void loadDSTamTru() {
        try {
            dsTamTru.setAll(tamTruDAO.getAll());
        } catch (SQLException e) {
            showAlert("Lỗi", "Không thể tải danh sách tạm trú: " + e.getMessage());
        }
    }

    private void loadDSTamVang() {
        try {
            dsTamVang.setAll(tamVangDAO.getAll());
        } catch (SQLException e) {
            showAlert("Lỗi", "Không thể tải danh sách tạm vắng: " + e.getMessage());
        }
    }

    @FXML
    private void timKiemThuongTru(ActionEvent event) {
        String keyword = textTimKiemThuongTru.getText().trim();
        if (keyword.isEmpty()) {
            loadDSThuongTru();
            return;
        }
        try {
            dsThuongTru.setAll(thuongTruDAO.search(keyword));
        } catch (SQLException e) {
            showAlert("Lỗi", "Tìm kiếm thất bại: " + e.getMessage());
        }
    }

    @FXML
    private void xemChiTietThuongTru(MouseEvent event) {
        selectedThuongTru = tvDSThuongTru.getSelectionModel().getSelectedItem();
        thuongTruCu = tvDSThuongTru.getSelectionModel().getSelectedItem();;
        if (selectedThuongTru != null) {
            textCCCDThuongTru.setText(selectedThuongTru.getCccd());
            textHoTenThuongTru.setText(selectedThuongTru.getHoTen());
            textGioiTinhThuongTru.setText(selectedThuongTru.getGioiTinh());
            textBiDanhThuongTru.setText(selectedThuongTru.getBiDanh());
            if (selectedThuongTru.getNgaySinh() != null) {
                dpNgaySinhThuongTru.setValue(((java.sql.Date) selectedThuongTru.getNgaySinh()).toLocalDate());
            }
            textQueQuanThuongTru.setText(selectedThuongTru.getQueQuan());
            textDanTocThuongTru.setText(selectedThuongTru.getDanToc());
            textTonGiaoThuongTru.setText(selectedThuongTru.getTonGiao());
            textNgheNghiepThuongTru.setText(selectedThuongTru.getNgheNghiep());
            textSoHoKhau.setText(selectedThuongTru.getSoHoKhau());
            textQHChuHo.setText(selectedThuongTru.getQuanHeChuHo());
            if (selectedThuongTru.getNgayChuyenDen() != null) {
                dpNgayChuyenDen.setValue(((java.sql.Date) selectedThuongTru.getNgayChuyenDen()).toLocalDate());
            }
            textNoiTrcKhiDen.setText(selectedThuongTru.getNoiTruocKhiChuyenDen());
            if (selectedThuongTru.getNgayChuyenDi() != null) {
                dpNgayChuyenDi.setValue(((java.sql.Date) selectedThuongTru.getNgayChuyenDi()).toLocalDate());
            }
            textNoiChuyenDi.setText(selectedThuongTru.getNoiChuyenDi());

            paneDSThuongTru.setVisible(false);
            paneChiTietThuongTru.setVisible(true);
        }
    }

    @FXML
    private void quayLaiDSThuongTru(ActionEvent event) {
        paneChiTietThuongTru.setVisible(false);
        paneDSThuongTru.setVisible(true);
        loadDSThuongTru();
    }

    @FXML
    private void chinhSuaThuongTru(ActionEvent event) throws SQLException {
        if (!validateNhanKhau(selectedThuongTru.getCccd(), textHoTenThuongTru.getText(), textGioiTinhThuongTru.getText(), getDateFromPicker(dpNgaySinhThuongTru), textQueQuanThuongTru.getText(), textDanTocThuongTru.getText())) return;

        selectedThuongTru.setHoTen(textHoTenThuongTru.getText());
        selectedThuongTru.setGioiTinh(textGioiTinhThuongTru.getText());
        selectedThuongTru.setBiDanh(textBiDanhThuongTru.getText());
        selectedThuongTru.setNgaySinh(getDateFromPicker(dpNgaySinhThuongTru));
        selectedThuongTru.setQueQuan(textQueQuanThuongTru.getText());
        selectedThuongTru.setDanToc(textDanTocThuongTru.getText());
        selectedThuongTru.setTonGiao(textTonGiaoThuongTru.getText());
        selectedThuongTru.setNgheNghiep(textNgheNghiepThuongTru.getText());
        selectedThuongTru.setSoHoKhau(textSoHoKhau.getText());
        selectedThuongTru.setQuanHeChuHo(textQHChuHo.getText());
        selectedThuongTru.setNgayChuyenDen(getDateFromPicker(dpNgayChuyenDen));
        selectedThuongTru.setNoiTruocKhiChuyenDen(textNoiTrcKhiDen.getText());
        selectedThuongTru.setNgayChuyenDi(getDateFromPicker(dpNgayChuyenDi));
        selectedThuongTru.setNoiChuyenDi(textNoiChuyenDi.getText());

        if (!validateThuongTru(textQHChuHo.getText(), getDateFromPicker(dpNgayChuyenDen), getDateFromPicker(dpNgayChuyenDi), textNoiTrcKhiDen.getText(), textSoHoKhau.getText())) return;
        
        try {
            thuongTruDAO.update(selectedThuongTru);
            showAlert("Thành công", "Chỉnh sửa thường trú thành công!");
        } catch (SQLException e) {
            showAlert("Lỗi", "Chỉnh sửa thất bại: " + e.getMessage());
        }
    }

    @FXML
    private void xoaThuongTru(ActionEvent event) {
        try {
            tamTruDAO.deleteByCCCD(selectedThuongTru.getCccd());
            thuongTruDAO.delete(selectedThuongTru.getCccd());
            nhanKhauDAO.delete(selectedThuongTru.getCccd());
            showAlert("Thành công", "Xóa thường trú thành công!");
            quayLaiDSThuongTru(event);
            loadDSTamVang();
        } catch (SQLException e) {
            showAlert("Lỗi", "Xóa thất bại: " + e.getMessage());
        }
    }

    @FXML
    private void timKiemTamTru(ActionEvent event) {
        String keyword = textTimKiemTamTru.getText().trim();
        if (keyword.isEmpty()) {
            loadDSTamTru();
            return;
        }
        try {
            dsTamTru.setAll(tamTruDAO.search(keyword));
        } catch (SQLException e) {
            showAlert("Lỗi", "Tìm kiếm thất bại: " + e.getMessage());
        }
    }

    @FXML
    private void xemChiTietTamTru(MouseEvent event) {
        selectedTamTru = tvDSTamTru.getSelectionModel().getSelectedItem();
        if (selectedTamTru != null) {
            textCCCDTamTru.setText(selectedTamTru.getCccd());
            textHoTenTamTru.setText(selectedTamTru.getHoTen());
            textGioiTinhTamTru.setText(selectedTamTru.getGioiTinh());
            textBiDanhTamTru.setText(selectedTamTru.getBiDanh());
            if (selectedTamTru.getNgaySinh() != null) {
                dpNgaySinhTamTru.setValue(((java.sql.Date) selectedTamTru.getNgaySinh()).toLocalDate());
            }
            textQueQuanTamTru.setText(selectedTamTru.getQueQuan());
            textDanTocTamTru.setText(selectedTamTru.getDanToc());
            textTonGiaoTamTru.setText(selectedTamTru.getTonGiao());
            textNgheNghiepTamTru.setText(selectedTamTru.getNgheNghiep());
            textMaTamTru.setText(selectedTamTru.getMaTamTru());
            if (selectedTamTru.getNgayBatDau() != null) {
                dpNgayBatDauTamTru.setValue(((java.sql.Date) selectedTamTru.getNgayBatDau()).toLocalDate());
            }
            if (selectedTamTru.getNgayKetThuc() != null) {
                dpNgayKetThucTamTru.setValue(((java.sql.Date) selectedTamTru.getNgayKetThuc()).toLocalDate());
            }
            textNoiTamTru.setText(selectedTamTru.getNoiTamTru());
            textLyDoTamTru.setText(selectedTamTru.getLyDo());

            paneDSTamTru.setVisible(false);
            paneChiTietTamTru.setVisible(true);
        }
    }

    @FXML
    private void quayLaiDSTamTru(ActionEvent event) {
        paneChiTietTamTru.setVisible(false);
        paneDSTamTru.setVisible(true);
        loadDSTamTru();
    }

    @FXML
    private void chinhSuaTamTru(ActionEvent event) {
        if (!validateNhanKhau(selectedTamTru.getCccd(), textHoTenTamTru.getText(), textGioiTinhTamTru.getText(), getDateFromPicker(dpNgaySinhTamTru), textQueQuanTamTru.getText(), textDanTocTamTru.getText())) return;

        selectedTamTru.setHoTen(textHoTenTamTru.getText());
        selectedTamTru.setGioiTinh(textGioiTinhTamTru.getText());
        selectedTamTru.setBiDanh(textBiDanhTamTru.getText());
        selectedTamTru.setNgaySinh(getDateFromPicker(dpNgaySinhTamTru));
        selectedTamTru.setQueQuan(textQueQuanTamTru.getText());
        selectedTamTru.setDanToc(textDanTocTamTru.getText());
        selectedTamTru.setTonGiao(textTonGiaoTamTru.getText());
        selectedTamTru.setNgheNghiep(textNgheNghiepTamTru.getText());
        selectedTamTru.setNoiTamTru(textNoiTamTru.getText());
        selectedTamTru.setLyDo(textLyDoTamTru.getText());
        selectedTamTru.setNgayBatDau(getDateFromPicker(dpNgayBatDauTamTru));
        selectedTamTru.setNgayKetThuc(getDateFromPicker(dpNgayKetThucTamTru));

        if (!validateTamTru(getDateFromPicker(dpNgayBatDauTamTru), getDateFromPicker(dpNgayKetThucTamTru), textNoiTamTru.getText(), textLyDoTamTru.getText())) return;

        try {
            tamTruDAO.update(selectedTamTru);
            showAlert("Thành công", "Chỉnh sửa tạm trú thành công!");
        } catch (SQLException e) {
            showAlert("Lỗi", "Chỉnh sửa thất bại: " + e.getMessage());
        }
    }

    @FXML
    private void ketThucTamTru(ActionEvent event) {
        try {
            tamTruDAO.delete(selectedTamTru.getMaTamTru());
            nhanKhauDAO.delete(selectedTamTru.getCccd());
            showAlert("Thành công", "Kết thúc tạm trú thành công!");
            quayLaiDSTamTru(event);
        } catch (SQLException e) {
            showAlert("Lỗi", "Kết thúc thất bại: " + e.getMessage());
        }
    }

    @FXML
    private void timKiemTamVang(ActionEvent event) {
        String keyword = textTimKiemTamVang.getText().trim();
        if (keyword.isEmpty()) {
            loadDSTamVang();
            return;
        }
        try {
            dsTamVang.setAll(tamVangDAO.search(keyword));
        } catch (SQLException e) {
            showAlert("Lỗi", "Tìm kiếm thất bại: " + e.getMessage());
        }
    }

    @FXML
    private void xemChiTietTamVang(MouseEvent event) {
        selectedTamVang = tvDSTamVang.getSelectionModel().getSelectedItem();
        if (selectedTamVang != null) {
            textCCCDTamVang.setText(selectedTamVang.getCccd());
            textHoTenTamVang.setText(selectedTamVang.getHoTen());
            textGioiTinhTamVang.setText(selectedTamVang.getGioiTinh());
            textBiDanhTamVang.setText(selectedTamVang.getBiDanh());
            if (selectedTamVang.getNgaySinh() != null) {
                dpNgaySinhTamVang.setValue(((java.sql.Date) selectedTamVang.getNgaySinh()).toLocalDate());
            }
            textQueQuanTamVang.setText(selectedTamVang.getQueQuan());
            textDanTocTamVang.setText(selectedTamVang.getDanToc());
            textTonGiaoTamVang.setText(selectedTamVang.getTonGiao());
            textNgheNghiepTamVang.setText(selectedTamVang.getNgheNghiep());
            textMaTamVang.setText(selectedTamVang.getMaTamVang());
            if (selectedTamVang.getNgayBatDau() != null) {
                dpNgayBatDauTamVang.setValue(((java.sql.Date) selectedTamVang.getNgayBatDau()).toLocalDate());
            }
            if (selectedTamVang.getNgayKetThuc() != null) {
                dpNgayKetThucTamVang.setValue(((java.sql.Date) selectedTamVang.getNgayKetThuc()).toLocalDate());
            }
            textNoiTamVang.setText(selectedTamVang.getNoiTamVang());
            textLyDoTamVang.setText(selectedTamVang.getLyDo());

            paneDSTamVang.setVisible(false);
            paneChiTietTamVang.setVisible(true);
        }
    }

    @FXML
    private void quayLaiDSTamVang(ActionEvent event) {
        paneChiTietTamVang.setVisible(false);
        paneDSTamVang.setVisible(true);
        loadDSTamVang();
    }

    @FXML
    private void chinhSuaTamVang(ActionEvent event) {
        if (!validateNhanKhau(selectedTamVang.getCccd(), textHoTenTamVang.getText(), textGioiTinhTamVang.getText(), getDateFromPicker(dpNgaySinhTamVang), textQueQuanTamVang.getText(), textDanTocTamVang.getText())) return;

        selectedTamVang.setHoTen(textHoTenTamVang.getText());
        selectedTamVang.setGioiTinh(textGioiTinhTamVang.getText());
        selectedTamVang.setBiDanh(textBiDanhTamVang.getText());
        selectedTamVang.setNgaySinh(getDateFromPicker(dpNgaySinhTamVang));
        selectedTamVang.setQueQuan(textQueQuanTamVang.getText());
        selectedTamVang.setDanToc(textDanTocTamVang.getText());
        selectedTamVang.setTonGiao(textTonGiaoTamVang.getText());
        selectedTamVang.setNgheNghiep(textNgheNghiepTamVang.getText());
        selectedTamVang.setNoiTamVang(textNoiTamVang.getText());
        selectedTamVang.setLyDo(textLyDoTamVang.getText());
        selectedTamVang.setNgayBatDau(getDateFromPicker(dpNgayBatDauTamVang));
        selectedTamVang.setNgayKetThuc(getDateFromPicker(dpNgayKetThucTamVang));

        if (!validateTamVang(getDateFromPicker(dpNgayBatDauTamVang), getDateFromPicker(dpNgayKetThucTamVang), textNoiTamVang.getText(), textLyDoTamVang.getText())) return;

        try {
            tamVangDAO.update(selectedTamVang);
            showAlert("Thành công", "Chỉnh sửa tạm vắng thành công!");
        } catch (SQLException e) {
            showAlert("Lỗi", "Chỉnh sửa thất bại: " + e.getMessage());
        }
    }

    @FXML
    private void ketThucTamVang(ActionEvent event) {
        try {
            tamVangDAO.delete(selectedTamVang.getMaTamVang());
            showAlert("Thành công", "Kết thúc tạm vắng thành công!");
            quayLaiDSTamVang(event);
        } catch (SQLException e) {
            showAlert("Lỗi", "Kết thúc thất bại: " + e.getMessage());
        }
    }

    private Date getDateFromPicker(DatePicker dp) {
        if (dp.getValue() != null) {
            return Date.from(dp.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        return null;
    }

    private boolean validateNhanKhau(String cccd, String hoTen, String gioiTinh, Date ngaySinh, String queQuan, String danToc) {
        if (cccd.isEmpty() || cccd.length() != 12 || !cccd.matches("\\d+")) {
            showAlert("Lỗi", "CCCD phải là 12 chữ số!");
            return false;
        }
        if (hoTen.isEmpty()) {
            showAlert("Lỗi", "Họ tên không được để trống!");
            return false;
        }
        if (!gioiTinh.equals("Nam") && !gioiTinh.equals("Nữ")) {
            showAlert("Lỗi", "Giới tính phải là Nam hoặc Nữ!");
            return false;
        }
        if (ngaySinh == null || ngaySinh.after(new Date())) {
            showAlert("Lỗi", "Ngày sinh phải nhỏ hơn hoặc bằng ngày hiện tại!");
            return false;
        }
        if (queQuan.isEmpty() || danToc.isEmpty()) {
            showAlert("Lỗi", "Quê quán và dân tộc không được để trống!");
            return false;
        }
        return true;
    }

    private boolean validateThuongTru(String quanHeChuHo, Date ngayChuyenDen, Date ngayChuyenDi, String noiTruoc, String soHoKhau) throws SQLException {
        if (quanHeChuHo.isEmpty()) {
            showAlert("Lỗi", "Quan hệ chủ hộ không được để trống!");
            return false;
        }
        if (ngayChuyenDen == null || ngayChuyenDen.after(new Date())) {
            showAlert("Lỗi", "Ngày chuyển đến phải nhỏ hơn hoặc bằng ngày hiện tại!");
            return false;
        }
        if (noiTruoc.isEmpty()) {
            showAlert("Lỗi", "Nơi trước khi chuyển đến không được để trống!");
            return false;
        }
        if (ngayChuyenDi != null && (ngayChuyenDi.before(ngayChuyenDen) || ngayChuyenDi.after(new Date()))) {
            showAlert("Lỗi", "Ngày chuyển đi phải >= chuyển đến và <= ngày hiện tại!");
            return false;
        }
        if (
            !quanHeChuHo.equals(thuongTruCu.getQuanHeChuHo())  // có thay đổi quan hệ
            && quanHeChuHo.equals("Chủ hộ")                    // đang set thành Chủ hộ
            && ThuongTruDAO.checkExistChuHo(soHoKhau)          // đã có chủ hộ
        ) {
            showAlert("Lỗi", "Hộ khẩu đã tồn tại chủ hộ!");
            return false;
        }

        return true;
    }

    private boolean validateTamTru(Date ngayBatDau, Date ngayKetThuc, String noiTamTru, String lyDo) {
        if (ngayBatDau == null || ngayBatDau.after(new Date())) {
            showAlert("Lỗi", "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày hiện tại!");
            return false;
        }
        if (ngayKetThuc == null || ngayKetThuc.before(ngayBatDau)) {
            showAlert("Lỗi", "Ngày kết thúc phải lớn hơn ngày bắt đầu!");
            return false;
        }
        if (noiTamTru.isEmpty() || lyDo.isEmpty()) {
            showAlert("Lỗi", "Nơi tạm trú và lý do không được để trống!");
            return false;
        }
        return true;
    }

    private boolean validateTamVang(Date ngayBatDau, Date ngayKetThuc, String noiTamVang, String lyDo) {
        if (ngayBatDau == null || ngayBatDau.after(new Date())) {
            showAlert("Lỗi", "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày hiện tại!");
            return false;
        }
        if (ngayKetThuc == null || ngayKetThuc.before(ngayBatDau)) {
            showAlert("Lỗi", "Ngày kết thúc phải lớn hơn ngày bắt đầu!");
            return false;
        }
        if (noiTamVang.isEmpty() || lyDo.isEmpty()) {
            showAlert("Lỗi", "Nơi tạm vắng và lý do không được để trống!");
            return false;
        }
        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}