/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.otpDAO;
import DTO.otpDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class otpBUS {
    public static ArrayList <otpDTO> dsotp  = new ArrayList<>();
    public void them(otpDTO otp)
    {
        otpDAO data = new otpDAO();
        data.them(otp);
        dsotp.add(otp);
    }
    public int check(otpDTO otp)
    {
        otpDAO data = new otpDAO();
        if(data.kiemtra(otp)==0)
        {
            return 0;
        }
        else 
        {
            return 1;
        }
    }
    public void xoa(String gmail)
    {
        otpDAO data = new otpDAO();
        data.xoa(gmail);
        //dsotp.remove(gmail);
    }
    public int checkOTP(String otp, String gmail)
    {
        otpDAO data = new otpDAO();
        if(data.kiemtraOTP(otp, gmail)==1)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
