
package BLL;

import DTO.DanhMucBean;
import DTO.HocVien;
import GUI.HocVienJPanel;
import GUI.KhoaHocJPanel;
import GUI.LopHocJPanel;
import GUI.ThongKeJPanel;
import GUI.TrangChuJPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author LENOVO
 */
public class ChuyenManHinhController {
    private JPanel root;
    private String kindSelected ="";
    
   private List<DanhMucBean> listDanhMuc;

    public ChuyenManHinhController(JPanel root) {
        this.root = root;
    }
    
        public void setDashboard(JPanel jpnItem, JLabel jlbItem){
        kindSelected = "TrangChu";
        jpnItem.setBackground(new Color(0,0,153));
        jlbItem.setBackground(new Color(0,0,153));
        
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChuJPanel());
        root.validate();
        root.repaint();
    }
    
   public void setEvent(List<DanhMucBean> listDanhMuc) {
     this.listDanhMuc=listDanhMuc;
     for (DanhMucBean item : listDanhMuc) {
           item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
     }
}

class LabelEvent implements MouseListener {

      private JPanel node;
      private String kind;

      private JPanel jpnItem;
      private JLabel jlbItem;

      public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
           this.kind = kind;
           this.jpnItem = jpnItem;
           this.jlbItem = jlbItem;
      }

      @Override
      public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "TrangChu":
                    node = new TrangChuJPanel();
                    break;
                case "HocVien":
                    node = new HocVienJPanel();
                    break;
                case "KhoaHoc":
                    node = new KhoaHocJPanel();
                    break;
                case "LopHoc":
                    node = new LopHocJPanel();
                    break;
                case "ThongKe":
                    node = new ThongKeJPanel();
                    break;
                
                default:
                    break;
           }
           root.removeAll();
           root.setLayout(new BorderLayout());
           root.add(node);
           root.validate();
           root.repaint();
           setChangeBackground(kind);
      }

      @Override
      public void mousePressed(MouseEvent e) {
           kindSelected = kind;
           jpnItem.setBackground(new Color(0,0,153));
           jlbItem.setBackground(new Color(0,0,153));
      }

      @Override
      public void mouseReleased(MouseEvent e) {

      }

      @Override
      public void mouseEntered(MouseEvent e) {
          jpnItem.setBackground(new Color(0,0,255));
          jlbItem.setBackground(new Color(0,0,255));
      }

      @Override
      public void mouseExited(MouseEvent e) {
          if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(102,102,255));
                jlbItem.setBackground(new Color(102,102,255));
          }
      }

}
private void setChangeBackground(String kind){
    for(DanhMucBean item : listDanhMuc)
        if(item.getKind().equalsIgnoreCase(kind)){
            item.getJpn().setBackground(new Color(0,0,153));
            item.getJlb().setBackground(new Color(0,0,153));
        }
        else{
            item.getJpn().setBackground(new Color(102,102,255));
            item.getJlb().setBackground(new Color(102,102,255));
        }
}
}
