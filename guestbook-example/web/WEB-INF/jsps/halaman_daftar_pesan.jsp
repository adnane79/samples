<%-- 
    Document   : halaman_daftar_pesan
    Created on : Nov 21, 2011, 11:31:37 PM
    Author     : radityo
--%>

<%@page import="entity.Pengunjung"%>
<%@page import="entity.Pesan"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Daftar Pesan</title>
    </head>
    <body>
        <h1>Sepuluh Pesan Terbaru Yang Sudah Masuk</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Email</th>
                    <th>Tanggal</th>
                    <th>Pesan</th>
                </tr>
            </thead>
            <tbody>
                <%
                Iterator<Pesan> daftarPesan = (Iterator<Pesan>) request.getAttribute("daftar_pesan");
                int no = 0;
                while(daftarPesan.hasNext()){
                    Pesan pesan = daftarPesan.next();
                    Pengunjung pengunjung = pesan.getPengunjung();
                    no++;
                %>
                <tr>
                    <td><%=(no)%></td>
                    <td><%=(pengunjung.getEmail())%></td>
                    <td><%=(pesan.getTanggal())%></td>
                    <td><%=(pesan.getPesan())%></td>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>
            <p>Jadikan Pesan Anda yang teratas! masukan pesan anda di <a href="tambah_pesan">sini</a> sekarang juga</p>
    </body>
</html>
