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
import DTO.LopHoc;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ClassTableModel_LopHoc {

    public DefaultTableModel setTableHocVien(List<LopHoc> listItem, String[] listColumn) {
        int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        int num = listItem.size();
        LopHoc lopHoc = null;
        for (int i = 0; i < num; i++) {
            lopHoc = listItem.get(i);
            obj = new Object[columns];
            obj[0] = lopHoc.getMa_lop_hoc();
            obj[1] = (i + 1);
            obj[2] = lopHoc.getMaKhoaHoc();
            obj[3] = lopHoc.getMaHocVien();
            obj[4] = lopHoc.getNgay_dang_ky();
            obj[5] = lopHoc.isTinh_trang();
            dtm.addRow(obj);
        }
        return dtm;
    }

}
