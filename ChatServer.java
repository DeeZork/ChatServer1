import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        // ������� ��������� ����� �� ����� 1234
        ServerSocket server = new ServerSocket(1234);
        while(true) {
            System.out.println("Waiting...");
            // ���� ������� �� ����
            Socket socket = server.accept();
            System.out.println("Client connected!");
            // ������� ������� �� ����� �������
            Client client = new Client(socket);
            // ��������� �����
            Thread thread = new Thread(client);
            thread.start();
        }
    }
}
