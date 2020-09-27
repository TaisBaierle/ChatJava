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

    private Socket socket;
    private Mensagem mensagem;
    private ServicosCliente servico;

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
        String login = campoLogin.getText();

        if (!login.isEmpty()) {
            mensagem = new Mensagem();
            mensagem.setComando(Comandos.CONECTADO);
            mensagem.setNomeUsuario(login);

            servico = new ServicosCliente();
            socket = servico.conectar();

            new Thread(new AguardarSocket(socket)).start();

            try {
                servico.enviar(mensagem);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }//GEN-LAST:event_entrarActionPerformed

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        Mensagem mensagem = new Mensagem();
        mensagem.setNomeUsuario(this.mensagem.getNomeUsuario());
        mensagem.setComando(Comandos.DESCONECTADO);
        try {
            this.servico.enviar(mensagem);
            desconectar();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_sairActionPerformed

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        String textoDigitado = this.areaMensagem.getText();
        String nomeUsuario = this.mensagem.getNomeUsuario();
        this.mensagem = new Mensagem();

        if (this.usuariosOnline.getSelectedIndex() > -1) {
            this.mensagem.setUsuarioMsgPrivada((String) this.usuariosOnline.getSelectedValue());
            this.mensagem.setComando(Comandos.ENVIA_PRIVADO);
            this.usuariosOnline.clearSelection();

        } else {
            this.mensagem.setComando(Comandos.ENVIA_GERAL);

        }

        if (!textoDigitado.isEmpty()) {

            this.mensagem.setNomeUsuario(nomeUsuario);
            this.mensagem.setTextoMensagem(textoDigitado);

            this.areaChat.append("Você: " + textoDigitado + "\n");

            try {
                this.servico.enviar(this.mensagem);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        this.areaMensagem.setText("");
    }//GEN-LAST:event_enviarActionPerformed

    private void arquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arquivoActionPerformed
        String nomeUsuario = this.campoLogin.getText();
        this.mensagem = new Mensagem();

        if (this.usuariosOnline.getSelectedIndex() > -1) {
            this.mensagem.setUsuarioMsgPrivada((String) this.usuariosOnline.getSelectedValue());
            this.mensagem.setComando(Comandos.ENVIA_ARQUIVO_PRIVADO);
            this.usuariosOnline.clearSelection();
        } else {
            this.mensagem.setComando(Comandos.ENVIA_ARQUIVO_GERAL);
            /*JOptionPane.showMessageDialog(this, "SELECIONE UM DESTINATÁRIO PARA O ARQUIVO", "Error Mensage", ERROR_MESSAGE);
            return;*/
        }

        this.mensagem.setNomeUsuario(nomeUsuario);

        JFileChooser fileChooser = new JFileChooser();
        int escolha = fileChooser.showOpenDialog(null);

        if (escolha == JFileChooser.APPROVE_OPTION) {

            File f = fileChooser.getSelectedFile();

            this.mensagem.setFile(f);
            this.areaChat.append("Você enviou : " + f.getName() + "\n");

            try {
                this.servico.enviar(this.mensagem);
            } catch (IOException ex) {
                ex.printStackTrace();
            }


    }//GEN-LAST:event_arquivoActionPerformed
    }
    private void limparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparActionPerformed
        this.areaMensagem.setText("");
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

    private class AguardarSocket implements Runnable {

        private ObjectInputStream in;

        public AguardarSocket(Socket socket) {
            try {
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
                            conectar(msg);
                            break;
                        case DESCONECTADO:
                            desconectar();
                            socket.close();
                            break;
                        case ENVIA_PRIVADO:
                            receberPrivado(msg);
                            break;
                        case ENVIA_GERAL:
                            break;
                        case USUARIO_ONLINE:
                            atualizarListaOnline(msg);
                            break;
                        case ENVIA_ARQUIVO_PRIVADO: {
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

        if (mensagem.getTextoMensagem().equals("NÃO CONECTOU")) {

            this.campoLogin.setText("");
            JOptionPane.showMessageDialog(this, "JÁ EXISTE UMA CONTA COM ESSE NOME", "ERROR MESSAGE", ERROR_MESSAGE);
            return;//Saida forçada do método
        }
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
