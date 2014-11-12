/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Ezequiel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            InputStreamReader in=new InputStreamReader(System.in);
            BufferedReader br=new BufferedReader(in);

            System.out.println("Ingrese opcion (add - remove - modify - auth)");
            String a=br.readLine();
            
            if (a.equals("add")){
                System.out.println("Ingrese usuario");
                String username = br.readLine();
                System.out.println("Ingrese contraseña");
                String pass = br.readLine();
                System.out.println("Ingrese contraseña administrador");
                String admpass = br.readLine();

                String mensaje = String.format("<MESSAGE TYPE='ADD'><USERNAME>%s</USERNAME>"
                        + "<PASSWORD>%s</PASSWORD><ADM-PASS>%s</ADM-PASS></MESSAGE>",username,pass,admpass);
                
                System.out.println(mensaje);
            
                Socket socket = new Socket("192.168.0.101",8080);
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                out.println(mensaje);
                socket.close();
            }
        }
        catch(Exception e){}
    }
    
}
