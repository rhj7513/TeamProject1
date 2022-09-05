package Multicast;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.*;
import java.io.*;

class SenderThread extends Thread {
    Socket socket;
    String id, name, sex, pos;
    WaitRoom wr;
    PrintWriter writer;

    SenderThread(Socket socket, String name, WaitRoom wr) {
        this.socket = socket;
        this.name = name;
        this.wr = wr;
        wr.tf.addKeyListener(new keyEventHandler());
    }

    public SenderThread(Socket socket, String id, String name, String sex) {
        this.socket = socket;
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new PrintWriter(socket.getOutputStream());

            // 제일 먼저 서버로 대화명 송신한다.
            writer.println(name);
            writer.flush();

            // 키보드로 입력된 메시지를 서버로 송신
            while (true) {
                String str = reader.readLine();
                if (str.equals("bye"))
                    break;
                writer.println(str);
                writer.flush();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (Exception ignored) {
            }
        }
    }

    class keyEventHandler extends KeyAdapter {//이너클래스

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyChar() == KeyEvent.VK_ENTER) {//엔터를 눌렀을 경우
                System.out.println(wr.tf.getText());
                writer.println(wr.tf.getText());
                writer.flush();
                wr.tf.setText("");
            }
        }
    }
}