/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author Admin
 */
public class otpDTO {
    private int id;
    private String gmail;
    private String otp;

    public otpDTO(int id, String gmail, String otp) {
        this.id = id;
        this.gmail = gmail;
        this.otp = otp;
    }
    public otpDTO ()
    {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

//    public LocalDateTime getTime() {
//        return time;
//    }

//    public void setTime(LocalDateTime time) {
//        this.time = time;
//    }

  
    
}
