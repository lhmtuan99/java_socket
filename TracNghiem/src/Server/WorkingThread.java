/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;


import BUS.DeThiBUS;
import DAO.DeThiDAO;
import DAO.NguoiDungDAO;
import DAO.ThanhTichDAO;
import DTO.CauHoi;
import DTO.DeThiDTO;
import DTO.DiemDTO;
import DTO.NguoiDungDTO;
import DTO.otpDTO;
import static Server.Server.ListUserOnline;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author tiennguyen
 */
public class WorkingThread extends Thread {
    public NguoiDungDTO nguoiDung = new NguoiDungDTO();
    public DTO.CauHoi currentCauHoiforClient = null;
    public static ArrayList<DTO.CauHoi> ThiThu = null;
    public int totalCauHoitrue=0;
    public int totalCauHoifalse=0;
    public int totalSoCau = 0;
    protected Socket socket;
    public String key="";
    public WorkingThread(Socket clientSocket) {
        this.socket = clientSocket;
    }
    InputStream inp = null;
    BufferedReader brinp = null;
    DataOutputStream out = null;
    public void run() {
        String line;
        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
        while (true) {
            try {
                line = brinp.readLine();
                if ((line == null) || line.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    return;
                } else {
                    // excute option of client here
                    line = decrypt(line, key+socket.getPort()); // data client send to server 
                    System.out.println(line);
                    System.out.println(socket);
                    System.out.println(socket.getPort());
                    String [] Clause = line.split(":");
                    line="";
                    // tao otp
                    if(Clause[0].equals("OTP")){
                        DAO.otpDAO otpDao = new DAO.otpDAO();
                        DTO.otpDTO otpp = new DTO.otpDTO();
                        otpp.setGmail(Clause[1]);
                        int res = otpDao.kiemtra(otpp);
                        if(res==1){
                            otpDao.them(otpp);
                        }
                        line =""+res+":";
                        
                    }
                    // xoa otp
                    if(Clause[0].equals("XOAOTP")){
                        DAO.otpDAO otpDao = new DAO.otpDAO();
                        otpDao.xoa(Clause[1]);
                    }
                    // thong tin dk
                    if(Clause[0].equals("DK")){
                        DAO.otpDAO otpDao = new DAO.otpDAO();
                        if(otpDao.kiemtraOTP(Clause[4],Clause[2])==1){
                            DTO.NguoiDungDTO nd = new DTO.NguoiDungDTO();
                            nd.setName(Clause[1]);
                            nd.setUsername(Clause[2]);
                            nd.setPassword(Clause[3]);
                            DAO.NguoiDungDAO nddao = new DAO.NguoiDungDAO();
                            nddao.them(nd);
                            line="DKTC:";
                        }else {
                            line="SAIOTP:";
                        }
                    }
                    // dang nhap
                    if(Clause[0].equals("DN")){
                        DAO.NguoiDungDAO nd = new DAO.NguoiDungDAO();
                        if(nd.CheckLogin(Clause[1], Clause[2])==1){
                            
                            nguoiDung = new NguoiDungDAO().getNguoiDung(Clause[1]);
                            boolean checkBlock = new DAO.NguoiDungDAO().getBlockUser(nguoiDung.getNd_id());
                            if(checkBlock==true){
                                line="ERROR:BLOCK:";
                            }else {
                                line="DNOK:"+nguoiDung.getName()+":"+nguoiDung.getUsername()+":"+nguoiDung.getBlockaccount()+":"+nguoiDung.getBlocktaode()+":"+nguoiDung.getBlockthi()+":";
                                nguoiDung.setPORT(socket.getPort());
                                ListUserOnline.add(nguoiDung);
                            }
                            //line="DNOK:"+nguoiDung.getName()+":"+nguoiDung.getUsername()+":"+nguoiDung.getBlockaccount()+":"+nguoiDung.getBlocktaode()+":"+nguoiDung.getBlockthi()+":";
                        }else {
                            line="DNSAI:";
                        }
                    }
                    //
                    // load de thi
                    if(Clause[0].equals("LOAD")){
                        if(Clause[1].equals("DETHI")){
                            ArrayList <DeThiDTO> dsdt = new ArrayList<>();
                            dsdt = new DeThiDAO().docDSDT(nguoiDung.getNd_id());
                            line="LOADDETHI:";
                            for (DeThiDTO dsdt1 : dsdt) {
                                line+=dsdt1.getDt_id()+":"+dsdt1.getTieude()+":"+dsdt1.getMonthi()+":"+dsdt1.getSocau()+":"+dsdt1.getThoiluong()+":"+dsdt1.getTongsonguoithi()+":"+dsdt1.getDt_public()+":";
                            }
                        }else if(Clause[1].equals("INF")){
                            line="INF:"+nguoiDung.getName()+":"+nguoiDung.getUsername()+":"+nguoiDung.getBlockaccount()+":"+nguoiDung.getBlocktaode()+":"+nguoiDung.getBlockthi()+":";
                        }else if(Clause[1].equals("DE")){
//                            ArrayList<DTO.CauHoi> ListCauHoi = new ArrayList<>();
//                            ListCauHoi = new DeThiDAO().GetAllCauHoiFromDethi(Clause[2]);
//                            
//                            line="LOADDE:";
//                            for (CauHoi ListCauHoi1 : ListCauHoi) {
//                                line+=ListCauHoi1.getCh_id()+": "+ListCauHoi1.getCauhoi()+": "+ListCauHoi1.getDapanA()+": "+ListCauHoi1.getDapanB()+": "+ListCauHoi1.getDapanC()+": "+ListCauHoi1.getDapanD()+": "+ListCauHoi1.getTraloi()+":";
//                            }
//                            System.out.println("-------->"+line);
                        }else if(Clause[1].equals("DECOMBOBOX")){
                            ArrayList <DeThiDTO> dsdt = new ArrayList<>();
                            dsdt = new DeThiDAO().docDSDT(nguoiDung.getNd_id());
                            line="LOADDECOMBOBOX:";
                            for (DeThiDTO dsdt1 : dsdt) {
                                line+=dsdt1.getDt_id()+":"+dsdt1.getTieude()+":"+dsdt1.getMonthi()+":"+dsdt1.getSocau()+":"+dsdt1.getThoiluong()+":"+dsdt1.getTongsonguoithi()+":";
                            }
                        }else if(Clause[1].equals("DECOMBOBOXTHITHU")){
                            totalCauHoifalse=0;
                            totalCauHoitrue=0;
                            ArrayList <DeThiDTO> dsdt = new ArrayList<>();
                            dsdt = new DeThiDAO().GetAllDeThiPublic();
                            line="LOADDECOMBOBOXTHITHU:";
                            for (DeThiDTO dsdt1 : dsdt) {
                                line+=dsdt1.getDt_id()+":"+dsdt1.getTieude()+":"+dsdt1.getMonthi()+":"+dsdt1.getSocau()+":"+dsdt1.getThoiluong()+":"+dsdt1.getTongsonguoithi()+":";
                            }
                           
                        }else if(Clause[1].equals("DETHITHU")){
                            ThiThu = new ArrayList<>();
                            ThiThu = new DeThiDAO().GetAllCauHoiFromDethi(Clause[2]);
                            DTO.DeThiDTO dethi = new DAO.DeThiDAO().GetDeThi(Clause[2]);
                            line="LOADDETHITHU:"+dethi.getSocau()+":"+dethi.getThoiluong()+":";
                            totalSoCau = Integer.parseInt(dethi.getSocau());
                        }else if(Clause[1].equals("CAUHOITHU1")){
                            if(nguoiDung.getBlockthi()==0){
                                line="ERROR:CAMTHI:";
                            }else{
                                //Thread.sleep(200);
                            //check kq
                            String kq = "FALSE:-1:";
                            if(!Clause[2].equals("z")){
                                if(Clause[2].trim().equals(currentCauHoiforClient.getTraloi().trim())){
                                totalCauHoitrue++;
                                kq="TRUE:"+totalCauHoitrue+":";
                            }else {
                                totalCauHoifalse++;
                                kq="FALSE:"+totalCauHoifalse+":";
                            }
                            }
                            //
                            
                            int randomIndex = (int) (Math.random() * ThiThu.size());
                            currentCauHoiforClient = ThiThu.get(randomIndex);
                            ThiThu.remove(currentCauHoiforClient);
                            line="CAUHOI:"+currentCauHoiforClient.getCauhoi().trim()+":"+currentCauHoiforClient.getDapanA().trim()+":"+currentCauHoiforClient.getDapanB().trim()+":"+currentCauHoiforClient.getDapanC().trim()+":"+currentCauHoiforClient.getDapanD().trim()+":"+kq+":";
                            System.out.println("-11111111111111111--------------------111111111111111111");
                            }
                        }else if(Clause[1].equals("CAUHOITHU2")){
                            String kq = "FALSE:-1:";
                            if(Clause[2].trim().equals(currentCauHoiforClient.getTraloi().trim())){
                                totalCauHoitrue++;
                                kq="TRUE:"+totalCauHoitrue+":";
                            }else {
                                totalCauHoifalse++;
                                kq="FALSE:"+totalCauHoifalse+":";
                            }
                            line="CAUHOI1:"+currentCauHoiforClient.getCauhoi().trim()+":"+currentCauHoiforClient.getDapanA().trim()+":"+currentCauHoiforClient.getDapanB().trim()+":"+currentCauHoiforClient.getDapanC().trim()+":"+currentCauHoiforClient.getDapanD().trim()+":"+kq+":";
                        }
                    }
                    //
                    // check KETQUACAUTHU
                    if(Clause[0].equals("KETQUACAUTHU")){
                        Thread.sleep(500);
                        if(Clause[1].trim().equals(currentCauHoiforClient.getTraloi().trim())){
                            totalCauHoitrue+=1;
                            line="KETQUACAUTHU1:TRUE:"+totalCauHoitrue+":";
                            System.out.println("");
                        }else {
                            totalCauHoifalse+=1;
                            line="KETQUACAUTHU1:FALSE:"+totalCauHoifalse+":";
                            System.out.println("1");
                        }
                    }
                    //
                    //LOGOUT
                    if(Clause[0].equals("LOGOUT")){
                        ListUserOnline.remove(nguoiDung);
                    }
                    //
                    // KETTHUCTHITHU
                    if(Clause[0].equals("KETTHUCTHITHU")){
                        float res = totalCauHoitrue/totalSoCau*10;
                        line="KETQUATHITHUXONG:"+totalCauHoitrue+":"+totalCauHoifalse+":"+res+":";
                        DTO.DeThiDTO dthi = new DAO.DeThiDAO().GetDeThi(Clause[1]);
                        
                        float tongcau = totalCauHoitrue+ totalCauHoifalse;
                        float diemmotcau = 10/tongcau;
                        //int tongsocau = Integer.parseInt(str[1])+ Integer.parseInt(str[2]);
                        float resu = totalCauHoitrue * diemmotcau;
                        
                        new DAO.DeThiDAO().UpdateLuotThi(dthi.getDt_id(), dthi.getTongsonguoithi());
                        new DAO.DeThiDAO().AddDiem(nguoiDung.getNd_id(),dthi.getDt_id(),resu+"");
                        totalCauHoifalse=0;
                        totalCauHoitrue=0;
                    }
                    //
                    // PUBLICDETHI
                    if(Clause[0].equals("PUBLICDETHI")){
                        new DeThiDAO().PublicDeThi(Clause[1]);
                        line="PUBLICDETHITHANHCONG:";
                    }
                    // KETTHUCTHITHU
                    if(Clause[0].equals("EDITNGUOIDUNG1")){
                        new DAO.NguoiDungDAO().UpdateNguoiDungWithPass(nguoiDung.getNd_id(), Clause[1], Clause[2].trim());
                        nguoiDung.setName(Clause[1]);
                        line="UPDATENGUOIDUNGSUCCESS:";
                    }
                    if(Clause[0].equals("EDITNGUOIDUNG2")){
                        new DAO.NguoiDungDAO().UpdateNguoiDung(nguoiDung.getNd_id(), Clause[1]);
                        nguoiDung.setName(Clause[1]);
                        line="UPDATENGUOIDUNGSUCCESS:";
                    }
                    //
                    //
                    // UPDATE CAU HOI
                    if(Clause[0].equals("UPDATE")){
                        if(Clause[1].equals("CAUHOI")){
                            DTO.CauHoi z = new CauHoi();
                            z.setCh_id(Integer.parseInt(Clause[2]));
                            z.setCauhoi(Clause[3]);
                            z.setDapanA(Clause[4]);
                            z.setDapanB(Clause[5]);
                            z.setDapanC(Clause[6]);
                            z.setDapanD(Clause[7]);
                            z.setTraloi(Clause[8]);
                            new DAO.DeThiDAO().UpdateCauhoi(z);
                            line="UPDATECAUHOITHANHCONG:";
                        }
                    }
                    //
                    // tiếp làm thành tích
                    if(Clause[0].equals("THANHTICH"))
                    {   
                        ArrayList <DeThiDTO> dsdt = new ArrayList<>();
                        ArrayList <DiemDTO> dsdiem = new ArrayList<>();
                            dsdt = new ThanhTichDAO().docDSDT();
                            line="LOADTHANHTICH:";
                            for (DeThiDTO dsdt1 : dsdt) {
                                line+=dsdt1.getDt_id()+":"+dsdt1.getTieude()+":"+dsdt1.getMonthi()+":"+dsdt1.getThoiluong()+":"+dsdt1.getSocau()+":"+dsdt1.getTongsonguoithi()+":";
                                float diemcaonhat = -1;
                                float tong = 0;
                                float diemtrungbinh = 0 ;
                                dsdiem = new ThanhTichDAO().docDSDiem(dsdt1.getDt_id());
                                for(DiemDTO dsdiem1 : dsdiem)
                                {   
                                    if(diemcaonhat < Integer.parseInt(dsdiem1.getDiem()))
                                    {
                                        diemcaonhat = Integer.parseInt(dsdiem1.getDiem());
                                    }
                                    tong += Integer.parseInt(dsdiem1.getDiem());
                                }
                                line+=diemcaonhat+":";
                                diemtrungbinh = tong / dsdiem.size();
                                System.out.println("Diem cao nhut: "+diemcaonhat);
                                System.out.println("Diem trung binh: "+diemtrungbinh);
                                line+=diemtrungbinh+":";
                            }
                    }
                    //
                    // Them xoa sua de thi
                    if(Clause[0].equals("DETHI")){
                        if(Clause[1].equals("THEM")){   //them
                            if(nguoiDung.getBlocktaode()==0){ // bi block
                                line="ERROR:ACCOUTBLOCKED:";
                            }else {
                                DeThiDTO dt = new DeThiDTO();
                                dt.setTieude(Clause[2]);
                                dt.setMonthi(Clause[3]);
                                dt.setSocau(Clause[4]);
                                dt.setThoiluong(Clause[5]);
                                dt.setNguoitao(nguoiDung.getNd_id());

                                DeThiBUS bus = new DeThiBUS();
                                bus.them(dt);
                                line="TAOTHANHCONG:";
                            }
                        }else if(Clause[1].equals("XOA")){  //xoa
                            DeThiDAO dt = new DeThiDAO();
                            dt.xoa(Clause[2]);
                            line="XOATHANHCONG:";
                        }else if(Clause[1].equals("SUA")){  //sua
                            DeThiBUS bus  = new DeThiBUS();
                            DeThiDTO dethi = new DeThiDTO();
                            dethi.setDt_id(Integer.parseInt(Clause[2]));
                            dethi.setTieude(Clause[3]);
                            dethi.setMonthi(Clause[4]);
                            dethi.setSocau(Clause[5]);
                            dethi.setThoiluong(Clause[6]);
                            bus.sua(dethi);
                            line="SUATHANHCONG:";
                        }
                    }
                    //
                    // send data to client
                    if(line.length()>0){
                        line = encrypt(line,key+socket.getPort());
                        System.out.println(line);
                        out.writeBytes(line + "\n");
                        out.flush();
                        line="";
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(WorkingThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(WorkingThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public String encrypt(String strToEncrypt, String myKey) {
      try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = myKey.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return java.util.Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
      } catch (Exception e) {
            System.out.println(e.toString());
      }
      return null;
    }
    public String decrypt(String strToDecrypt, String myKey) {
      try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = myKey.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(java.util.Base64.getDecoder().decode(strToDecrypt)));
      } catch (Exception e) {
            System.out.println(e.toString());
      }
      return null;
}
}

