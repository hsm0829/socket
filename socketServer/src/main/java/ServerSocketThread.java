import java.io.*;
import java.net.Socket;

/**
 * 与客户端的通信线程
 */
public class ServerSocketThread extends Thread{

    private Socket socket;

    public ServerSocketThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //客户端的输出流即时服务端的输入，这里是指：服务端要读取的信息是客户端发出的消息
        InputStreamReader inputStreamReader=null;
        BufferedReader bufferedReader=null;
        PrintWriter printWriter=null;
        try{
            InputStream inputStream = socket.getInputStream();//获取socket通道的输入流，字节流
            inputStreamReader=new InputStreamReader(inputStream);//将字节流转为字符流
            bufferedReader=new BufferedReader(inputStreamReader);//在输入的字符流中读取文本
            String line=null;
            StringBuffer content=new StringBuffer();
            while ((line=bufferedReader.readLine())!=null){
                content.append(line);
            }
            System.out.println("我是服务端，我收到客户端的消息内容："+content);
            socket.shutdownInput();//单向关闭服务端的socket的输入流，客户端的输入流并不会被关闭，socket也不会被关闭
            //返回给客户端消息
            OutputStream outputStream=socket.getOutputStream();
            printWriter=new PrintWriter(outputStream);
            printWriter.write("收到收到，服务器已收到客户端发出的消息\n");
            printWriter.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                socket.close();
                inputStreamReader.close();
                bufferedReader.close();
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
