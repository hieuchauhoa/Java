/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;


import DTO.KhoaHoc;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface KhoaHocDAO {
    public List<KhoaHoc> getList();
    
    public int createOrUpdate(KhoaHoc khoaHoc);
}
