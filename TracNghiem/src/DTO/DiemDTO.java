/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Admin
 */
public class DiemDTO {
    private int dt_id, nd_id;
    private String Diem;
    public DiemDTO(){
        
    }

    public DiemDTO(int dt_id, int nd_id, String Diem) {
        this.dt_id = dt_id;
        this.nd_id = nd_id;
        this.Diem = Diem;
    }

    public int getDt_id() {
        return dt_id;
    }

    public void setDt_id(int dt_id) {
        this.dt_id = dt_id;
    }

    public int getNd_id() {
        return nd_id;
    }

    public void setNd_id(int nd_id) {
        this.nd_id = nd_id;
    }

    public String getDiem() {
        return Diem;
    }

    public void setDiem(String Diem) {
        this.Diem = Diem;
    }
    
}
