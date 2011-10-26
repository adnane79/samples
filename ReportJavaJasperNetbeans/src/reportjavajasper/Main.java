/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package reportjavajasper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import objects.Item;
import objects.Transaction;
import report1.Report1Data;

/**
 *
 * @author radityo
 */
public class Main {


    private static ArrayList<Item> daftarItem;

    private static ArrayList<Transaction> daftarTransaksi;

    private static ArrayList<Report1Data> daftarData;

    private static double total = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        initData();
        
        prepareData();

        processingData();

        reportingData();

    }


    public static void prepareData(){

        //menyiapkan data item

        Item item1 = new Item();
        item1.setNama("item1");
        item1.setHarga(3000);
        item1.setNoId("0001");

        Item item2 = new Item();
        item2.setNama("item2");
        item2.setHarga(6000);
        item2.setNoId("0002");


        daftarItem.add(item1);
        daftarItem.add(item2);

        //menyiapkan data transaksi

        Calendar c = Calendar.getInstance();

        Transaction t1 = new Transaction();
        t1.setItem(item1);
        t1.setTanggal(Calendar.getInstance().getTime());
        t1.setJumlah(100);

        Transaction t2 = new Transaction();
        t2.setItem(item1);
        c.set(2011, 10, 3);
        t2.setTanggal(c.getTime());
        t2.setJumlah(1000);

        Transaction t3 = new Transaction();
        t3.setItem(item2);
        t3.setJumlah(10000);
        t3.setTanggal(c.getTime());

        daftarTransaksi.add(t1);
        daftarTransaksi.add(t2);
        daftarTransaksi.add(t3);

        
    }

    private static void processingData() {
        //melakukan summary data
        HashMap<String,Report1Data> tempData = new HashMap<String,Report1Data>();
        Iterator<Transaction> iteratorTrans = daftarTransaksi.iterator();
        while(iteratorTrans.hasNext()){
            Transaction t = iteratorTrans.next();
            Report1Data rd = null;
            if(tempData.containsKey(t.getItem().getNama())){
                rd = tempData.get(t.getItem().getNama());
            }else{
                rd = new Report1Data();
                tempData.put(t.getItem().getNama(), rd);
                rd.setJumlah(0);
                rd.setTotal(0.00);
                rd.setNama(t.getItem().getNama());
                rd.setHarga(t.getItem().getHarga());
            }

            rd.setJumlah(rd.getJumlah() + t.getJumlah());
            rd.setTotal(rd.getHarga() * rd.getJumlah());

        }
        // memasukkan dalam daftarData dan memberi No, serta mengupdate total harga
        Iterator<Report1Data> iteratorData = tempData.values().iterator();
        int iterator = -1;
        while(iteratorData.hasNext()){
            iterator++;
            Report1Data data = iteratorData.next();
            data.setNo(""+iterator);
            daftarData.add(data);
            total+=data.getHarga();
        }
    }

    private static void reportingData() {
        try {
            //membuat parameter untuk report
            Map parameters = new HashMap();
            parameters.put("TITLE", "REPORT COBA COBA");
            parameters.put("TOTAL", total);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport("report1.jasper", parameters, new JRBeanCollectionDataSource(daftarData));

            JasperExportManager.exportReportToPdfFile(jasperPrint,"report1.pdf");

            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "report1.xls" );
            exporter.exportReport();

        } catch (JRException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static void initData() {
        total = 0;
        daftarItem = new ArrayList<Item>();
        daftarTransaksi = new ArrayList<Transaction>();
        daftarData = new ArrayList<Report1Data>();
    }

}
