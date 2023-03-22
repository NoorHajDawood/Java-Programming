package il.ac.shenkar.java.samples.networking;

import java.io.*;
import java.net.Socket;

public class SimpleClient {

    public static void main(String args[]){
        if(args.length==0){
            System.out.println("You should pass data over your nickname");
        }else{
            String nickname=args[0];
            Socket socket=null; //אתמול בנאל כדי שבבלוק פינלי יהיה אפשר לגשת אליו לסגור אותו
            InputStream is=null;
            OutputStream os=null;
            DataInputStream dis=null;
            DataOutputStream dos=null;

            //System.out.println("nickname="+nickname);
            try{
                System.out.println("Try to create connection ");
                socket=new Socket("127.0.0.1",1300);
                System.out.println("Connection wat created (socket object) ");

                is=socket.getInputStream();
                os=socket.getOutputStream();
                dis=new DataInputStream(is);
                dos=new DataOutputStream(os);

                dos.writeUTF(nickname);

                System.out.println("nickname was sent successfully");

                String text= dis.readUTF();
                System.out.println(text +"was received");

            }catch(IOException e){
                e.printStackTrace();

            }finally {
                if(socket!=null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
