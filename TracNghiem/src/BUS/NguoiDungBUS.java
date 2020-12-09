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
    public static ArrayList <NguoiDungDTO> dsnguoidung  = new ArrayList<>();
    
    
    public void them(NguoiDungDTO nguoidung)
    {
        NguoiDungDAO data = new NguoiDungDAO();
        data.them(nguoidung); 
        dsnguoidung.add(nguoidung);
    }
    public int CheckLogin(String username, String gmail)
    {
        NguoiDungDAO data = new NguoiDungDAO();
        if(data.CheckLogin(username, gmail)==1)
        {
            return 1;
        }
        else {
            return 0;
        }
    }
}
