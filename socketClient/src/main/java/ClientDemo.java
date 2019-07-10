import java.io.*;
import java.net.Socket;

/**
 * 请求通讯客户端
 */
public class ClientDemo {

    public static void main(String[] args) {
        Socket socket=null;//创建一个和服务端链接的socket会话
        OutputStream outputStream=null;
        PrintWriter printWriter=null;
        InputStream inputStream=null;
        InputStreamReader inputStreamReader=null;
        BufferedReader reader=null;
        try{
            socket=new Socket("localhost",10086);//当前会话进程的ip和端口
            //发送消息
            outputStream=socket.getOutputStream();//获取socket通道的输出流
            printWriter=new PrintWriter(outputStream);
            printWriter.write("你好，我是客户端，我请求发送消息\n");
            printWriter.flush();
            socket.shutdownOutput();//单向关闭客户端的socket的输出流，服务端的输出流并不会被关闭，socket也不会被关闭
            //获取服务端返回的消息
            inputStream=socket.getInputStream();
            inputStreamReader=new InputStreamReader(inputStream);
            reader=new BufferedReader(inputStreamReader);
            String line=null;
            StringBuffer content=new StringBuffer();
            while ((line=reader.readLine())!=null){
                content.append(line);
            }
            System.out.println("我是客户端：我收到服务端返回的消息内容："+content);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                socket.close();
                outputStream.close();
                printWriter.close();
                inputStream.close();
                inputStreamReader.close();
                reader.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
