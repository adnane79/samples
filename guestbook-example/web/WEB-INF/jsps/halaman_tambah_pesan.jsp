<%-- 
    Document   : halaman_tambah_pesan
    Created on : Nov 21, 2011, 8:46:41 PM
    Author     : radityo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Halaman Tambah Pesan</title>
    </head>
    <body>
        <h2><%=(request.getAttribute("message"))%></h2>
        <form action="tambah_pesan" method="POST">
            <label>Email : </label><br />
            <input type="text" name="email" value="" /> <input type="hidden" name="act" value="add" /><br />
            <label>Pesan : </label><br />
            <textarea name="pesan" rows="4" cols="20">
            </textarea><br/>
            <input type="submit" value="isi pesan" />
        </form><br/>
        <a href="daftar_pesan">Kembali Ke Daftar Pesan</a>
    </body>
</html>
