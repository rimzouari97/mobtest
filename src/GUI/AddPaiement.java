/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.PaiementStripe;
import Entities.TwilioSms;
import Entities.transaction;
import Entities.user;
import Services.ServiceTransaction;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import static com.codename1.io.Log.e;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codename1.uikit.cleanmodern.NewsfeedForm;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import static com.codename1.uikit.cleanmodern.PaiementOrder.isNotInteger;

/**
 *
 * @author Memmicha
 */
public class AddPaiement extends BaseForm{

    public AddPaiement(Form previous, transaction cours, Resources res,user u) {

        Form f;
        Button btnaff = new Button("Payer");
        Button anuler = new Button("Annuler");

        anuler.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                HomeForm h = new HomeForm();
                h.current.show();

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

                Token token = PaiementStripe.getToken("pk_test_H1Bs8cGiQgEBTovDlNEfAm3x00wgQt3aQK", num.getText(), mois0, annee0, cvv.getText(), email.getText());
                if (token != null) {
                    Charge ch = PaiementStripe.ChargePayement("rk_test_oGfrFNOjpnRPklUVzjelPHgf", "usd", "tok_visa", cours.getMontant(), "sk_test_yIqEVjLUzA1vwKhr1PjhnS9I", num.getText(), mois0, annee0, cvv.getText(), email.getText());
                    ServiceTransaction lo = new ServiceTransaction();
                    lo.getInstance().addTransaction(cours);
                    Dialog.show("Succès", "Le paiement a été effectué avec succès", "OK", null);
                  TwilioSms a = new TwilioSms();
                  a.sendSms("Transaction effectuee");
                   new NewsfeedForm(res).show();

                } else {
                    Dialog.show("Erreur", "Merci de vérifier vos informations", "OK", null);
                   new NewsfeedForm(res).show();
                }
            }
        });

        addAll(num, mois, annee, cvv, email, montant, btnaff);
        /*
            Form hi = new Form("PDF Viewer", BoxLayout.y());
            Button devGuide = new Button("Show PDF");
            devGuide.addActionListener(e -> {
            FileSystemStorage fs = FileSystemStorage.getInstance();
            String fileName = fs.getAppHomePath() + "pdf-sample.pdf";
             if(!fs.exists(fileName)) {
               Util.downloadUrlToFile("http://www.polyu.edu.hk/iaee/files/pdf-sample.pdf", fileName, true);
       }
           Display.getInstance().execute(fileName);
       });
       hi.add(devGuide);
       hi.show();*/
    }

    
     private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }

       
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    } 
     private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                
            }
        });
    }
}
