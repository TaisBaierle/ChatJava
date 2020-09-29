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
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class ServicosServidor {

    private ServerSocket serverSocket;// atributo da classe, responsável por esperar as conexões com o cliente
    private Socket socket;//Que envia as respostas para o cliente
    private Map<String, ObjectOutputStream> map = new HashMap<>();//Map que lista os usuários por chave, valor

    public ServicosServidor() {

        try {
            serverSocket = new ServerSocket(5050);//Instancia do serverSocket na porta 5050

            System.out.println("SERVER RUNNING!!");

            while (true) {
                socket = serverSocket.accept();//Ouvinte de conexões

                new Thread(new AguardaSocket(socket)).start();//Dispara as threads que vão executar os comportamentos
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO EXECUTAR O SERVIDOR","ERRO MESSAGE",ERROR_MESSAGE );
        }
    }

    private class AguardaSocket implements Runnable {

        private ObjectOutputStream out;//saida de dados
        private ObjectInputStream in;//entrada de dados

        AguardaSocket(Socket socket) {
            try {
                //Passado o socket via construtor, utilizando para instanciar os objetos responsaveis por ler e escrever
                this.out = new ObjectOutputStream(socket.getOutputStream());
                this.in = new ObjectInputStream(socket.getInputStream());

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            Mensagem msg = null;//instancia de Mensage

            try {
                while ((msg = (Mensagem) in.readObject()) != null) {
                    //Fazendo a leitura do ObjectinputStream, continua no while enquanto não for vazio

                    Comandos comandos = msg.getComando();//Pega o comando vindo junto com a mensagem mandada pelo cliente

                    switch (comandos) {//switch casa para chamar os metodos de acordo com a resposta do cliente
                        case CONECTADO:
                            boolean statusConexao = conectar(msg, out);//retorna um boolean verdadeiro ou falso
                            //Conforme a resposta da conexão
                            if (statusConexao) {
                                map.put(msg.getNomeUsuario(), out);//se a conexão for feita, adiciona o user ao map
                                enviaListaConectados();//atualza a lista de conectados
                            }
                            break;
                        case DESCONECTADO:
                            desconectar(msg, out);//Desconecta o user do servidor
                            enviaListaConectados();//Atualiza a lista
                            return;
                        case ENVIA_GERAL:
                            enviageral(msg);//Envia as mensagens em broadcast
                            enviaListaConectados();//atualiza a lista de conectados
                            break;
                        case ENVIA_PRIVADO:
                            enviarPrivado(msg);//Envia as mensagem privadas
                            enviaListaConectados();//Atualiza a lista de conectados
                            break;
                        case ENVIA_ARQUIVO_PRIVADO:
                            enviaArquivoPrivado(msg, out);//envia arquivos de forma privada
                            //enviaListaConectados();//Atualiza a lista de conectados
                        case ENVIA_ARQUIVO_GERAL:
                            enviarArquivoGeral(msg, out);//envia arquivos em broadcast
                            //enviaListaConectados();////Atualiza a lista de conectados
                        default:
                            break;
                    }

                }
            } catch (IOException | ClassNotFoundException ex) {
                Mensagem m = new Mensagem();
                m.setNomeUsuario(msg.getNomeUsuario());
                desconectar(m, out);
                enviaListaConectados();
                /*Caso caia nas exceptions, vai desconectar e enviar a lista de conectados*/

                //System.out.println(msg.getNomeUsuario() + " Saiu do chat");
            }
        }

        private boolean conectar(Mensagem mensagem, ObjectOutputStream out) throws IOException {

            if (map.isEmpty()) {//Se for o primeir cliente a se conectar
                mensagem.setTextoMensagem("CONECTOU");//manda a mensagem de CONECTOU
                enviarConexao(mensagem, out);//chama o método de conexão, 
                //é como um envio de mensagem, mas serve para se estabelecer conexão
                return true;//retorna true, pois a conexão ocorreu
            }

            if (map.containsKey(mensagem.getNomeUsuario())) {//Busca na lista de users online
                //Se o nome já não existe, se existe, envia a mensagem que não se conectou
                mensagem.setTextoMensagem("NÃO CONECTOU");
                enviarConexao(mensagem, out);
                return false;//retorna falso
                //Esse trecho de código garante que não tenham dois user com o mesmo login
            } else {
                mensagem.setTextoMensagem("CONECTOU");
                enviarConexao(mensagem, out);
                return true;
                //caso não seja igual, envia a mensagem CONECTOU e retorna true
            }

        }

        private void desconectar(Mensagem mensagem, ObjectOutputStream out) {
            map.remove(mensagem.getNomeUsuario());//AO DESCONECTAR O REMOVE DA FILA O USUÁRIO

            mensagem.setTextoMensagem("saiu");//Envia a mensagem que saiu

            mensagem.setComando(Comandos.ENVIA_PRIVADO);//seta o comando envia privado

            enviageral(mensagem);//depois faz o envio geral

            // System.out.println(mensagem.getNomeUsuario() + " saiu do chat.");
        }

        private void enviarPrivado(Mensagem mensagem) throws IOException {

            for (Map.Entry<String, ObjectOutputStream> key : map.entrySet()) {
                if (key.getKey().equals(mensagem.getUsuarioMsgPrivada())) {

                    key.getValue().writeObject(mensagem);//pega o valor e envia
                    /*Busca na lista de usuários o nome do destinatário da mensagem privada
                    se encontra dentro do objeto mensagem */
 /*O map contém chava: NOME DO USER, VALOR: OBJECTOUTPUTSTREAM, que vai fazer a escrita dos dados*/

                }

            }
        }

        private void enviarConexao(Mensagem mensagem, ObjectOutputStream out) throws IOException {
            out.writeObject(mensagem);//O enviar conexão, so envia uma mensagem de conexão
        }

        private void enviageral(Mensagem mensagem) {
            /*Implementa o mesmo comportamento do envio privado, mas ao invés de ter a restrição
            de enviar somente para a pessoa destinatário, ele faz o laço enviando para todos os 
            clientes, e somente exclui si mesmo*/

            for (Map.Entry<String, ObjectOutputStream> key : map.entrySet()) {
                if (!key.getKey().equals(mensagem.getNomeUsuario())) {
                    mensagem.setComando(Comandos.ENVIA_PRIVADO);//manda um envio privado por que o envio broadcast é um envio privado para cada um
                    try {
                        //System.out.println(mensagem.getNomeUsuario());
                        key.getValue().writeObject(mensagem);//envia a mensagem
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }

        }

        private void enviaListaConectados() {
            Set<String> listaNomes = new HashSet<>();//Cria uma lista do tipo Set

            for (Map.Entry<String, ObjectOutputStream> key : map.entrySet()) {
                listaNomes.add(key.getKey());
                //Faz uma busca no Map, e adiciona as chaves na lista, lembrando que as 
                //chaves são os nomes dos users
            }

            Mensagem mensagem = new Mensagem();//Cria um objeto Mensagem
            mensagem.setComando(Comandos.USUARIO_ONLINE);//seta o comando USUARIO_ONLINE
            mensagem.setUsuariosOnline(listaNomes);//seta a lista de nomes gerados pelo servidor

            for (Map.Entry<String, ObjectOutputStream> key : map.entrySet()) {
                //Pega os nomes de usuários, que são as chaves dos Map
                mensagem.setNomeUsuario(key.getKey());
                try {
                    //System.out.println(key.getKey());
                    key.getValue().writeObject(mensagem);//envio do objeto carregando a lista de users
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }

        }

        private void enviaArquivoPrivado(Mensagem msg, ObjectOutputStream out) throws IOException {
            // map.put(msg.getNomeUsuario(), out);
            if (msg.getFile() != null) {
                for (Map.Entry<String, ObjectOutputStream> key : map.entrySet()) {
                    if (key.getKey().equals(msg.getUsuarioMsgPrivada())) {

                        key.getValue().writeObject(msg);

                        /*Mesma lógica do envio de mensagem privada, mas a difereça é que
                            só será executado quando a mensagem carregar um arquivo*/
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
                    /*Mesma lógica do envio geral, mas somente executa se a mensagem carregar
                        um arquivo*/

                }
            }
        }
    }
}

