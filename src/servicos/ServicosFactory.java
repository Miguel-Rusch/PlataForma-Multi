/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

/**
 *
 * @author 182220058
 */
public class ServicosFactory {
    
    private static HostServicos hostServicos = new HostServicos();
    
    private static JogoServicos jogoServicos = new JogoServicos();
    
    private static PostServicos postServicos = new PostServicos();
    
    private static ChatServicos chatServicos = new ChatServicos();

    public static ChatServicos getChatServicos() {
        return chatServicos;
    }

    public static void setChatServicos(ChatServicos chatServicos) {
        ServicosFactory.chatServicos = chatServicos;
    }
    
    

    public static HostServicos getHostServicos() {
        return hostServicos;
    }

    public static void setHostServicos(HostServicos hostServicos) {
        ServicosFactory.hostServicos = hostServicos;
    }

    public static JogoServicos getJogoServicos() {
        return jogoServicos;
    }

    public static void setJogoServicos(JogoServicos jogoServicos) {
        ServicosFactory.jogoServicos = jogoServicos;
    }

    public static PostServicos getPostServicos() {
        return postServicos;
    }

    public static void setPostServicos(PostServicos postServicos) {
        ServicosFactory.postServicos = postServicos;
    }
    
    
}
