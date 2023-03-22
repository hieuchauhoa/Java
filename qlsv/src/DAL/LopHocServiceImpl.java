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

public class LopHocServiceImpl implements LopHocService {

    private LopHocDAO LopHocDAO = null;

    public LopHocServiceImpl() {
        this.LopHocDAO = new LopHocDAOImpl();
    }

    @Override
    public List<LopHoc> getList() {
        return LopHocDAO.getList();
    }
    
    @Override
    public int createOrUpdate(LopHoc lopHoc) {
        return LopHocDAO.createOrUpdate(lopHoc);
    }
}