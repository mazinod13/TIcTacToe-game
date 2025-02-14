import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;

class mygame extends JFrame implements ActionListener{
    private JLabel heading,clockLabel;
    private JPanel panel;
    boolean gameOver=false;
    FontUIResource font = new FontUIResource("", FontUIResource.BOLD, 35);
    JButton[] btns = new JButton[9];
    int winner = 2;

//game instance variable
    int gameChances[] = {2,2,2,2,2,2,2,2,2};
    int activePlayer=0;
    int wps[][]={
        {0,1,2},
        {3,4,5},
        {6,7,8},
        {0,3,6},
        {1,4,7},
        {2,5,8},
        {0,4,8},
        {2,4,6}
    };
    mygame(){
        super.setTitle("TIC TAC TOE");
        super.setLocation(400, 70);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(650, 650);
        this.createGUI();
        super.setVisible(true);
        ImageIcon icon = new ImageIcon("images\\icon.png");
        super.setIconImage(icon.getImage());
        
    }

    public void createGUI(){

        this.getContentPane().setBackground(Color.black);    
        this.setLayout(new BorderLayout());

        heading = new JLabel();
        heading.setText("TIC TAC TOE");
       // heading.setIcon(new ImageIcon("images\\icon.png"));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setFont(font);
        heading.setForeground(Color.yellow);
        this.add(heading,BorderLayout.NORTH);

        clockLabel = new JLabel("CLOCK");
        clockLabel.setFont(font);
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        clockLabel.setForeground(Color.yellow);       
        this.add(clockLabel,BorderLayout.SOUTH);

        Thread t = new Thread(){
            public void run(){
                try {
                    while (true) {
                        String datetime = new Date().toLocaleString();
                        clockLabel.setText(datetime);
                        Thread.sleep(1000);
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

        

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        for(int i=1; i<=9; i++){
            JButton btn = new JButton();
            btn.setIcon(new ImageIcon(""));
            btn.setFont(font);
            btn.setBackground(Color.black);
            btns[i-1]=btn;
            panel.add(btn);
            btn.addActionListener(this);
            btn.setName(String.valueOf(i-1));
        }
        this.add(panel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentButton = (JButton)e.getSource();
        String nameStr= currentButton.getName();
        int name= Integer.parseInt(nameStr.trim());

        if (gameOver) {
            JOptionPane.showMessageDialog(null,"GAME OVER......xXxXxXx.......");
            return;
            
        }
        if (gameChances[name]==2) {
            if (activePlayer==1) {
                currentButton.setIcon(new ImageIcon("images\\sdsad.PNG"));
                gameChances[name]=activePlayer;
                activePlayer=0;
            }else{
                currentButton.setIcon(new ImageIcon("images\\asd.PNG"));
                gameChances[name]=activePlayer;
                activePlayer=1;
            }
            for (int []temp : wps) {
                if((gameChances[temp[0]]==gameChances[temp[1]]&&gameChances[temp[1]]==gameChances[temp[2]] && gameChances[temp[2]]!=2)){
                    winner=gameChances[temp[0]];
                    gameOver=true;
                    JOptionPane.showMessageDialog(null,"player"+winner+"is the winner"); 
                    int i = JOptionPane.showConfirmDialog(this,"do you want to play more");
                        if (i==0) {
                        this.setVisible(false);
                            new mygame();
            
                                }if (i==1) {
            
                                     System.exit(42848);
                                                        }else {
            
                                            }
                System.out.println(i);
                break;
            }

        }  
        //draw logic
        int c= 0;
        for(int x:gameChances){
            if(x==2)
            {
                c++;
                break;
            }
        } 
        if(c==0 && gameOver==false)   {
            JOptionPane.showMessageDialog(null, "Its Draw");
                    int i = JOptionPane.showConfirmDialog(this,"do you want to play more?");
                        if (i==0) {
                        this.setVisible(false);
                            new mygame();
            
                                }if (i==1) {
            
                                     System.exit(42848);
                                                        }else {
            
                                            }

        }

    }else{
        JOptionPane.showMessageDialog(this,"Position Occupied");
    }
}
    }

