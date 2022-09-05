import javax.swing.*;
import java.awt.*;

public class Login extends JPanel{
    private Image back;
    private JLabel la1,la2,la3;
    JTextField tf;       //default로 잡힌 이유는 다른 클래스에서 사용해야 하기 때문에
    JTextField pf;   //default로 잡힌 이유는 다른 클래스에서 사용해야 하기 때문에
    JButton b1,b2;      //default로 잡힌 이유는 다른 클래스에서 사용해야 하기 때문에
    JRadioButton rb1, rb2;

    public Login()
    {
        //이미지 정보 읽기
        back=Toolkit.getDefaultToolkit().getImage("c:\\javaDev\\back.png");
        setLayout(null); //직접 배치

        //로그인 부분 아이디와 아이디 칠 textField
        la1=new JLabel("아이디",JLabel.RIGHT);
        la1.setBounds(745, 730, 80, 30);
        tf=new JTextField();
        tf.setBounds(830, 730, 150, 30);
        add(la1); add(tf);

        //로그인 부분 비밀번호와 비밀번호 칠 textField
        la2=new JLabel("대화명",JLabel.RIGHT);
        la2.setBounds(745, 765, 80, 30);
        pf=new JTextField();
        pf.setBounds(830, 765, 150, 30);
        add(la2); add(pf);

        rb1=new JRadioButton("남자");
        rb2=new JRadioButton("여자");
        ButtonGroup bg=new ButtonGroup();
        bg.add(rb1); bg.add(rb2);
        rb1.setSelected(true);
        la3=new JLabel("성별",JLabel.RIGHT);
        la3.setBounds(745, 800, 80, 30);
        rb1.setBounds(830, 800, 70, 30);
        rb2.setBounds(910, 800, 70, 30);
        rb1.setOpaque(false);
        rb2.setOpaque(false);
        add(la3); add(rb1); add(rb2);

        b1 = new JButton("로그인");
        b2 = new JButton("취   소");
        JPanel p = new JPanel(); //패널을 배치하는 이유, 가운데 맞추기 어려워서 패널로 잡아준다
        p.add(b1);
        p.add(b2);
        p.setOpaque(false); // setOpaque -투명모드
        p.setBounds(740, 850, 235, 35);
        add(p);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
    }

    public static void main(String[] args) {

    }
}
