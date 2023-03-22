/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.TaiKhoan;

/**
 *
 * @author LENOVO
 */
public interface TaiKhoanService {
    
    public TaiKhoan login(String tenDangNhap, String matKhau);
    
}