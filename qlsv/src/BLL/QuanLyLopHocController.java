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
import GUI.KhoaHocJFrame;
import GUI.LopHocJFrame;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author LENOVO
 */
public class QuanLyLopHocController {

    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;

    private ClassTableModel_LopHoc classTableModel = null;

    private final String[] COLUMNS = {"Mã lớp học", "STT", "Mã khóa học", "Mã học viên",
        "Ngày đăng kí", "Trạng thái"};

    private LopHocService lopHocService = null;

    private TableRowSorter<TableModel> rowSorter = null;

    
    
    public QuanLyLopHocController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;

        this.classTableModel = new ClassTableModel_LopHoc();

        this.lopHocService = new LopHocServiceImpl();
    }

    public void setDataToTable() {
        List<LopHoc> listItem = lopHocService.getList();
        DefaultTableModel model = classTableModel.setTableHocVien(listItem, COLUMNS);
        JTable table = new JTable(model);

        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);

        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
             if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    LopHoc lopHoc = new LopHoc();
                    lopHoc.setMa_lop_hoc((int) model.getValueAt(selectedRowIndex, 0));
                    
                    lopHoc.setMaKhoaHoc((int) model.getValueAt(selectedRowIndex, 2));
                    lopHoc.setMaHocVien((int) model.getValueAt(selectedRowIndex, 3));
                    
                    lopHoc.setNgay_dang_ky((java.sql.Date) (Date) model.getValueAt(selectedRowIndex, 4));
                  
                    
                  //  khoaHoc.setNgay_sinh(classFormat.covertDateToDateSql(
                //            classFormat.covertStringToDate(model.getValueAt(selectedRowIndex, 3).toString(), "dd/MM/yyyy")));


                    lopHoc.setTinh_trang((boolean) model.getValueAt(selectedRowIndex, 5));

                    LopHocJFrame frame = new LopHocJFrame(lopHoc);
                    frame.setLocationRelativeTo(null);
                    frame.setResizable(false);
                    frame.setTitle("Thông tin lớp học");
                    frame.setVisible(true);
                     frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
             }
      }

});
        // design
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        jpnView.removeAll();
        jpnView.setLayout(new CardLayout());
        jpnView.add(scroll);
        jpnView.validate();
        jpnView.repaint();
    }
    
    public void setEvent(){
    btnAdd.addMouseListener(new MouseAdapter(){
        
        @Override
            public void mouseClicked(MouseEvent e) {
            LopHocJFrame frame = new LopHocJFrame(new LopHoc());
            frame.setTitle("Thông tin lớp học");
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAdd.setBackground(new Color(0,0,255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAdd.setBackground(new Color(102,102,255));
            }
        
    });

}
}
