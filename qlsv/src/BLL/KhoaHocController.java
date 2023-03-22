/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

/**
 *
 * @author LENOVO
 */



import DAL.KhoaHocService;
import DAL.KhoaHocServiceImpl;
import DTO.KhoaHoc;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

public class KhoaHocController {
    
    private JButton btnSubmit;
    private JTextField jtfMaKhoaHoc;
    private JTextField jtfTenKhoaHoc;
    private JTextArea jtaMoTa;
    private JDateChooser jdcNgayBatDau;
    private JDateChooser jdcNgayKetThuc;
    private JCheckBox jcbTinhTrang;
    private JLabel jlbMsg;

    private KhoaHoc khoaHoc = null;

    private KhoaHocService khoaHocService = null;

    public KhoaHocController(JButton btnSubmit, JTextField jtfMaKhoaHoc, JTextField jtfTenKhoaHoc, JTextArea jtaMoTa, JDateChooser jdcNgayBatDau, JDateChooser jdcNgayKetThuc, JCheckBox jcbTinhTrang, JLabel jlbMsg) {
        this.btnSubmit = btnSubmit;
        this.jtfMaKhoaHoc = jtfMaKhoaHoc;
        this.jtfTenKhoaHoc = jtfTenKhoaHoc;
        this.jtaMoTa = jtaMoTa;
        this.jdcNgayBatDau = jdcNgayBatDau;
        this.jdcNgayKetThuc = jdcNgayKetThuc;
        this.jcbTinhTrang = jcbTinhTrang;
        this.jlbMsg = jlbMsg;
    
    
   

        this.khoaHocService = new KhoaHocServiceImpl();
    }

    public void setView(KhoaHoc khoaHoc) {
        this.khoaHoc = khoaHoc;
        // set data
        jtfMaKhoaHoc.setText("#" + khoaHoc.getMa_khoa_hoc());
        jtfTenKhoaHoc.setText(khoaHoc.getTen_khoa_hoc());
        jtaMoTa.setText(khoaHoc.getMo_ta());
        jdcNgayBatDau.setDate(khoaHoc.getNgay_bat_dau());
        jdcNgayKetThuc.setDate(khoaHoc.getNgay_ket_thuc());
        jcbTinhTrang.setSelected(khoaHoc.isTinh_trang());
        // set event
        setEvent();
    }

    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (!checkNotNull()) {
                        jlbMsg.setText("Vui lòng nhập dữ liệu bắt buộc!");
                    } else {
                        khoaHoc.setTen_khoa_hoc(jtfTenKhoaHoc.getText().trim());
                        khoaHoc.setMo_ta(jtaMoTa.getText());
                        
                        khoaHoc.setNgay_bat_dau(covertDateToDateSql(jdcNgayBatDau.getDate()));
                        khoaHoc.setNgay_ket_thuc(covertDateToDateSql(jdcNgayKetThuc.getDate()));
                        khoaHoc.setTinh_trang(jcbTinhTrang.isSelected());
                        if (showDialog()) {
                            int lastId =khoaHocService.createOrUpdate(khoaHoc);
                            if (lastId != 0) {
                                khoaHoc.setMa_khoa_hoc(lastId);
                                jtfMaKhoaHoc.setText("#" + khoaHoc.getMa_khoa_hoc());
                                jlbMsg.setText("Xử lý cập nhật dữ liệu thành công!");
                            } else {
                                jlbMsg.setText("Có lỗi xảy ra, vui lòng thử lại!");
                            }
                        }
                    }
                } catch (Exception ex) {
                    jlbMsg.setText(ex.toString());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSubmit.setBackground(new Color(0,0,255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSubmit.setBackground(new Color(102,102,255));
            }
        });
    }

    private boolean checkNotNull() {
        return jtfTenKhoaHoc.getText() != null && !jtfTenKhoaHoc.getText().equalsIgnoreCase("");
    }

    private boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn muốn cập nhật dữ liệu hay không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }
    
    public java.sql.Date covertDateToDateSql(Date d) {
        return new java.sql.Date(d.getTime());
    }
    
}