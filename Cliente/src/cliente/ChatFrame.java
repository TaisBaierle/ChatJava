package cliente;

import java.net.Socket;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import compartilhado.Mensagem;
import compartilhado.Mensagem.Comandos;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.ListSelectionModel;

public class ChatFrame extends javax.swing.JFrame {

    /*Classe que define a interface gráfica do lado do cliente*/
    private Socket socket;//Socket para receber/enviar as mensagens
    private Mensagem mensagem;//classe do tipo Mensagem, pois a mensagem é uma classe com serialização
    private ServicosCliente servico;//Classe servido que implementa o envio do objeto serializado ao servidor

    public ChatFrame() {

        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {

        }
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        usuariosOnline = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        campoLogin = new javax.swing.JTextField();
        entrar = new javax.swing.JButton();
        sair = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaChat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaMensagem = new javax.swing.JTextArea();
        enviar = new javax.swing.JButton();
        limpar = new javax.swing.JButton();
        arquivo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sala de Chat");
        setBackground(new java.awt.Color(204, 204, 255));

        jPanel4.setBackground(new java.awt.Color(255, 204, 255));

        jPanel1.setBackground(new java.awt.Color(255, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Online", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 153, 51))); // NOI18N

        usuariosOnline.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        usuariosOnline.setSelectionBackground(new java.awt.Color(102, 255, 102));
        jScrollPane3.setViewportView(usuariosOnline);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Login", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        campoLogin.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        entrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/icons/adicionar-usuario.png"))); // NOI18N
        entrar.setText("Entrar");
        entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entrarActionPerformed(evt);
            }
        });

        sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/icons/sair .png"))); // NOI18N
        sair.setText("Sair");
        sair.setEnabled(false);
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoLogin)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(campoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(entrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        areaChat.setEditable(false);
        areaChat.setColumns(20);
        areaChat.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        areaChat.setLineWrap(true);
        areaChat.setRows(5);
        areaChat.setEnabled(false);
        jScrollPane1.setViewportView(areaChat);

        areaMensagem.setColumns(20);
        areaMensagem.setLineWrap(true);
        areaMensagem.setRows(5);
        areaMensagem.setEnabled(false);
        jScrollPane2.setViewportView(areaMensagem);

        enviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/icons/enviar.png"))); // NOI18N
        enviar.setText("Enviar");
        enviar.setEnabled(false);
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });

        limpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/icons/apagar-texto.png"))); // NOI18N
        limpar.setText("Limpar");
        limpar.setEnabled(false);
        limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparActionPerformed(evt);
            }
        });

        arquivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/icons/clip.png"))); // NOI18N
        arquivo.setText("Arquivo");
        arquivo.setEnabled(false);
        arquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arquivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(arquivo, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(limpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(arquivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(limpar)
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void entrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entrarActionPerformed
        ///Implementa a ação de botão ENTRAR

        String login = campoLogin.getText();//Pega o nome no campo de login

        if (!login.isEmpty()) {//Se o login não for vazio, ou seja se tiver conteudo
            mensagem = new Mensagem();//um objeto mensagem é instanciando
            mensagem.setComando(Comandos.CONECTADO);//Esse objeto seta o comando CONECTADO. Pois na classe Mensagem
            //Existe um enum com comandos que o sistema executa
            mensagem.setNomeUsuario(login);//Pega o nome de login e armazena no objeto Mensagem

            servico = new ServicosCliente();//intancia da classe ServicosCliente
            socket = servico.conectar();//chama o método conectar, que cria o socket e o objeto ObjectOutputStream, responsável pela
            //srialização

            new Thread(new AguardarSocket(socket)).start();//Criando as threads que vão receber os retornos do servidor

            try {
                servico.enviar(mensagem);//envia a mensagem com as novas informações pegas do form com o método enviar, da classe ServicosCliente
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }//GEN-LAST:event_entrarActionPerformed

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        Mensagem mensagem = new Mensagem(); // Intancia de nova mensagem
        mensagem.setNomeUsuario(this.mensagem.getNomeUsuario());//Pega o nome do usuario do atributo da classe
        mensagem.setComando(Comandos.DESCONECTADO);//set o comando DESCONECTADO na variavel local
        try {
            this.servico.enviar(mensagem);//envia para o servidor
            desconectar();//Chama o método que faz a desconexão
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_sairActionPerformed

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        String textoDigitado = this.areaMensagem.getText();//pega o texto digitado do campo textual
        String nomeUsuario = this.mensagem.getNomeUsuario();//Pega o nome do usuário que está mandando a mensagem, quando a thread conecta, seta
        //o nome do usuário conectado
        this.mensagem = new Mensagem();  //intancia uma nova mensagem, reaproveita o atributo da classe                   

        if (this.usuariosOnline.getSelectedIndex() > -1) {//verificando a lista de usuários, pois se não tiver niguém selecionado
            //retorna -1 no método
            this.mensagem.setUsuarioMsgPrivada((String) this.usuariosOnline.getSelectedValue());//como o ato de selecionar alguém na
            //lista, siginifica o envio como mensagem privada, o nome selecionado é setado no atributo UsuarioMsgPrivada da classe Mensagem
            this.mensagem.setComando(Comandos.ENVIA_PRIVADO);//Envia o comando de ENVIA_PRIVADO
            this.usuariosOnline.clearSelection();//Limpa a seleção na lista

        } else {
            this.mensagem.setComando(Comandos.ENVIA_GERAL);//caso o valor de retorno seja -1, o envio é geral

        }

        if (!textoDigitado.isEmpty()) {//Se o texto digitado não é vazio
            //Segue executando o envio
            this.mensagem.setNomeUsuario(nomeUsuario);
            this.mensagem.setTextoMensagem(textoDigitado);
            //Pega o nome do usuário que envia a mensagem
            //Pega o texto que foi digitado

            this.areaChat.append("Você: " + textoDigitado + "\n");
            //Seta na tela a mensagem que "VOCE" enviou

            try {
                this.servico.enviar(this.mensagem);//chama o serviço de envio do cliente para enviar para o servidor
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        this.areaMensagem.setText("");//Limpa a área de mensagem para ser digitada a proxima
    }//GEN-LAST:event_enviarActionPerformed

    private void arquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arquivoActionPerformed
        String nomeUsuario = this.campoLogin.getText();//Pega o nome do usuário do campo de login
        this.mensagem = new Mensagem();//instancia um novo objeto de mensagem no atributo da classe

        if (this.usuariosOnline.getSelectedIndex() > -1) {
            this.mensagem.setUsuarioMsgPrivada((String) this.usuariosOnline.getSelectedValue());
            this.mensagem.setComando(Comandos.ENVIA_ARQUIVO_PRIVADO);
            this.usuariosOnline.clearSelection();
            //Mesmo procedimento de selecionar a mensagem privada na lista, mas dessa vez usa um comando de ENVIA_ARQUIVO_PRIVADO
        } else {
            this.mensagem.setComando(Comandos.ENVIA_ARQUIVO_GERAL);
            //Envio de arquivo em broadcast
        }

        this.mensagem.setNomeUsuario(nomeUsuario);//Seta o nome do usuário que vai enviar a mensagem no objeto novo

        JFileChooser fileChooser = new JFileChooser();
        int escolha = fileChooser.showOpenDialog(null);

        if (escolha == JFileChooser.APPROVE_OPTION) {

            File f = fileChooser.getSelectedFile();
            /*Abre um seletor de arquivos para selecionar o arquivo que vai ser enviado*/

            this.mensagem.setFile(f);//Seta o aquivo escolhido no objeto da classe mensagem
            this.areaChat.append("Você enviou : " + f.getName() + "\n");//Mostra na tela a ação feita, envio do arquivo

            try {
                this.servico.enviar(this.mensagem);//envia a mensagem contendo as informações para a classe ServicosCliente, para
                //seja enviada para o servidor
            } catch (IOException ex) {
                ex.printStackTrace();
            }


    }//GEN-LAST:event_arquivoActionPerformed
    }
    private void limparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparActionPerformed
        this.areaMensagem.setText("");//somente limpa o campo textual
    }//GEN-LAST:event_limparActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaChat;
    private javax.swing.JTextArea areaMensagem;
    private javax.swing.JButton arquivo;
    private javax.swing.JTextField campoLogin;
    private javax.swing.JButton entrar;
    private javax.swing.JButton enviar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton limpar;
    private javax.swing.JButton sair;
    private javax.swing.JList<String> usuariosOnline;
    // End of variables declaration//GEN-END:variables

    /*Classe que implementa as funcionalidades das threads clientes*/
    private class AguardarSocket implements Runnable {

        private ObjectInputStream in;//Cria um objeto ObjectInputStream que le as informações recebidas do socket

        public AguardarSocket(Socket socket) {
            try {
                this.in = new ObjectInputStream(socket.getInputStream());//instancia do objeto recebendo o socket
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() { //implementação do metodo run da thread

            Mensagem msg = null;// Novo objeto Mensagem

            try {
                while ((msg = (Mensagem) in.readObject()) != null) {//WHILE ONDE OCORRE A LEITURA E DESERIALIZAÇÃO DO OBJETO RECEBIDO, 
                    //COMO ESSA LEITURA RETORNA UM OBJECT, FOI FEITO O CAST PARA MENSAGEM
                    Comandos comandos = msg.getComando();//Pegando o comando recebido na mensagem, para definir o comportamento 
                    //Estrutura de opções baseada nas respostas dos comandos recebidos pela mensagem
                    //existe as chamadas aos repectivos métodos
                    switch (comandos) {
                        case CONECTADO:
                            conectar(msg);
                            //Se o comando for conectado, será chamado o médodo responsável por isso
                            break;
                        case DESCONECTADO:
                            desconectar();
                            socket.close();
                            //Se o comando for desconectado, será o chamado o método responsável por isso
                            //Fecha o socket
                            break;
                        case ENVIA_PRIVADO:
                            receberPrivado(msg);
                        //Se o comando for envia_privado, será o chamado o método responsável por isso
                        case USUARIO_ONLINE:
                            atualizarListaOnline(msg);
                            //Se o comando for usuarios_online, será o chamado o método responsável por isso
                            break;
                        case ENVIA_ARQUIVO_PRIVADO: {
                            //Se o comando for envia_arquivo_privado, será o chamado o método responsável por isso
                            try {
                                receberArquivo(msg);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        default:
                            break;
                    }

                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        }

    }

    private void conectar(Mensagem mensagem) {

        if (mensagem.getTextoMensagem().equals("NÃO CONECTOU")) {//retorno de um teste de conexão que é feito no servidor
            //pois no servidor é feito o teste se o nome de login ja existe na lista
            this.campoLogin.setText("");//caso isso ocorra, limpa o campo de login
            JOptionPane.showMessageDialog(this, "JÁ EXISTE UMA CONTA COM ESSE NOME", "ERROR MESSAGE", ERROR_MESSAGE);
            //chama uma mensagem de erro para o usuario
            return;//Saida forçada do método
        }
        //caso não caia no if, é feito o bloquei do campo de login e do botão
        //São habilitados os campos funcionais do chat
        this.mensagem = mensagem;
        entrar.setEnabled(false);
        campoLogin.setEnabled(false);
        sair.setEnabled(true);
        areaMensagem.setEnabled(true);
        areaChat.setEnabled(true);
        enviar.setEnabled(true);
        limpar.setEnabled(true);
        arquivo.setEnabled(true);

        JOptionPane.showMessageDialog(this, "BEM VINDO A SALA DE CHAT");

    }

    private void desconectar() throws IOException {
        //O desconectar faz  contrário, libera o campo de login e o botão
        //bloqueia as demais funcionalidades do chat
        entrar.setEnabled(true);
        campoLogin.setEditable(true);

        sair.setEnabled(false);
        areaMensagem.setEnabled(false);
        areaChat.setEditable(false);
        enviar.setEnabled(false);
        limpar.setEnabled(false);
        arquivo.setEnabled(false);
        areaChat.setText("");
        areaMensagem.setText("");

        JOptionPane.showMessageDialog(this, "VOCE DEIXOU A SALA");
    }

    private void receberPrivado(Mensagem mensagem) throws IOException {

        areaChat.append(mensagem.getNomeUsuario() + ":  " + mensagem.getTextoMensagem() + "\n");

    }

    private void atualizarListaOnline(Mensagem mensagem) {
        //System.out.println(mensagem.getUsuariosOnline().toString());
        Set<String> nomesConectados = mensagem.getUsuariosOnline();
        nomesConectados.remove((String) mensagem.getNomeUsuario());
        String[] vetorNomes = (String[]) nomesConectados.toArray(new String[nomesConectados.size()]);

        usuariosOnline.setListData(vetorNomes);
        usuariosOnline.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        usuariosOnline.setLayoutOrientation(JList.VERTICAL);

    }

    private void receberArquivo(Mensagem mensagem) throws InterruptedException {

        areaChat.append("Você recebeu um arquivo de " + mensagem.getNomeUsuario() + " : " + mensagem.getFile().getName() + "\n");

        try {
            salvaArquivo(mensagem);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();

        }

    }

    private void salvaArquivo(Mensagem mensagem) throws FileNotFoundException, IOException, InterruptedException {
        long hora = System.currentTimeMillis();
        Thread.sleep(1000);

        String path = "C:\\Users\\Tais Baierle\\Desktop\\Mensagens\\";
        File pathFile = new File(path);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        FileInputStream fileInputStream = new FileInputStream(mensagem.getFile());
        FileOutputStream fileOutputStream = new FileOutputStream(path + hora + " - "
                + mensagem.getFile().getName());

        int size = fileInputStream.available();
        byte[] arquivoBytes = new byte[size];
        fileInputStream.read(arquivoBytes);

        fileOutputStream.write(arquivoBytes);

    }

}
