import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.*;

public class WaitRoom extends JPanel {
    JTable table1, table2;
    DefaultTableModel model1, model2;
    JTextField tf;
    JTextArea ta;
    JButton b1, b2, b3, b4, b5, b6;
    JScrollBar bar;

    public WaitRoom() {
        String[] col1 = { "방이름", "공개/비공개", "인원" };
        String[][] row1 = new String[0][3];

        model1 = new DefaultTableModel(row1, col1);
        table1 = new JTable(model1);
        JScrollPane js1 = new JScrollPane(table1);

        String[] col2 = { "ID", "대화명", "성별", "위치" };
        String[][] row2 = new String[0][3];

        model2 = new DefaultTableModel(row2, col2);
        table2 = new JTable(model2);
        JScrollPane js2 = new JScrollPane(table2);

        ta = new JTextArea();
        ta.setEditable(false);
        JScrollPane js3 = new JScrollPane(ta);
        bar = js3.getVerticalScrollBar();

        tf = new JTextField();

        b1 = new JButton("방 만들기");
        b2 = new JButton("입장하기");
        b3 = new JButton("정보보기");
        b4 = new JButton("쪽지보내기");
        b5 = new JButton("귓속말 보내기");
        b6 = new JButton("나가기");

        // 배치
        setLayout(null);
        js1.setBounds(10, 15, 600, 500);
        js2.setBounds(10, 520, 600, 350);
        add(js1);
        add(js2);

        js3.setBounds(615, 15, 390, 465);
        add(js3);

        tf.setBounds(615, 480, 390, 30);
        add(tf);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3, 2, 5, 5));
        p.add(b1);//방만들기
        p.add(b2);//입장하기
        p.add(b3);//정보보내기
        p.add(b4);//쪽지보내기
        p.add(b5);//귓속말 보내기
        p.add(b6);//나가기

        p.setBounds(615, 523, 390, 350);
        add(p);
        ButtonAction();
    }

    public void ButtonAction(){
        b6.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                setVisible(false);//창 안보이게 하기
            }
        });
    }

}

