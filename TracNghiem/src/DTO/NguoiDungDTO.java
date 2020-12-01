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
public class NguoiDungDTO {
    private int nd_id;
    private String name;
    private String username;
    private String password;
    private String trangthai;
    
    public NguoiDungDTO(){
        
    }

    public NguoiDungDTO(int nd_id, String name, String username, String password, String trangthai) {
        this.nd_id = nd_id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.trangthai = trangthai;
    }

    public int getNd_id() {
        return nd_id;
    }

    public void setNd_id(int nd_id) {
        this.nd_id = nd_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
    
}
