package compartilhado;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Mensagem implements Serializable {

    private String nomeUsuario;
    private String textoMensagem;
    private String usuarioMsgPrivada;
    private Set<String> usuariosOnline = new HashSet<>();
    private Comandos comando;
    private File file;
/*Implementa a mensagem em si que será enviado de forma serializada por cliente e servidor
  Carrega consigo as informaçoes de usuario que envia, usuário que recebe, arquivo, lista de users online, comandos de comportamento*/
    public enum Comandos {
        CONECTADO, DESCONECTADO, ENVIA_PRIVADO, ENVIA_GERAL, USUARIO_ONLINE, ENVIA_ARQUIVO_PRIVADO,ENVIA_ARQUIVO_GERAL;
    }//Enum com todos os comandos de comportamento do sistema

//Getters e setters
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getTextoMensagem() {
        return textoMensagem;
    }

    public void setTextoMensagem(String textoMensagem) {
        this.textoMensagem = textoMensagem;
    }

    public String getUsuarioMsgPrivada() {
        return usuarioMsgPrivada;
    }

    public void setUsuarioMsgPrivada(String usuarioMsgPrivada) {
        this.usuarioMsgPrivada = usuarioMsgPrivada;
    }

    public Set<String> getUsuariosOnline() {
        return usuariosOnline;
    }

    public void setUsuariosOnline(Set<String> usuariosOnline) {
        this.usuariosOnline = usuariosOnline;
    }

    public Comandos getComando() {
        return comando;
    }

    public void setComando(Comandos comando) {
        this.comando = comando;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

  
    

}
