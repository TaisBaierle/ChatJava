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

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return socket;
    }

    public void enviar(Mensagem mensagem) throws IOException {
        
        out.writeObject(mensagem);
    }

}
