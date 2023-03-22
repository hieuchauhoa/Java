package Main;


import GUI.DangNhapDialog;
import GUI.HocVienJFrame;
import GUI.MainJFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class Main {
    public static void main(String[] args) {
        
         DangNhapDialog dialog=new DangNhapDialog(null,true);
        dialog.setTitle("Đăng Nhập");
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
      
        dialog.setVisible(true);
        
        
        
    }
  
}
