/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Entities.PaiementStripe;
import Entities.transaction;
import GUI.HomeForm;
import Services.ServiceTransaction;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.stripe.model.Charge;
import com.stripe.model.Token;

/**
 *
 * @author Memmicha
 */
public class PaiementOrder extends BaseForm {

    public PaiementOrder(transaction cours) {

        Form f;

        setTitle("Add a new task");
        setLayout(BoxLayout.y());

        Button btnaff = new Button("Payer");

        Button anuler = new Button("Annuler");

        anuler.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                HomeForm h = new HomeForm();
                h.show();

            }
        });

        final FontImage back = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "Label", 6);

        final FontImage prx = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, "Label", 11);
        final FontImage bike = FontImage.createMaterial(FontImage.MATERIAL_DIRECTIONS_BIKE, "Label", 11);
        f = new Form("Paiement  " + cours.getMontant() + "DT");
        f.add(prx);
        f.add(bike);
        String m = String.valueOf(cours.getMontant());
        TextField montant = new TextField("", "Numéro de carte");
        montant.setText(m);
        montant.setEditable(false);
        TextField num = new TextField("", "Numéro de carte");

        TextField mois = new TextField("", "mois d'expirtion");
        TextField annee = new TextField("", "annee d'expiration");
        TextField cvv = new TextField("", "CVC");
        TextField email = new TextField("", "Adresse email");

        btnaff.addActionListener((evt) -> {
            if (num.getText() == "" || mois.getText() == "" || annee.getText() == "" || email.getText() == "") {
                Dialog.show("Erreur", "Merci de vérifier vos informations", "OK", null);
            } else if ((!email.getText().contains("@")) || (!email.getText().contains("."))) {
                Dialog.show("Erreur", "Email non valide", "OK", null);
            } else if (isNotInteger(cvv.getText())) {
                Dialog.show("Erreur", "CVC ne peut contenir que des chiffres", "OK", null);
            } else if (cvv.getText().length() != 3) {
                Dialog.show("Erreur", "CVC doit contenir 3 chiffres", "OK", null);
            } else if (num.getText().length() != 16) {
                Dialog.show("Erreur", "Code erroné ", "OK", null);

            } else {

                int mois0 = Integer.parseInt(mois.getText());
                int annee0 = Integer.parseInt(annee.getText());
                Token token = PaiementStripe.getToken("sk_test_U4WiZFgPGkHAenv7tm9TyUGo00AlNMbQDo", num.getText(), mois0, annee0, cvv.getText(), email.getText());
                if (token != null) {
                    Charge ch = PaiementStripe.ChargePayement("rk_test_oGfrFNOjpnRPklUVzjelPHgf", "usd", "tok_visa", cours.getMontant(), "sk_test_yIqEVjLUzA1vwKhr1PjhnS9I", num.getText(), mois0, annee0, cvv.getText(), email.getText());
                    ServiceTransaction lo = new ServiceTransaction();
                    lo.getInstance().addTransaction(cours);
                    Dialog.show("Succès", "Le paiement a été effectué avec succès", "OK", null);

                } else {
                    Dialog.show("Erreur", "Merci de vérifier vos informations", "OK", null);
                }
            }
        });

        addAll(num, mois, annee, cvv, email, montant, btnaff,anuler);

    }

    public static boolean isNotInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }

        return false;
    }

}
