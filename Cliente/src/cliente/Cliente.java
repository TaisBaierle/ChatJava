package cliente;

public class Cliente {

    public static void main(String args[]) {
        /*Inicia a tela do chatFrame*/

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatFrame().setVisible(true);
            }
        });
    }

}
