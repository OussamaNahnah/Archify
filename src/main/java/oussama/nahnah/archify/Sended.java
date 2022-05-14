//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package oussama.nahnah.archify;

import java.io.File;
import java.text.DecimalFormat;

public class Sended {
    int id;
    String reference;
    String recipient;
    String object;
    String path;
    String date;
    boolean frech = false;

    public boolean isFrech() {
        return this.frech;
    }

    public void setFrech(boolean frech) {
        this.frech = frech;
    }

    public Sended(int id, String reference, String date, String recipient, String object, String path) {
        this.id = id;
        this.reference = reference;
        this.recipient = recipient;
        this.object = object;
        this.date = date;
       File filechkeck = new File(path);
        if (filechkeck.exists()) {
            this.path = path;
        } else {
            this.path = "Il n'y a pas de document".toUpperCase();
        }
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getObject() {
        return this.object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDate(int day, int mounth, int year) {
        this.date = (new DecimalFormat("0000")).format((long)year) + "/" + (new DecimalFormat("00")).format((long)mounth) + "/" + (new DecimalFormat("00")).format((long)day);
    }
}
