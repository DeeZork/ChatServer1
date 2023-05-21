import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    ArrayList<Client> clients=new ArrayList<>();
    ServerSocket serverSocket;
    ChatServer() throws IOException {
        // ������� ��������� ����� �� ����� 1234
        serverSocket = new ServerSocket(1234);
    }
    public void run(){
        while(true) {
            System.out.println("Waiting...");
            Socket socket = null;
            try {
                // ���� ������� �� ����
                socket = serverSocket.accept();
                System.out.println("Client connected!");
                // ������� ������� �� ����� �������
                clients.add(new Client(socket));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args)  {
        try {
            new ChatServer().run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
