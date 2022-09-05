package Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

class ClientExample4 extends JFrame implements ActionListener {
    CardLayout card = new CardLayout();
    Login login = new Login();
    WaitRoom wr = new WaitRoom();

    public ClientExample4() {
        setLayout(card);
        add("LOGIN", login);
        add("WR", wr);
        setSize(1024, 950);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        login.b1.addActionListener(this);
        login.b2.addActionListener(this);
        wr.b3.addActionListener(this);//waitRoom에서 차단하기 버튼
        wr.b6.addActionListener(this);//waitRoom에서 나가기 버튼
        wr.tf.addActionListener(this);
    }

    public static void main(String[] args) {
        new ClientExample4();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login.b1) {
            String id = login.tf.getText();
            String name = login.pf.getText();
            String sex = "";
            if (login.rb1.isSelected())
                sex = "남자";
            else
                sex = "여자";
            connection(id, name, sex);
        }

        //종료 버튼 추가
        if(e.getSource() == login.b2){
            System.exit(0);
        }

    }

    private void connection(String id, String name, String sex) {
        try {
            // 서버와 연결
            Socket socket = new Socket("192.168.0.90", 9002);

            // 메시지 송신 쓰레드와 수신 쓰레드 생성해서 시작
            Thread thread1 = new SenderThread(socket, name, wr);
            Thread thread2 = new ReceiverThread(socket, wr);

            thread1.start();
            thread2.start();
            card.show(getContentPane(), "WR");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}