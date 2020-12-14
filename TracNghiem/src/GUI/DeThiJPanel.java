/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.DeThiBUS;
import static BUS.DeThiBUS.dsdt;
import DTO.DeThiDTO;
import Server.Client;
import static Server.Client.SendToServer;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static javax.swing.JOptionPane.showMessageDialog;
/**
 *
 * @author Admin
 */
public class DeThiJPanel extends javax.swing.JPanel {
    ArrayList<DeThiDTO> dsdt = new ArrayList<>();
    DefaultTableModel model = new DefaultTableModel();
    public int socauselected =0;
    public int Thied =0;
    /**
     * Creates new form TrangChuJPanel
     */
    
//    public void doc()
//    {
//        
//        DeThiBUS bus = new DeThiBUS();
//                if(DeThiBUS.dsdt== null) bus.docDSDT();
//        Vector header = new Vector ();
//        header.add("ID");
//        header.add("TIÊU ĐỀ");
//        header.add("MÔN THI");
//        header.add("THỜI LƯỢNG");
//        header.add("SỐ CÂU");
//        model = new DefaultTableModel(header,0);
//        for(DeThiDTO dt: DeThiBUS.dsdt)
//        {
//            Vector row  = new Vector();
//            row.add(dt.getDt_id());
//            row.add(dt.getTieude());
//            row.add(dt.getMonthi());
//            row.add(dt.getThoiluong());
//            row.add(dt.getSocau());
//            model.addRow(row);
//            dsdt.add(dt);
//        }
//        jTable1.setModel(model); 
//    }
    public DeThiJPanel() {
        initComponents();
        SendToServer("LOAD:DETHI:");
        lbid.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txttieude = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtmonthi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtthoiluong = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtsocau = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        lbid = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Tạo đề thi");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Tiêu đề: ");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Môn thi: ");

        txtmonthi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmonthiActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Thời gian (phút)");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Số câu:");

        btnThem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/baseline_add_circle_outline_white_18dp.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/outline_build_white_24dp.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaMouseClicked(evt);
            }
        });
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/outline_delete_forever_white_24dp.png"))); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
        });
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txttieude, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                            .addComponent(txtmonthi))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel4)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtthoiluong, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(txtsocau))
                        .addGap(0, 67, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txttieude, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtthoiluong, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(txtmonthi)
                    .addComponent(txtsocau, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbid))
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "TIÊU ĐỀ", "MÔN THI", "THỜI LƯỢNG", "SỐ CÂU"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
//        DefaultTableModel tbmodel = (DefaultTableModel) jTable1.getModel();
//        int rowCount = jTable1.getRowCount();
//        int tt = (int) jTable1.getValueAt(rowCount-1, 0);
//
//        DeThiDTO dt = new DeThiDTO();
//        dt.setDt_id(tt+1);
//        dt.setTieude(txttieude.getText());
//        if(txttieude.getText().length()<1) {
//            showMessageDialog(null, "Tiêu đề không được để trống !!!");
//            return;
//        }
//        //dt.setMonthi(txtmonthi.getText());
//        if(txtmonthi.getText().length()<1) {
//            showMessageDialog(null, "Môn thi không được để trống !!!");
//            return;
//        }
//        //dt.setSocau(txtsocau.getText());
//        if(txtsocau.getText().length()<1) {
//            showMessageDialog(null, "Số câu không được để trống !!!");
//            return;
//        }
//        //dt.setThoiluong(txtthoiluong.getText());
//        if(txtthoiluong.getText().length()<1) {
//            showMessageDialog(null, "Thời gian thi không được để trống !!!");
//            return;
//        }
//        //showMessageDialog(null, "Tạo đề thi thành công...");
//        
//        SendToServer("DETHI:THEM:"+txttieude.getText()+":"+txtmonthi.getText()+":"+txtsocau.getText()+":"+txtthoiluong.getText()+":");
//            txttieude.setText("");
//            txtsocau.setText("");
//            txtmonthi.setText("");
//            txtthoiluong.setText("");
//            lbid.setText("");
//        DeThiBUS bus = new DeThiBUS();
//        bus.them(dt);
//        Vector row = new Vector();
//        row.add(tt+1);
//        row.add(dt.getTieude());
//        row.add(dt.getMonthi());
//        row.add(dt.getThoiluong());
//        row.add(dt.getSocau());
//        dsdt.add(dt);
//        
//        model.addRow(row);
    }//GEN-LAST:event_btnThemMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
                if(txttieude.getText().length()<1) {
            showMessageDialog(null, "Tiêu đề không được để trống !!!");
            return;
        }
        //dt.setMonthi(txtmonthi.getText());
        if(txtmonthi.getText().length()<1) {
            showMessageDialog(null, "Môn thi không được để trống !!!");
            return;
        }
        //dt.setSocau(txtsocau.getText());
        if(txtsocau.getText().length()<1) {
            showMessageDialog(null, "Số câu không được để trống !!!");
            return;
        }
        //dt.setThoiluong(txtthoiluong.getText());
        if(txtthoiluong.getText().length()<1) {
            showMessageDialog(null, "Thời gian thi không được để trống !!!");
            return;
        }
        //showMessageDialog(null, "Tạo đề thi thành công...");
        
        SendToServer("DETHI:THEM:"+txttieude.getText()+":"+txtmonthi.getText()+":"+txtsocau.getText()+":"+txtthoiluong.getText()+":");
            txttieude.setText("");
            txtsocau.setText("");
            txtmonthi.setText("");
            txtthoiluong.setText("");
            lbid.setText("");
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseClicked
        
//        int i =lbid.getText().length();
//        int j= jTable1.getSelectedRow();
//        if(i>0)
//        {
//            if(txttieude.getText().length()<1){
//                showMessageDialog(null, "Tiêu đề không được để trống !!!");
//                return;
//            }
//            if(txtmonthi.getText().length()<1){
//                showMessageDialog(null, "Môn thi không được để trống !!!");
//                return;
//            }
//            if(txtthoiluong.getText().length()<1){
//                showMessageDialog(null, "Thời gian thi không được để trống !!!");
//                return;
//            }
//            if(txtsocau.getText().length()<1){
//                showMessageDialog(null, "Số câu không được để trống !!!");
//                return;
//            }
//            int getUsed = Integer.parseInt((String) jTable1.getValueAt(i,5));
//            if(getUsed>0 ) {
//                showMessageDialog(null, "Đề đã có người thi, không thể chỉnh sửa !!!");
//                return;
//            }
//            int socauhoi =Integer.parseInt((String) jTable1.getValueAt(j,3));
//            if(socauhoi!= Integer.parseInt(txtsocau.getText())){
//                showMessageDialog(null, "Đề đã tạo không thể thay đổi số câu hỏi !!!");
//                return;
//            }
//            txttieude.setText("");
//            txtsocau.setText("");
//            txtmonthi.setText("");
//            txtthoiluong.setText("");
//            lbid.setText("");
//            SendToServer("DETHI:SUA:"+lbid.getText()+":"+txttieude.getText()+":"+txtmonthi.getText()+":"+txtsocau.getText()+":"+txtthoiluong.getText()+":");
//            //jTable1.getSelectedRow() = -1;
////            DeThiBUS bus  = new DeThiBUS();
////            DeThiDTO dethi1 = new DeThiDTO();
////            dethi1 = dsdt.get(i);
////            DeThiDTO dethi = new DeThiDTO();
////            dethi.setDt_id(Integer.parseInt(lbid.getText()));
////            dethi.setTieude(txttieude.getText());
////            dethi.setThoiluong(txtthoiluong.getText());
////            dethi.setMonthi(txtmonthi.getText());
////            dethi.setSocau(txtsocau.getText());
////
////            bus.sua(Long.toString(dethi1.getDt_id()), dethi);
////
////            DeThiDTO old = dsdt.set(i,dethi);
////            model.setValueAt(dethi.getDt_id(),i,0);
////            model.setValueAt(dethi.getTieude(), i, 1);
////            model.setValueAt(dethi.getMonthi(),i,2);
////            model.setValueAt(dethi.getThoiluong(), i, 3);
////            model.setValueAt(dethi.getSocau(),i,4);
////
////            jTable1.setModel(model);
////            showMessageDialog(null, "Cập nhật thành công...");
//        }else{
//            showMessageDialog(null, "Vui lòng chọn đề muốn sửa");
//        }
    }//GEN-LAST:event_btnSuaMouseClicked

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        
//        int selected = -1;
//        selected = jTable1.getSelectedRow();
//        if(selected == -1){
//            showMessageDialog(null,"Vui lòng chọn đề cần xóa !!!");
//        }else {
//            int xet=JOptionPane.showConfirmDialog(null,"Bạn có muốn xóa");
//       
//        if(xet==0)
//        {
//            int i= Integer.parseInt(lbid.getText());
//            Client.SendToServer("DETHI:XOA:"+i);
//            txttieude.setText("");
//            txtsocau.setText("");
//            txtmonthi.setText("");
//            txtthoiluong.setText("");
//            lbid.setText("");
////            DeThiDTO dethi = new DeThiDTO();
////            dethi = dsdt.get(i);
////            DeThiBUS bus = new DeThiBUS();
////            bus.xoa(dethi);
////            if(i>=0)
////            {
////                model.removeRow(i);
////                jTable1.setModel(model);
////                //                doc();
////                dsdt.remove(i);
////                showMessageDialog(null, "Xóa thành công...");
////            }
//        }
//        }

    }//GEN-LAST:event_btnXoaMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int i= jTable1.getSelectedRow();
        if(i>=0)
        {
            lbid.setText((String) jTable1.getValueAt(i, 0));
//            DeThiDTO dt = new DeThiDTO();
//            dt = dsdt.get(i);
            txttieude.setText((String) jTable1.getValueAt(i, 1));
            txtmonthi.setText((String) jTable1.getValueAt(i, 2));
            txtsocau.setText((String) jTable1.getValueAt(i, 3));
            txtthoiluong.setText((String) jTable1.getValueAt(i, 4));
            socauselected = Integer.parseInt(txtsocau.getText().trim());
            Thied = Integer.parseInt((String) jTable1.getValueAt(i, 5));
//            lbid.setText(Long.toString(dt.getDt_id()));
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtmonthiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmonthiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmonthiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
         int i =lbid.getText().length();
        int j= jTable1.getSelectedRow();
        if(i>0)
        {
            if(txttieude.getText().length()<1){
                showMessageDialog(null, "Tiêu đề không được để trống !!!");
                return;
            }
            if(txtmonthi.getText().length()<1){
                showMessageDialog(null, "Môn thi không được để trống !!!");
                return;
            }
            if(txtthoiluong.getText().length()<1){
                showMessageDialog(null, "Thời gian thi không được để trống !!!");
                return;
            }
            if(txtsocau.getText().length()<1){
                showMessageDialog(null, "Số câu không được để trống !!!");
                return;
            }
            int getUsed = Thied;
            if(getUsed>0 ) {
                showMessageDialog(null, "Đề đã có người thi, không thể chỉnh sửa !!!"+Thied);
                return;
                
            }else{
                int socauhoi = Integer.parseInt(txtsocau.getText());
                if(socauhoi!= socauselected){
                    showMessageDialog(null, "Đề đã tạo không thể thay đổi số câu hỏi !!!"+socauselected);
                    return;
                }
            }
            
            SendToServer("DETHI:SUA:"+lbid.getText().trim()+":"+txttieude.getText().trim()+":"+txtmonthi.getText().trim()+":"+txtsocau.getText().trim()+":"+txtthoiluong.getText().trim()+":");
             txttieude.setText("");
            txtsocau.setText("");
            txtmonthi.setText("");
            txtthoiluong.setText("");
            lbid.setText("");
            //jTable1.getSelectedRow() = -1;
//            DeThiBUS bus  = new DeThiBUS();
//            DeThiDTO dethi1 = new DeThiDTO();
//            dethi1 = dsdt.get(i);
//            DeThiDTO dethi = new DeThiDTO();
//            dethi.setDt_id(Integer.parseInt(lbid.getText()));
//            dethi.setTieude(txttieude.getText());
//            dethi.setThoiluong(txtthoiluong.getText());
//            dethi.setMonthi(txtmonthi.getText());
//            dethi.setSocau(txtsocau.getText());
//
//            bus.sua(Long.toString(dethi1.getDt_id()), dethi);
//
//            DeThiDTO old = dsdt.set(i,dethi);
//            model.setValueAt(dethi.getDt_id(),i,0);
//            model.setValueAt(dethi.getTieude(), i, 1);
//            model.setValueAt(dethi.getMonthi(),i,2);
//            model.setValueAt(dethi.getThoiluong(), i, 3);
//            model.setValueAt(dethi.getSocau(),i,4);
//
//            jTable1.setModel(model);
//            showMessageDialog(null, "Cập nhật thành công...");
        }else{
            showMessageDialog(null, "Vui lòng chọn đề muốn sửa");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
         int selected = -1;
        selected = jTable1.getSelectedRow();
        if(selected == -1 || lbid.getText().length()==0){
            showMessageDialog(null,"Vui lòng chọn đề cần xóa !!!");
        }else {
            int xet=JOptionPane.showConfirmDialog(null,"Bạn có muốn xóa");
       
        if(xet==0)
        {
            int i= Integer.parseInt(lbid.getText());
            Client.SendToServer("DETHI:XOA:"+i);
            txttieude.setText("");
            txtsocau.setText("");
            txtmonthi.setText("");
            txtthoiluong.setText("");
            lbid.setText("");
//            DeThiDTO dethi = new DeThiDTO();
//            dethi = dsdt.get(i);
//            DeThiBUS bus = new DeThiBUS();
//            bus.xoa(dethi);
//            if(i>=0)
//            {
//                model.removeRow(i);
//                jTable1.setModel(model);
//                //                doc();
//                dsdt.remove(i);
//                showMessageDialog(null, "Xóa thành công...");
//            }
        }
        }
    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    private javax.swing.JLabel lbid;
    private javax.swing.JTextField txtmonthi;
    private javax.swing.JTextField txtsocau;
    private javax.swing.JTextField txtthoiluong;
    private javax.swing.JTextField txttieude;
    // End of variables declaration//GEN-END:variables
}
