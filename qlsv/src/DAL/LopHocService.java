/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

/**
 *
 * @author LENOVO
 */
import DTO.LopHoc;
import java.util.List;

public interface LopHocService {
    
    public List<LopHoc> getList();
    
    public int createOrUpdate(LopHoc hocVien);
    
}