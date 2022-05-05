//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package oussama.nahnah.archify;

import java.io.File;

public class Received {
    int id;
    String reference;
    String sender;
    String object;
    String path;
    String oldreference;
    String date;
    boolean frech = false;

    public Received(int id, String reference, String oldreference, String date, String sender, String object, String path) {
        this.id = id;
        this.reference = reference;
        this.sender = sender;
        this.object = object;
        this.date = date;
        this.oldreference = oldreference;
        File filechkeck = new File(path);
        if (filechkeck.exists()) {
            this.path = path;
        } else {
            this.path = "Il n'y a pas de document".toUpperCase();
        }

    }

    public String getOldreference() {
        return this.oldreference;
    }

    public void setOldreference(String oldreference) {
        this.oldreference = oldreference;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
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

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isFrech() {
        return this.frech;
    }

    public void setFrech(boolean frech) {
        this.frech = frech;
    }
}
