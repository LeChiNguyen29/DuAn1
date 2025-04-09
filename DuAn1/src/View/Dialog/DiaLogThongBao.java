/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author lcinu
 */
public class DiaLogThongBao {
    
    private int width = 350;
    private int height = 100;
    
    private int khoangCach = 100;
    private int time = 1000;
    
    public void thongBao(String thongBao) {
        
        JWindow window = new JWindow();
        window.setLayout(new BorderLayout());
        
        JLabel label = new JLabel(thongBao, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setPreferredSize(new Dimension(width, height));
        
        label.setOpaque(true);
        label.setBackground(new Color(0,204,153)); 
        label.setForeground(Color.WHITE);
        
        window.add(label, BorderLayout.CENTER);
        
        window.pack();
        Dimension sizeForm = Toolkit.getDefaultToolkit().getScreenSize();
        int x = sizeForm.width - 20 - window.getWidth();
        int y = 10;
        window.setLocation(x, y);
        
        window.setVisible(true);
        
        Timer timer = new Timer(10, new ActionListener() {
            private long startTime = System.currentTimeMillis();
            private int saveY = y;
            
            @Override
            public void actionPerformed(ActionEvent e) {
            
                long elapsedTime = System.currentTimeMillis() - startTime;
                double moreTime = (double) elapsedTime / time;
                
                if (moreTime < 1) {
                    
                    int newY = saveY + (int) (khoangCach * moreTime);
                    window.setLocation(x, newY);
                } else {
                    ((Timer) e.getSource()).stop();
                    
                    Timer endTime = new Timer(1000, new ActionListener() {
                        
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            
                            window.dispose();
                        } 
                    });
                    endTime.setRepeats(false);
                    endTime.start();
                }
            }
        });
        timer.start();
    }
}
