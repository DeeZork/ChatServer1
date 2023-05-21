import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {
    Socket socket;
    Scanner in;
    PrintStream out;
    ChatServer server;
    int id;

    public Client(Socket socket, ChatServer server, int id) {
        this.socket = socket;
        this.server = server;
        this.id = id;
        // ��������� �����
        new Thread(this).start();
    }

    void receive(int id, String message) {
        out.println(id+": "+message);
    }

    @Override
    public void run() {
        try {
            // �������� ������ ����� � ������
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            // ������� ������� �������� ����� � ������
            in = new Scanner(is);
            out = new PrintStream(os);

            // ������ �� ���� � ����� � ����
            out.println("Welcome to Chat, your id - "+id);
            String input = in.nextLine();
            while (!input.equals("bye")) {
                server.sendAll(input, id);
                input = in.nextLine();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
