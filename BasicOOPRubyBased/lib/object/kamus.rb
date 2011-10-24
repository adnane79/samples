# @author radityo.p.w (radityo.p.w@gmail.com)
# @version 1.0

class Kamus

  @daftar_mata = nil

  @daftar_kaca_mata = nil


  def get_daftar_mata

    if @daftar_mata == nil

      @daftar_mata = Hash.new

      m = Mata.new

      m.is_beleken = true
      m.jenis_mata = "Mata Sakit"
      m.warna_bola_mata = "Merah"


      m1 = Mata.new
      m1.is_beleken = false
      m1.jenis_mata = "Mata Sehat"
      m1.warna_bola_mata = "biru"

      m2 = Mata.new
      m2.is_beleken = false
      m2.warna_bola_mata = "IJO"
      m2.jenis_mata = "Mata Duitan"


      @daftar_mata[:mata_sakit] = m
      @daftar_mata[:mata_duitan] = m2
      @daftar_mata[:mata_sehat] = m1

      

    end

    @daftar_mata
    
  end


  def get_daftar_kaca_mata

    if @daftar_kaca_mata == nil


      @daftar_kaca_mata = Hash.new

      km = KacaMata.new

      km.warna = "merah"

      km.merk = "Kaca Mata Kijang"

      km.harga = "3000"



      km1 = KacaMata.new

      km1.warna = "putih"

      km1.merk = "Kaca Mata Kuda"

      km1.harga = "40000"


      km2 = KacaMata.new

      km2.warna = "hitam"

      km2.merk = "Kaca Mata Orang"

      km2.harga = "50000"


      @daftar_kaca_mata[:kaca_mata_orang] = km2

      @daftar_kaca_mata[:kaca_mata_kuda] = km1

      @daftar_kaca_mata[:kaca_mata_kijang] = km


    end


    @daftar_kaca_mata
    

  end


end
