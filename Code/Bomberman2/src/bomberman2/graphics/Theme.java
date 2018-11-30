/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.graphics;

/**
 *
 * @author Liscli
 */
public class Theme {
    private final String[] themes = {"classic","noel"};
    private static String theme;

    public String[] getThemes() {
        return themes;
    }
    public void setTheme(String theme) {
        Theme.theme = theme;
    }
    @Override
    public String toString(){
        return Theme.theme;
    }
}
