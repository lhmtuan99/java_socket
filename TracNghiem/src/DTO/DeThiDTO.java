/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class DeThiDTO {
    private int dt_id;
    private String tieude, monthi, socau;
    private String thoiluong;
    public DeThiDTO(){
        
    }
    public DeThiDTO(int dt_id, int nd_id, String tieude, String monthi, String socau, String ngaytao, String thoiluong) {
        this.dt_id = dt_id;
        this.tieude = tieude;
        this.monthi = monthi;
        this.socau = socau;
        this.thoiluong = thoiluong;
    }

    public int getDt_id() {
        return dt_id;
    }

    public void setDt_id(int dt_id) {
        this.dt_id = dt_id;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getMonthi() {
        return monthi;
    }

    public void setMonthi(String monthi) {
        this.monthi = monthi;
    }

    public String getSocau() {
        return socau;
    }

    public void setSocau(String socau) {
        this.socau = socau;
    }

    public String getThoiluong() {
        return thoiluong;
    }

    public void setThoiluong(String thoiluong) {
        this.thoiluong = thoiluong;
    }

  
    
}
