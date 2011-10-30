/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */
package controller;

import entity.DaftarMahasiswa;
import entity.Mahasiswa;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

/**
 *
 * @author radityo
 */
public class MyListBox extends GenericForwardComposer {

    private List<Mahasiswa> daftarMahasiswa;
    Listbox listOne;
    Button selectBtn;
    private List<Mahasiswa> selectedMahasiswa;

    public List<Mahasiswa> getSelectedMahasiswa() {
        return selectedMahasiswa;
    }

    public void setSelectedMahasiswa(List<Mahasiswa> selectedMahasiswa) {
        this.selectedMahasiswa = selectedMahasiswa;
    }

    public List<Mahasiswa> getDaftarMahasiswa() {
        if (daftarMahasiswa == null) {
            daftarMahasiswa = DaftarMahasiswa.getList();
        }
        return daftarMahasiswa;
    }

    public void setDaftarMahasiswa(List<Mahasiswa> daftarMahasiswa) {
        this.daftarMahasiswa = daftarMahasiswa;
    }

    public void onSelect$listOne(SelectEvent evt) {
        setSelectedMahasiswa(new ArrayList<Mahasiswa>());
        Set seld = evt.getSelectedItems();
        for (Object o : seld) {
            Listitem item = (Listitem) o;

            getSelectedMahasiswa().add(getDaftarMahasiswa().get(item.getIndex()));
        }
    }

    public void onClick$selectBtn() {
        List<Mahasiswa> list = getSelectedMahasiswa();
        if (list == null || list.size() != 0) {
            try {
                Messagebox.show("There is No Selected Mahasiswa");
            } catch (InterruptedException ex) {
                Logger.getLogger(MyListBox.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                String x = "Mahasiswa Yang Dipilih : \n";
                Iterator<Mahasiswa> iterator = list.iterator();
                while (iterator.hasNext()) {
                    Mahasiswa next = iterator.next();
                    x = x + "nama : " + next.getNama() + "\n";
                }

                Messagebox.show(x);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyListBox.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
