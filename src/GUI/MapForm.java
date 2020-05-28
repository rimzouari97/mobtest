/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.material;
import Services.ServiceMaterial;

import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;

import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;

import com.codename1.ui.Toolbar;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;

import com.codename1.uikit.cleanmodern.BaseForm;

import java.util.ArrayList;


/**
 *
 * @author Memmicha
 */
public class MapForm extends BaseForm{

    Form F;
    Container B;

    public MapForm() {
        F = new Form("Stades");
        //Components.showHamburger(F);
        B = new Container(new TableLayout(12, 2));
        ServiceMaterial SM = new ServiceMaterial();
        ArrayList<material> LS = SM.getAllMaterial();
        for (material stade : LS) {
            
            Button S1 = new Button("   " + stade.getName_address());
            //Action Button
            S1.addActionListener(ee -> {
                Form f2 = new Form("Details", new TableLayout(3, 1));
                Toolbar tb = f2.getToolbar();
                tb.addMaterialCommandToRightBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
                    this.getF().show();
                });
                SpanLabel l = new SpanLabel("Ville : " + stade.getAddress());
                l.setUIID("CaseMatch");
                Button lb = new Button("Voir stade sur la carte");
                lb.addActionListener(e -> {
                    String HTML_API_KEY = "AIzaSyBEDfNcQRmKQEyulDN8nGWjLYPm8s4YB58";
                    Form hi = new Form("Google Maps", new BorderLayout());
                    hi.getToolbar().addMaterialCommandToRightBar("Back", FontImage.MATERIAL_ARROW_BACK, e1 -> {
                        f2.show();
                    });
                    com.codename1.googlemaps.MapContainer cnt = new com.codename1.googlemaps.MapContainer(HTML_API_KEY);
                    Style s = new Style();
                    s.setFgColor(0xff0000);
                    s.setBgTransparency(0);
                    FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(1));

                    final boolean[] init = {false};

                    cnt.addMapListener((e1, e2, e3) -> {
                        if (!init[0]) {
                            if (stade.getId() == 1) {
                                Coord coord = new Coord(54.6982, 20.5339);
                                cnt.zoom(coord, 13);
                                cnt.addMarker(EncodedImage.createFromImage(markerImg, false), coord, "Description : " + stade.getDescription(), "Text", evt -> {
                                    ToastBar.showMessage(stade.getName_address() + "                       Ville :" + stade.getAddress(), FontImage.MATERIAL_PLACE);
                                });
                                init[0] = true;
                            }
                            if (stade.getId() == 2) {
                                Coord coord = new Coord(48.7345, 44.5483);
                                cnt.zoom(coord, 13);
                                cnt.addMarker(EncodedImage.createFromImage(markerImg, false), coord, "Description : " + stade.getDescription(), "Text", evt -> {
                                    ToastBar.showMessage(stade.getName_address() + "        Ville :" + stade.getAddress(), FontImage.MATERIAL_PLACE);
                                });
                                init[0] = true;
                            }
                            if (stade.getId() == 3) {
                                Coord coord = new Coord(53.2781, 50.2389);
                                cnt.zoom(coord, 13);
                                cnt.addMarker(EncodedImage.createFromImage(markerImg, false), coord, "Description : " + stade.getDescription(), "Text", evt -> {
                                    ToastBar.showMessage(stade.getName_address() + "                          Ville :" + stade.getAddress(), FontImage.MATERIAL_PLACE);
                                });
                                init[0] = true;
                            }
                            if (stade.getId() == 4) {
                                Coord coord = new Coord(55.8210, 49.1610);
                                cnt.zoom(coord, 13);
                                cnt.addMarker(EncodedImage.createFromImage(markerImg, false), coord, "Description : " + stade.getDescription(), "Text", evt -> {
                                    ToastBar.showMessage(stade.getName_address() + "                          Ville :" + stade.getAddress(), FontImage.MATERIAL_PLACE);
                                });
                                init[0] = true;
                            }

                        }
                    });

                    Container root = LayeredLayout.encloseIn(
                            BorderLayout.center(cnt)
                    );

                    hi.add(BorderLayout.CENTER, root);

                    hi.show();
                });
    
            });
                    }    
    }
    
    public Form getF() {
        return F;
    }

    public void setF(Form F) {
        this.F = F;
    }
}
