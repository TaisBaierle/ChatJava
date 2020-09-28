package cliente;

import java.io.IOException;
import java.io.ObjectOutput;
import java.net.Socket;
import compartilhado.Mensagem;
import java.io.ObjectOutputStream;

public class ServicosCliente {

    private Socket socket;
    private ObjectOutput out;

    public Socket conectar() {

        try {
            this.socket = new Socket("localhost", 5050);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            //Método conectar que instancia o socket no localhost e na porta 5050
            //E o Objeto ObjectOutputStream que escreve e serializa

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return socket;
    }

    public void enviar(Mensagem mensagem) throws IOException {
        
        out.writeObject(mensagem);//Serializa o objeto mensagem para o que mesmo sejá enviado
    }

}
