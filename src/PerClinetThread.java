// 각 클라이언트 접속에 대해 하나씩 작동하는 스레드 클래스

package Multicast;

import java.io.*;
import java.net.*;
import java.util.*;

class PerClinetThread extends Thread {

    // ArrayList 객체를 여러 스레드가 안전하게 공유할 수 있는 동기화된 리스트로 만듭니다.
    static List<PrintWriter> list = Collections.synchronizedList(new ArrayList<PrintWriter>());
    static List<Clients> clients = Collections.synchronizedList(new ArrayList<Clients>());
    Socket socket;
    PrintWriter writer;

    PerClinetThread(Socket socket) {
        this.socket = socket;
        try {
            writer = new PrintWriter(socket.getOutputStream());
            list.add(writer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        String result = null, id = null, name = null, sex = null;

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            // 수신된 첫번째 문자열을 대화명으로 사용하기 위해 저장
            result = reader.readLine();
            StringTokenizer st = new StringTokenizer(result, "|");
            id = st.nextToken();
            name = st.nextToken();
            sex = st.nextToken();

            sendAll("#" + name + "님이 들어오셨습니다");
            clients.add(new Clients(id, name, sex, writer));
            while (true) {
                String str = reader.readLine();
                if (str == null) {
                    break;
                } else if (str.equals("toString")) {
                    sendAll(list.toString());
                }

                sendAll(name + ">" + str);  // 수신된 메시지 앞에 대화명을 붙여서 모든 클라이언트로 송신
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            list.remove(writer);
            sendAll("#" + name + "님이 나가셨습니다"); // 사용자가 채팅을 종료했다는 메시지를 모든 클라이언트로 보냅니다.
            try {
                socket.close();
            } catch (Exception ignored) {
            }
        }
    }

    // 서버에 연결되어 있는 모든 클라이언트로 똑같은 메시지를 보냅니다.
    private void sendAll(String str) {
        for (PrintWriter writer : list) {//현재 접송중인 모든 유저에게 msg 전송
            writer.println(str);
            writer.flush();
        }
    }

    private void sendOne(String str) {//한명에게 귓속말

    }
}
