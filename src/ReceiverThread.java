package Multicast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class ReceiverThread extends Thread {
    Socket socket;
    WaitRoom wr;

    ReceiverThread(Socket socket, WaitRoom wr) {
        this.socket = socket;
        this.wr = wr;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {

                //서버로부터 수신된 메시지를 모니터로 출력
                String str = reader.readLine();
                wr.ta.append(str + "\n");
                if (str == null)
                    break;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
