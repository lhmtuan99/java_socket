/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DeThiDTO;
import DTO.otpDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class otpDAO {
    Connection conn = null;
    Statement st= null;
    ResultSet rs = null;
    ArrayList<otpDTO> dsdt = new ArrayList<>();
    public otpDAO()
    {
        MyConnection connectiondatabase = new MyConnection();
        conn=connectiondatabase.getConnecDB();
    }
}
