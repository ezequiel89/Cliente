/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.Socket;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

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
            
            System.out.println("Ingrese opcion (add - remove - modify - auth - listusr - listauth - exit)");
            String a=br.readLine();
            String mensaje = "";
            while (!a.equals("exit")) {                
            
                if (a.equals("add")){
                    System.out.println("Ingrese usuario");
                    String username = br.readLine();
                    System.out.println("Ingrese contraseña");
                    String pass = br.readLine();
                    System.out.println("Ingrese contraseña administrador");
                    String admpass = br.readLine();

                    mensaje = String.format("<MESSAGE TYPE='ADD'><USERNAME>%s</USERNAME>"
                            + "<PASSWORD>%s</PASSWORD><ADM-PASS>%s</ADM-PASS></MESSAGE>",username,pass,admpass);
                }
                if (a.equals("remove")){
                    System.out.println("Ingrese usuario");
                    String username = br.readLine();
                    System.out.println("Ingrese contraseña administrador");
                    String admpass = br.readLine();

                    mensaje = String.format("<MESSAGE TYPE='REMOVE'><USERNAME>%s</USERNAME>"
                            + "<ADM-PASS>%s</ADM-PASS></MESSAGE>",username,admpass);
                }
                if (a.equals("modify")){
                    System.out.println("Ingrese usuario");
                    String username = br.readLine();
                    System.out.println("Ingrese contraseña");
                    String pass = br.readLine();
                    System.out.println("Ingrese nueva contraseña");
                    String newpass = br.readLine();

                    mensaje = String.format("<MESSAGE TYPE='MODIFY'><USERNAME>%s</USERNAME><PASSWORD>%s</PASSWORD>"
                            + "<NEW-PASS>%s</NEW-PASS></MESSAGE>",username,pass,newpass);
                }
                if (a.equals("auth")){
                    System.out.println("Ingrese usuario");
                    String username = br.readLine();
                    System.out.println("Ingrese contraseña");
                    String pass = br.readLine();                

                    mensaje = String.format("<MESSAGE TYPE='AUTHENTICATE'><USERNAME>%s</USERNAME>"
                            + "<PASSWORD>%s</PASSWORD></MESSAGE>",username,pass);
                }
                if (a.equals("listusr")){
                    System.out.println("Ingrese contraseña administrador");
                    String admpass = br.readLine();

                    mensaje = String.format("<MESSAGE TYPE='LIST-USERS'><ADM-PASS>%s</ADM-PASS></MESSAGE>",admpass);
                }

                if (a.equals("listauth")){
                    System.out.println("Ingrese usuario");
                    String username = br.readLine();
                    System.out.println("Ingrese contraseña administrador");
                    String admpass = br.readLine();

                    mensaje = String.format("<MESSAGE TYPE='LIST-AUT'><USERNAME>%s</USERNAME>"
                            + "<ADM-PASS>%s</ADM-PASS></MESSAGE>",username,admpass);
                }
                for (int i = 0; i < 1; i++) {

                    System.out.println(mensaje);
                    Socket socket = new Socket("localhost",8080);
                    PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                    out.println(mensaje);

                    //Avisa al socket que finalizo el envio
                    socket.shutdownOutput();

                    BufferedReader inn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println(inn.readLine());

                    /*
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    StringReader sr = new StringReader(inn.readLine());
                    InputSource is = new InputSource(sr);
                    Document document = builder.parse(is);
                    XPath xPath = XPathFactory.newInstance().newXPath();
                    String tipo = null;
                    try {
                        tipo = document.getDocumentElement().getAttributes().getNamedItem("STATUS").getNodeValue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (tipo.equals("OK")){
                        System.out.println("Proceso realizado con exito");
                    }
                    if(tipo.equals("ERROR")){
                        String admpass = (String)xPath.evaluate("//ACK[@STATUS='ERROR']/DESC/text()",
                        document.getDocumentElement(),
                        XPathConstants.STRING);
                        System.out.println("Error");
                    }


                    System.out.println("TIPO: "+ tipo);
                    */

                    socket.close();
                }
                System.out.println("Ingrese opcion (add - remove - modify - auth - listusr - listauth - exit)");
                a=br.readLine();
                mensaje = "";
            }
        }
        catch(Exception e){}
    }    
}
