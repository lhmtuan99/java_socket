/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DAO.ThanhTichDAO;
import DTO.DeThiDTO;
import DTO.DiemDTO;
import DTO.NguoiDungDTO;
import static Server.Server.Clients;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;

/**
 *
 * @author tiennguyen
 */
public class ServerExcute implements Runnable{
    private Scanner sc = new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private Thread t;
    private String threadName;
 
    ServerExcute(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
        
    }
    public void Menu(){
        System.out.println("-------------------MENU---------------------");
        System.out.println("1: Có tổng cộng bao nhiêu người dùng.");
        System.out.println("2: Thông tin cơ bản của tất cả người dùng.");
        System.out.println("3: Gửi tin nhắn đến một người đang online.");
        System.out.println("4: Gửi tin nhắn đến tất cả người đang online.");
        System.out.println("5: Có bao nhiêu người đang online.");
        System.out.println("6: Thông tin cơ bản những người đang online.");
        System.out.println("7: Block.");
        System.out.println("8: Điểm cao nhất/ thấp nhất theo đề thi.");
        System.out.println("9: Hệ thống có bao nhiêu đề thi.");
        System.out.println("10: Thông tin của các đề thi.");
        System.out.println("--------------------------------------------");
        
    }
    public void ServerExcute() throws IOException, SQLException{
        boolean flag = false;
        Server R1 = new Server("Thread-Server");
        R1.start();
        
        while(!flag){
            Menu();
            System.out.print("Lựa chọn của bạn:");
            int n;
            n= sc.nextInt();
            switch(n){
//                case 0: {
//                    R1.shutdown();
//                    flag = true;
//                    break;
//                }
                case 1: {
                    System.out.println("Bạn chọn 1:");
                    // có tổng bao nhiêu người dùng;
                    DAO.MyConnection myconnection = new DAO.MyConnection();
                    Connection con = myconnection.getConnecDB();
                    Statement statement = con.createStatement();
                    String sql = "select COUNT(*) as total from NguoiDung";
                    ResultSet rs;
                    rs = statement.executeQuery(sql);
                    rs.next();
                    System.out.println("Tổng số người dùng: "+rs.getInt("total"));
                    break;
                }
                case 2:{
                    System.out.println("Bạn chọn 2:");
                    DAO.MyConnection myconnection = new DAO.MyConnection();
                    Connection con = myconnection.getConnecDB();
                    Statement statement = con.createStatement();
                    String sql = "select * from NguoiDung";
                    ResultSet rs;
                    rs = statement.executeQuery(sql);
                    String format = "%-5s%-40s%-40s%-25s%-25s%-25s%n";
                    System.out.printf(format, "ID", "Họ & Tên","Email","BlockAccount","BlockTaoDe","BlockThi");
                  
                    while(rs.next()){
                        int id = rs.getInt("nd_id");
                        String name = rs.getString("nd_name");
                        String username = rs.getString("nd_username");
                        int trangthai = rs.getInt("nd_blockaccount");
                        int trangthai1 = rs.getInt("nd_blocktaode");
                        int trangthai2 = rs.getInt("nd_blockthi");
                        System.out.printf(format, id, name,username,trangthai==1?"FALSE":"TRUE",trangthai1==1?"FALSE":"TRUE",trangthai2==1?"FALSE":"TRUE");
                    }
                  
                    break;
                }
                case 3:{
                    System.out.println("Bạn chọn 3");
                    System.out.println("Nhập ID người muốn gửi: ");
                    String str= br.readLine();
                    int flagIdNguoiDung =0;
                    int port= -1;
                    for(int i=0;i<Server.ListUserOnline.size();i++){
                        String id = Server.ListUserOnline.get(i).getNd_id()+"";
                        if(id.trim().equals(str.trim())){
                            flagIdNguoiDung=1;
                            port = Server.ListUserOnline.get(i).getPORT();
                        }
                    }
                    if(flagIdNguoiDung==1){
                        
                    for(int i=0;i<Clients.size();i++){
                        if(Clients.get(i).PORT == port && Clients.get(i).isAlive()){
                            System.out.println("Nhập tin nhắn muốn gửi: ");
                            String str2="";
                            str2= br.readLine();
                            Clients.get(i).line=str2;
                        }
                    }
                    }else{
                        System.out.println("ID Người dùng không chính xác hoặc người dùng đang offline...");
                    }
                         
                    
                    break;
                }
                case 4:{
                    System.out.println("Bạn chọn 4");
                    System.out.println("Nhap dữ liệu muốn gửi: ");
                    String str= "";
                    str= br.readLine();
                    //Server.Clients.get(0).line=str;
                    if(Server.ListUserOnline.size()>0){
                        for(int i=0;i<=Clients.size()-1;i++){
                            if(Server.Clients.get(i).isAlive())
                            Server.Clients.get(i).line=str;
                        }
                    }else System.out.println("Hiện tại không có ai đang online...");
                    
                    break;
                }
                case 5:{
                    System.out.println("Bạn chọn 5");
                    
                    System.out.println("Tổng số người đang online: "+Server.ListUserOnline.size());
                    
                    break;
                }
                case 6:{
                    System.out.println("Bạn chọn 6");
                    String format = "%-10s%-25s%-25s%-25s%-25s%-25s%n";
                    System.out.printf(format, "ID", "Họ & Tên","Email","BlockAccount","BlockTaoDe","BlockThi");
                    //System.out.printf(format, id, name,username,trangthai);
                    for(int i=0;i<Server.ListUserOnline.size();i++){
                        NguoiDungDTO z= new NguoiDungDTO();
                        z = Server.ListUserOnline.get(i);
                        for(int j=0;j<Clients.size();j++){
                            if(Server.Clients.get(j).isAlive() && Server.Clients.get(j).PORT == z.getPORT()){
                                System.out.printf(format, z.getNd_id(),z.getName(),z.getUsername(),z.getBlockaccount()==1?"FALSE":"TRUE",z.getBlocktaode()==1?"FALSE":"TRUE",z.getBlockthi()==1?"FALSE":"TRUE");
                            }
                            
                        }
                        
                    }
                    
                    break;
                }
                case 7:{
                    System.out.println("Bạn chọn 7:");
                    System.out.println("Nhập 1: UNBLOCK, Nhập 2:BLOCK");
                    int choice = sc.nextInt();
                    if(choice ==1){
                        System.out.print("Nhập ID người dùng muốn UNBLOCK: ");
                        int id = sc.nextInt();
                        if( new DAO.ServerDAO().kiemtraNguoiDung(id)==1){
                            System.out.println("Nhập 1: UNBLOCK ACCOUNT, Nhập 2: UNBLOCK TẠO ĐỀ THI, Nhập 3: UNBLOCK THI");
                            int luachon = sc.nextInt();
                            if(luachon==1){
                                new DAO.ServerDAO().UnBlockaccount(id);
                                for(int i=0;i<Server.ListUserOnline.size();i++){
                                    if(Server.ListUserOnline.get(i).getNd_id() == id){
                                        Server.ListUserOnline.get(i).setBlockaccount(1);
                                    }
                                }
                            }else if(luachon==2){
                                new DAO.ServerDAO().UnBlocktaode(id);
                                for(int i=0;i<Server.ListUserOnline.size();i++){
                                    if(Server.ListUserOnline.get(i).getNd_id() == id){
                                        Server.ListUserOnline.get(i).setBlocktaode(1);
                                    }
                                }
                            }else if(luachon==3){
                                new DAO.ServerDAO().UnBlockthi(id);
                                for(int i=0;i<Server.ListUserOnline.size();i++){
                                    if(Server.ListUserOnline.get(i).getNd_id() == id){
                                        Server.ListUserOnline.get(i).setBlockthi(1);
                                    }
                                }
                            }else System.out.println("Lựa chọn không tồn tại...");
                        }else {
                            System.out.println("ID người dùng không tồn tại....");
                        }
                    }else if(choice ==2){
                        System.out.print("Nhập ID người dùng mốn BLOCK: ");
                        int id = sc.nextInt();
                        if( new DAO.ServerDAO().kiemtraNguoiDung(id)==1){
                            System.out.println("Nhập 1: BLOCK ACCOUNT, Nhập 2: BLOCK TẠO ĐỀ THI, Nhập 3: BLOCK THI");
                            int luachon = sc.nextInt();
                            if(luachon==1){
                                new DAO.ServerDAO().Blockaccount(id);
                                for(int i=0;i<Server.ListUserOnline.size();i++){
                                    if(Server.ListUserOnline.get(i).getNd_id()==id){
                                        Server.ListUserOnline.get(i).setBlockaccount(0);
                                        
                                        
                                        int port = Server.ListUserOnline.get(i).getPORT();
                                        for(int j=0;j<Clients.size();j++){
                                            if(Clients.get(j).PORT == port){
                                                Clients.get(j).line="BLOCKUSERLOGOUT:";
                                            }
                                        }
                                        Server.ListUserOnline.remove(Server.ListUserOnline.get(i));
                                    }
                                }
                                
                            }else if(luachon==2){
                                new DAO.ServerDAO().Blocktaode(id);
                                for(int i=0;i<Server.ListUserOnline.size();i++){
                                    if(Server.ListUserOnline.get(i).getNd_id() == id){
                                        Server.ListUserOnline.get(i).setBlocktaode(0);
                                    }
                                }
                            }else if(luachon==3){
                                new DAO.ServerDAO().Blockthi(id);
                                for(int i=0;i<Server.ListUserOnline.size();i++){
                                    if(Server.ListUserOnline.get(i).getNd_id() == id){
                                        Server.ListUserOnline.get(i).setBlockthi(0);
                                    }
                                }
                            }else System.out.println("Lựa chọn không tồn tại...");
                        }else {
                            System.out.println("ID người dùng không tồn tại....");
                        }
                    }else System.out.println("Lựa chọn không tồn tại...");
                    // kết nối
                    //System.out.println("Tổng số người đang online: "+Clients.size());
                    
                    break;
                }
                case 8:{
                     System.out.println("Bạn chọn 8");
                     String format = "%-5s%-40s%-15s%-15s%-10s%-10s%-15s%-15s%-15s%-18s%-18s%n";
                    System.out.printf(format, "ID", "TIÊU ĐỀ","THỜI GIAN","MÔN THI","SỐ CÂU","LƯỢT THI","ID NGƯỜI TẠO","TRẠNG THÁI","ĐIỂM CAO NHẤT","ĐIỂM THẤP NHẤT","ĐIỂM TRUNG BÌNH");
                    // kết nối
//                     int dem =0;
//                     for(int i=0;i<Clients.size();i++){
//                            if(Server.Clients.get(i).isAlive())
//                            dem++;
//                        }
//                    System.out.println("Tổng số kết nối: "+dem);
                     
                            ArrayList <DeThiDTO> dsdt = new ArrayList<>();
                            ArrayList <DiemDTO> dsdiem = new ArrayList<>();
                            dsdt = new ThanhTichDAO().docDSDT();
                            for (DeThiDTO dsdt1 : dsdt) {
                                float diemcaonhat = 0;
                                float diemthapnhat =11;
                                float tong = 0;
                                float diemtrungbinh = 0 ;
                                dsdiem = new ThanhTichDAO().docDSDiem(dsdt1.getDt_id());
                                for(DiemDTO dsdiem1 : dsdiem)
                                {   
                                    if(diemcaonhat < Integer.parseInt(dsdiem1.getDiem()))
                                    {
                                        diemcaonhat = Integer.parseInt(dsdiem1.getDiem());
                                    }
                                    if(diemthapnhat > Integer.parseInt(dsdiem1.getDiem()))
                                    {
                                        diemthapnhat = Integer.parseInt(dsdiem1.getDiem());
                                    }
                                    tong += Integer.parseInt(dsdiem1.getDiem());
                                }
                                diemtrungbinh = tong / dsdiem.size();
                                if(Double.isNaN(diemtrungbinh)) diemtrungbinh=0;
//                                System.out.println("Diem cao nhut: "+diemcaonhat);
//                                System.out.println("Diem trung binh: "+diemtrungbinh);
                                System.out.printf(format,dsdt1.getDt_id(), dsdt1.getTieude(),dsdt1.getThoiluong(),dsdt1.getMonthi(),dsdt1.getSocau(),dsdt1.getTongsonguoithi(),dsdt1.getNguoitao(),dsdt1.getDt_public()==1?"TRUE":"FALSE",diemcaonhat,diemthapnhat==11?0:diemthapnhat,diemtrungbinh);
                            }
                    
                    break;
                }
                case 9: {
                    System.out.println("Bạn chọn 9:");
                    // tổng số đề thi
                    DAO.MyConnection myconnection = new DAO.MyConnection();
                    Connection con = myconnection.getConnecDB();
                    Statement statement = con.createStatement();
                    String sql = "select COUNT(*) as total from DeThi";
                    ResultSet rs;
                    rs = statement.executeQuery(sql);
                    rs.next();
                    System.out.println("Tổng số đề thi trên hệ thống là: "+rs.getInt("total"));
                    break;
                }
                case 10:{
                    System.out.println("Bạn chọn 10:");
                    // chi tiết đề thi
                    DAO.MyConnection myconnection = new DAO.MyConnection();
                    Connection con = myconnection.getConnecDB();
                    Statement statement = con.createStatement();
                    String sql = "select * from DeThi";
                    ResultSet rs;
                    rs = statement.executeQuery(sql);
                    String format = "%-10s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%n";
                    System.out.printf(format, "ID", "TIÊU ĐỀ","THỜI GIAN","MÔN THI","SỐ CÂU","LƯỢT THI","ID NGƯỜI TẠO","TRẠNG THÁI");
                  
                    while(rs.next()){
                        int id = rs.getInt("dt_id");
                        String str1 = rs.getString("dt_tieude");
                        String str2 = rs.getString("dt_thoiluong");
                        String str3 = rs.getString("dt_socau");
                        String str4 = rs.getString("dt_monthi");
                        int trangthai = rs.getInt("dt_songuoithi");
                        int trangthai1 = rs.getInt("dt_nguoitao");
                        int trangthai2 = rs.getInt("dt_public");
                        System.out.printf(format, id, str1,str2,str4,str3,trangthai,trangthai1,trangthai2==1?"TRUE":"FALSE");
                    }
                  
                    break;
                }
                default:{
                    System.out.println("Dont have your option !!!");
                    break;
                }
            }
        }
        System.out.println("quit");
    }

    @Override
    public void run() {
        try {
            ServerExcute();
        } catch (IOException ex) {
            Logger.getLogger(ServerExcute.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServerExcute.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}
