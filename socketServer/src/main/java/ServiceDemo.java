import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 通信服务端
 */
public class ServiceDemo {

    public static void main(String[] args) {
        try {
            int countlink=0;
            ServerSocket serverSocket=new ServerSocket(10086);//绑定一个10086的端口
            Socket socket=null;
            while (true){
                //服务器监听，没有客户端连接，这里就会一直阻塞，直到有客户端链接，才会往下走
                socket=serverSocket.accept();//接收连接请求，创建一个socket会话
                ServerSocketThread thread=new ServerSocketThread(socket);
                thread.start();
                countlink++;
                System.out.println("连接客户数："+countlink);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
