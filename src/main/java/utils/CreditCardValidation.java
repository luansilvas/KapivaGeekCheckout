/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import br.senac.sp.model.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static utils.CustomerDataValidation.verificaCEPCaracteres;
import static utils.CustomerDataValidation.verificaLetra;

/**
 *
 * @author luans
 */
public class CreditCardValidation {

    public List<String> CheckCCData(Card c) {
        List<String> errorList = new ArrayList();
        try{
        System.out.println(c);
        if (c.getPrintedName().equals("")
                || c.getCvv().equals("")
                || c.getExp().equals("")) {
            System.out.println("Existem campos que nao foram preenchidos");
            errorList.add("Nem todos os campos foram preenchidos.");
        }
        if (!CheckCCNumber(c.getCard_number())) {
            errorList.add("O cartão inserido não é válido");
            System.out.println("cartao invalido");
        }
        if (!checkExp(c.getExp())) {
            System.out.println("Exp invalido");
            errorList.add("Data exp invalida");
        }
        if (!verificaPalavra(c.getPrintedName())) {
            System.out.println("NOME invalido");
            errorList.add("Nome de usuario invalido");
        }
        if (!verificaCVV(c.getCvv())) {
            System.out.println("CVV invalido");
            errorList.add("Código de verificação inválido.");
        }
        }catch(Exception e){
            System.out.println("ERRRO DE FORMATO HEIN");
        throw e;
        }
        return errorList;
    }

    private boolean CheckCCNumber(String ccNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(ccNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    private boolean checkExp(String date) {

        
        String month = date.substring(date.length() - 2);
        String year = date.substring(0, 4);
        System.out.println("ESSE É O MES "+ month);
        System.out.println("ESSE É O ANO "+year);
        int auxY = Integer.parseInt(year);
        int auxM = Integer.parseInt(month);
        if (auxY < 2021) {
            System.out.println("ano invalido");

            return false;
        }
        if (auxY == 2021) {
            System.out.println("É DE 2021 HEIN");
            if (auxM < 05) {
                System.out.println("VENCEU ESSE ANO JA HEIN");
                System.out.println("mes invalido");
                return false;
            }
            return true;
        }

        return true;
    }

    public static boolean verificaCVV(String cvv) {
        boolean retorno = true;
        if (cvv.length() != 3) {
            retorno = false;
        }

        for (int i = 0; i < cvv.length(); i++) {
            if (verificaCVVCaracteres(cvv.charAt(i)) == false) {
                return false;
            }

        }
        return retorno;
    }

    public static boolean verificaCVVCaracteres(char caract) {
        boolean letra = false;
        String temp = Character.toString(caract);

        Pattern pattern = Pattern.compile("[0123456789]");
        Matcher matcher = pattern.matcher(temp);
        while (matcher.find()) {
            letra = true;
        }

        return letra;
    }

    public static boolean verificaPalavra(String palavra) {
        boolean retorno = true;
        for (int i = 0; i < palavra.length(); i++) {
            if (verificaLetra(palavra.charAt(i)) == false) {
                return false;
            }

        }
        return retorno;
    }

    public static boolean verificaLetra(char caract) {
        boolean letra = false;
        String temp = Character.toString(caract);

        Pattern pattern = Pattern.compile("[qwertyuiopasdfghjklzxcvbnmQWERTYÇUIOPASDFGHJKLZXCVBN M]");
        Matcher matcher = pattern.matcher(temp);
        while (matcher.find()) {
            letra = true;
        }

        return letra;
    }
}
