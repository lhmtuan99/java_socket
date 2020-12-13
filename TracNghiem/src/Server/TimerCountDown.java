/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import GUI.MainJFrame;
import GUI.ThiThuJPanel;
import static GUI.ThiThuJPanel.comboboxThithu;
import static GUI.ThiThuJPanel.jButton2;

/**
 *
 * @author tiennguyen
 */
public class TimerCountDown implements java.lang.Runnable{
    
    public int minutes ;
    public TimerCountDown(int minutes){
        this.minutes = minutes;
    }
    @Override
    public void run() {
        this.runTimer();
    }
    public void runTimer(){
        int i = minutes;
        int check =0;
        ThiThuJPanel.timeCountDown.setText("0");
         while (i>=0 && check==0){
             check =ThiThuJPanel.nopbai;
          System.out.println("Remaining: "+i+" seconds");
          try {
            ThiThuJPanel.timeCountDown.setText(i+"");
            i--;
            Thread.sleep(1000L);    // 1000L = 1000ms = 1 second
           }
           catch (InterruptedException e) {
               //I don't think you need to do anything for your particular problem
           }
         }
         if(ThiThuJPanel.nopbai==0){
             MainJFrame.AlertMessageFromServer("Hết giờ làm bài !!!");
         ThiThuJPanel.jButton2.setEnabled(false);
         String str  = (String) comboboxThithu.getSelectedItem();
        String idDethi = str.split("-")[0];
        Client.SendToServer("KETTHUCTHITHU:"+idDethi+":");
         }
         ThiThuJPanel.timeCountDown.setText("0");
        //timer.cancel();
        
    }
}
