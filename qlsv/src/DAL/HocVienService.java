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

import DTO.HocVien;
import java.util.List;

public interface HocVienService {
    
    public List<HocVien> getList();
    
    public int createOrUpdate(HocVien hocVien);
    
}