package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import compartilhado.Mensagem;
import compartilhado.Mensagem.Comandos;
import java.util.HashSet;
import java.util.Set;

public class ServicosServidor {

    private ServerSocket serverSocket;
    private Socket socket;
    private Map<String, ObjectOutputStream> map = new HashMap<>();

    public ServicosServidor() {

        try {
            serverSocket = new ServerSocket(5050);

            System.out.println("SERVER RUNNING!!");

            while (true) {
                socket = serverSocket.accept();

                new Thread(new AguardaSocket(socket)).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private class AguardaSocket implements Runnable {

        private ObjectOutputStream out;//saida de dados
        private ObjectInputStream in;//entrada de dados

        AguardaSocket(Socket socket) {
            try {
                this.out = new ObjectOutputStream(socket.getOutputStream());
                this.in = new ObjectInputStream(socket.getInputStream());

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            Mensagem msg = null;

            try {
                while ((msg = (Mensagem) in.readObject()) != null) {

                    Comandos comandos = msg.getComando();

                    switch (comandos) {
                        case CONECTADO:
                            boolean statusConexao = conectar(msg, out);
                            if (statusConexao) {
                                map.put(msg.getNomeUsuario(), out);
                                enviaListaConectados();
                            }
                            break;
                        case DESCONECTADO:
                            desconectar(msg, out);
                            enviaListaConectados();
                            return;
                        case ENVIA_GERAL:
                            enviageral(msg);
                            enviaListaConectados();
                            break;
                        case ENVIA_PRIVADO:
                            enviarPrivado(msg);
                            enviaListaConectados();
                            break;
                        case ENVIA_ARQUIVO_PRIVADO:
                            enviaArquivoPrivado(msg, out);
                        case ENVIA_ARQUIVO_GERAL:
                            enviarArquivoGeral(msg,out);
                        default:
                            break;
                    }

                }
            } catch (IOException | ClassNotFoundException ex) {
                Mensagem m = new Mensagem();
                m.setNomeUsuario(msg.getNomeUsuario());
                desconectar(m, out);
                enviaListaConectados();

                //System.out.println(msg.getNomeUsuario() + " Saiu do chat");
            }
        }

        private boolean conectar(Mensagem mensagem, ObjectOutputStream out) throws IOException {

            if (map.isEmpty()) {
                mensagem.setTextoMensagem("CONECTOU");
                enviarConexao(mensagem, out);
                return true;
            }

            if (map.containsKey(mensagem.getNomeUsuario())) {
                mensagem.setTextoMensagem("NÃO CONECTOU");
                enviarConexao(mensagem, out);
                return false;
            } else {
                mensagem.setTextoMensagem("CONECTOU");
                enviarConexao(mensagem, out);
                return true;
            }

        }

        private void desconectar(Mensagem mensagem, ObjectOutputStream out) {
            map.remove(mensagem.getNomeUsuario());

            mensagem.setTextoMensagem("saiu");

            mensagem.setComando(Comandos.ENVIA_PRIVADO);

            enviageral(mensagem);

            // System.out.println(mensagem.getNomeUsuario() + " saiu do chat.");
        }

        private void enviarPrivado(Mensagem mensagem) throws IOException {

            for (Map.Entry<String, ObjectOutputStream> key : map.entrySet()) {
                if (key.getKey().equals(mensagem.getUsuarioMsgPrivada())) {

                    key.getValue().writeObject(mensagem);

                }

            }
        }

        private void enviarConexao(Mensagem mensagem, ObjectOutputStream out) throws IOException {
            out.writeObject(mensagem);
        }

        private void enviageral(Mensagem mensagem) {

            for (Map.Entry<String, ObjectOutputStream> key : map.entrySet()) {
                if (!key.getKey().equals(mensagem.getNomeUsuario())) {
                    mensagem.setComando(Comandos.ENVIA_PRIVADO);
                    try {
                        //System.out.println(mensagem.getNomeUsuario());
                        key.getValue().writeObject(mensagem);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }

        }

        private void enviaListaConectados() {
            Set<String> listaNomes = new HashSet<>();

            for (Map.Entry<String, ObjectOutputStream> key : map.entrySet()) {
                listaNomes.add(key.getKey());
            }

            Mensagem mensagem = new Mensagem();
            mensagem.setComando(Comandos.USUARIO_ONLINE);
            mensagem.setUsuariosOnline(listaNomes);

            for (Map.Entry<String, ObjectOutputStream> key : map.entrySet()) {
                mensagem.setNomeUsuario(key.getKey());
                try {
                    //System.out.println(key.getKey());
                    key.getValue().writeObject(mensagem);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }

        }

        private void enviaArquivoPrivado(Mensagem msg, ObjectOutputStream out) throws IOException {
            //map.put(msg.getNomeUsuario(), out);
            if (msg.getFile() != null) {
                for (Map.Entry<String, ObjectOutputStream> key : map.entrySet()) {
                    if (!msg.getNomeUsuario().equals(key.getKey())) {
                        if (key.getKey().equals(msg.getUsuarioMsgPrivada())) {
                            msg.setComando(Comandos.ENVIA_ARQUIVO_PRIVADO);
                            key.getValue().writeObject(msg);

                        }
                    }

                }

            }

        }

        private void enviarArquivoGeral(Mensagem mensagem, ObjectOutputStream out) throws IOException {

            if (mensagem.getFile() != null) {
                for (Map.Entry<String, ObjectOutputStream> key : map.entrySet()) {
                    if (!mensagem.getNomeUsuario().equals(key.getKey())) {

                        mensagem.setComando(Comandos.ENVIA_ARQUIVO_PRIVADO);
                        key.getValue().writeObject(mensagem);

                    }
                }
            }
        }
    }
}
