/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class ChuyenManHinhController {
    private JPanel root;
    private String kindSelected = "";
    private List<DanhMuc> listItem= null;

    public ChuyenManHinhController(JPanel jpnRoot) {
        this.root = jpnRoot;
    }
    public void setView (JPanel jpnItem, JLabel jlbItem){
        kindSelected = "TrangChu";
        jpnItem.setBackground(new Color(96, 100, 191));
        jlbItem.setBackground(new Color(96, 100, 191));
        
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new ThongTinNguoiDungJPanel());
        root.validate();
        root.repaint();
    }
    
    public void setEvent(List<DanhMuc> listItem){
        this.listItem = listItem;
        for(DanhMuc item : listItem)
        {
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }
    
    class LabelEvent implements MouseListener{
        
        private JPanel node;
        private String kind;
        
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }
                
        @Override
        public void mouseClicked(MouseEvent e) {
           switch (kind){
               case "Account": node = new ThongTinNguoiDungJPanel(); break;
               case "DeThi": node = new DeThiJPanel(); break;
               case "TaoCauHoi": node = new TaoCauHoiJPanel(); break;
               case "ThiThu": node = new ThiThuJPanel(); break;
               case "ThanhTich": node = new ThanhTichJPanel(); break;
               default: break;
           }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(!kindSelected.equalsIgnoreCase(kind)){
            jpnItem.setBackground(new Color(76, 175, 80));
            jlbItem.setBackground(new Color(76, 175, 80));
            }
        }
        
    }
    
    private void setChangeBackground(String kind){
        for(DanhMuc item: listItem)
        {
            if(item.getKind().equalsIgnoreCase(kind))
            {
                item.getJpn().setBackground(new Color(96, 100, 191));
                item.getJlb().setBackground(new Color(96, 100, 191));
            }else 
                {
                    item.getJpn().setBackground(new Color(76, 175, 80));
                    item.getJlb().setBackground(new Color(76, 175, 80));
                }
        }
    }
}
