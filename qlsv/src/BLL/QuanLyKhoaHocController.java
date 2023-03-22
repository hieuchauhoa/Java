/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;



import DAL.KhoaHocService;
import DAL.KhoaHocServiceImpl;
import DTO.KhoaHoc;
import GUI.KhoaHocJFrame;
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
public class QuanLyKhoaHocController {

    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;

    private ClassTableModel_KhoaHoc classTableModel = null;

    private final String[] COLUMNS = {"Mã khoá học", "STT", "Tên khóa học", "Mô tả",
        "Ngày bắt đầu", "Ngày kết thúc",  "Trạng thái"};

    private KhoaHocService khoaHocnService = null;

    private TableRowSorter<TableModel> rowSorter = null;

    
    
    public QuanLyKhoaHocController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;

        this.classTableModel = new ClassTableModel_KhoaHoc();

        this.khoaHocnService = new KhoaHocServiceImpl();
    }

    public void setDataToTable() {
        List<KhoaHoc> listItem = khoaHocnService.getList();
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

                    KhoaHoc khoaHoc = new KhoaHoc();
                    khoaHoc.setMa_khoa_hoc((int) model.getValueAt(selectedRowIndex, 0));
                    khoaHoc.setTen_khoa_hoc(model.getValueAt(selectedRowIndex, 2).toString());
                    khoaHoc.setMo_ta(model.getValueAt(selectedRowIndex, 3).toString());
                    
                    khoaHoc.setNgay_bat_dau((java.sql.Date) (Date) model.getValueAt(selectedRowIndex, 4));
                    khoaHoc.setNgay_ket_thuc((java.sql.Date) (Date) model.getValueAt(selectedRowIndex, 5));
                    
                  //  khoaHoc.setNgay_sinh(classFormat.covertDateToDateSql(
                //            classFormat.covertStringToDate(model.getValueAt(selectedRowIndex, 3).toString(), "dd/MM/yyyy")));


                    khoaHoc.setTinh_trang((boolean) model.getValueAt(selectedRowIndex, 6));

                    KhoaHocJFrame frame = new KhoaHocJFrame(khoaHoc);
                    frame.setLocationRelativeTo(null);
                    frame.setResizable(false);
                    frame.setTitle("Thông tin khóa học");
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
            KhoaHocJFrame frame = new KhoaHocJFrame(new KhoaHoc());
            frame.setTitle("Thông tin khoá học");
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
