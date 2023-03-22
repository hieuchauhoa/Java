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
public class KhoaHocServiceImpl implements KhoaHocService {

    private KhoaHocDAO khoaHocnDAO = null;

    public KhoaHocServiceImpl() {
        this.khoaHocnDAO = new KhoaHocDAOImpl();
    }

    @Override
    public List<KhoaHoc> getList() {
        return khoaHocnDAO.getList();
    }
    
    @Override
    public int createOrUpdate(KhoaHoc hocVien) {
        return khoaHocnDAO.createOrUpdate(hocVien);
    }
}