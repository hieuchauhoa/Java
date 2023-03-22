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
import DAL.LopHocService;
import DAL.LopHocServiceImpl;
import DTO.LopHoc;
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

public class LopHocController {
    
    private JButton btnSubmit;
    private JTextField jtfMaLopHoc;
    private JTextField jtfMaKhoaHoc;
    private JTextField jtfMaHocVien;
    private JDateChooser jdcNgayDangKy;
    private JCheckBox jcbTinhTrang;
    private JLabel jlbMsg;
    


    private LopHoc lopHoc = null;

    private LopHocService lopHocService = null;

    public LopHocController(JButton btnSubmit, JTextField jtfMaLopHoc, JTextField jtfMaKhoaHoc, JTextField jtfMaHocVien, JDateChooser jdcNgayDangKy, JCheckBox jcbTinhTrang, JLabel jlbMsg) {
        this.btnSubmit = btnSubmit;
        this.jtfMaLopHoc = jtfMaLopHoc;
        this.jtfMaKhoaHoc = jtfMaKhoaHoc;
        this.jtfMaHocVien = jtfMaHocVien;
        this.jdcNgayDangKy = jdcNgayDangKy;
        this.jcbTinhTrang = jcbTinhTrang;
        this.jlbMsg = jlbMsg;
        
        this.lopHocService = new LopHocServiceImpl();
    }

    

    public void setView(LopHoc lopHoc) {
        this.lopHoc = lopHoc;
        // set data
        
        jtfMaLopHoc.setText("#" + lopHoc.getMa_lop_hoc());
        
        jtfMaKhoaHoc.setText("#" + lopHoc.getMaKhoaHoc());
        jtfMaHocVien.setText("#" + lopHoc.getMaHocVien());
   
        jdcNgayDangKy.setDate(lopHoc.getNgay_dang_ky());
        jcbTinhTrang.setSelected(lopHoc.isTinh_trang());
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
                        
                        
                        
                        lopHoc.setNgay_dang_ky(covertDateToDateSql(jdcNgayDangKy.getDate()));
 
                        lopHoc.setTinh_trang(jcbTinhTrang.isSelected());
                        if (showDialog()) {
                            int lastId =lopHocService.createOrUpdate(lopHoc);
                            if (lastId != 0) {
                                lopHoc.setMa_lop_hoc(lastId);
                                jtfMaKhoaHoc.setText("#" + lopHoc.getMa_lop_hoc());
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
        return jtfMaLopHoc.getText() != null && !jtfMaLopHoc.getText().equalsIgnoreCase("");
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