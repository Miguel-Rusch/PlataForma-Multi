/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

/**
 *
 * @author 182220058
 */
public class conversao {
        
    /*
    Esse método responsável por substituir a vírgula
    por ponto,caso o usuário digite errado.
    */
    public static String converterVirgulaPonto(String c){
        return c.replaceAll(",", ".");
    }//fecha método
    
    /*
    Coloca todas String em caixa alta
    */
    public static String converterTudoMai(String c){
        return c.toUpperCase();
    }//fecha método
    
     /*
    Coloca todas String em caixa baixa
    */
    public static String converterTudoMin(String c){
        return c.toLowerCase();
    }//fecha método
    
    /*
    Método que verifica se o usuário digitou apenas
    letras maiúsculas ou minúsculas
    No mínimo 6 e no máximo 50 caracteres
    */
    public static boolean verificarTexto(String c){
        return c.matches("^[a-zA-ZáÁéÉíÍóÓúÚçÇãÃõÕ ]{3,50}$");
    }//fecha método
    
    /*
    Método que verifica se o usuário digitou apenas
    número de 0 a 9 com no mínimo 6 e no máximo 8 dígitos 
    */
    public static boolean verificarNumeros(String c){
        return c.matches("^[0-9]{1,11}$");
    }//fecha método
    
    /*
    Método que verifica se o usuário digitou o @ no email
    */
    public static boolean verificarArroba(String c){
        for(char t : c.toCharArray()){
            if(t == '@'){
            return true;
            }
            
        }
        return false;
    }//fecha método
    
    /*
    Método que verifica se a senha que o
    usuário digitou é forte se não retorna forte
    */
    public static String verificarSenha(String senha){
        if(senha.length() < 6){
            return "Senha tem que ser maior que 6";
        }
        if(senha.length() > 20){
            return "Senha tem que ser menor que 20";
        }
        
    boolean achouNum = false;
    boolean achouMai = false;
    boolean achouMin = false;
    boolean achouCaracter = false;
    for (char c : senha.toCharArray()) {
         if (c >= '0' && c <= '9') {
             achouNum = true;
         } else if (c >= 'A' && c <= 'Z') {
             achouMai = true;
         } else if (c >= 'a' && c <= 'z') {
             achouMin = true;
         } else {
             achouCaracter = true;
         }
    }
    if(!achouNum){
        return "Senha tem que ter número";
    }else if(!achouMin){
        return "Senha tem que ter letras minúsculas";
    }else if(!achouMai){
        return "Senha tem que ter letras maiúsculas";
    }else if(!achouCaracter){
        return "Senha tem que ter símbolos";
    }
    
        return "";
        
    }//fecha método
    
    public static String formatacaoMenagem(String mensagem,int usuario){
        int cont = 7;
        //12 tabs
        if(usuario == 1){
        for(int i = 0;i <= cont;i++){
        mensagem ="     "+mensagem;
        }
        }else{
            
        }
        
    return mensagem;
    } 
    
    
}
