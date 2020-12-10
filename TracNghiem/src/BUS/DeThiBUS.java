/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DeThiDAO;
import DTO.DeThiDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class DeThiBUS {
    public static ArrayList <DeThiDTO> dsdt;
    public void docDSDT()
    {
//        DeThiDAO data = new DeThiDAO();
//        if(dsdt == null) dsdt = new ArrayList<DeThiDTO>();
//        dsdt = data.docDSDT();
    }
    public void them(DeThiDTO dethi)
    {
        DeThiDAO data = new DeThiDAO();
        data.them(dethi);
        //dsdt.add(dethi);
    }
    public void sua(DeThiDTO dethi)
    {
        DeThiDAO data = new DeThiDAO();
        data.sua(dethi);
//        for(DeThiDTO dethi1 : dsdt)
//        {
//            if(dethi1.getDt_id() == Integer.parseInt(id))
//            {
//                dethi1.setTieude(dethi.getTieude());
//                dethi1.setThoiluong(dethi.getThoiluong());
//                dethi1.setMonthi(dethi.getMonthi());
//                dethi1.setSocau(dethi.getSocau());
//            }
//        }
    }
    public void xoa(DeThiDTO dethi)
    {
        DeThiDAO data = new DeThiDAO();
        data.xoa(Long.toString(dethi.getDt_id()));
        dsdt.remove(dethi);
    }
}   
