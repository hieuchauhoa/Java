/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author LENOVO
 */
import java.sql.Date;

public class LopHoc  {

    private int ma_lop_hoc;
    private KhoaHoc khoaHoc;
    
    private int ma_khoa_hoc;
    private int ma_hoc_vien;

    
    private HocVien hocVien;

    private Date ngay_dang_ky;
    private boolean tinh_trang;

    public int getMa_lop_hoc() {
        return ma_lop_hoc;
    }

    public void setMa_lop_hoc(int ma_lop_hoc) {
        this.ma_lop_hoc = ma_lop_hoc;
    }

    public KhoaHoc getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(KhoaHoc khoaHoc) {
        this.khoaHoc = khoaHoc;
    }
    
    public void setMaKhoaHoc(int ma_khoa_hoc){
        this.ma_khoa_hoc=ma_khoa_hoc;
    }
    
    public int getMaKhoaHoc(){
        return ma_khoa_hoc;
    }
    
    public HocVien getHocVien() {
        return hocVien;
    }

    public void setHocVien(HocVien hocVien) {
        this.hocVien = hocVien;
    } 
    
    public void setMaHocVien(int ma_hoc_vien){
        this.ma_hoc_vien=ma_hoc_vien;
    }
    
    public int getMaHocVien(){
        return ma_hoc_vien;
    }

    public Date getNgay_dang_ky() {
        return ngay_dang_ky;
    }

    public void setNgay_dang_ky(Date ngay_dang_ky) {
        this.ngay_dang_ky = ngay_dang_ky;
    }

    public boolean isTinh_trang() {
        return tinh_trang;
    }

    public void setTinh_trang(boolean tinh_trang) {
        this.tinh_trang = tinh_trang;
    }

}
