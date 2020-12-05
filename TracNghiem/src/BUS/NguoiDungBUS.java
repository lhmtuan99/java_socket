/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NguoiDungDAO;
import DTO.NguoiDungDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NguoiDungBUS {
    public static ArrayList <NguoiDungDTO> dsnd = new ArrayList<>();
    
    
    public void them(NguoiDungDTO nd)
    {
        NguoiDungDAO data = new NguoiDungDAO();
        data.them(nd);
        //dsnd.add(nd);
    }
}
