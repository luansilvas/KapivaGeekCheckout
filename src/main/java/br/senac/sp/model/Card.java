/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.model;

/**
 *
 * @author luans
 */
public class Card extends Payment{
 
    private String card_number;

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getPrintedName() {
        return printedName;
    }

    public void setPrintedName(String printedName) {
        this.printedName = printedName;
    }
    private String cvv;
    private String exp;
    private String printedName;

    public Card(String card_number, String cvv, String exp, String printedName, String payment_way, int payment_instalments, String payment_status) {
        super(payment_way, payment_instalments, payment_status);
        this.card_number = card_number;
        this.cvv = cvv;
        this.exp = exp;
        this.printedName = printedName;
    }

    @Override
    public String toString() {
        return "Card{" + "card_number=" + card_number + ", cvv=" + cvv + ", exp=" + exp + ", printedName=" + printedName + '}';
    }
    
}
