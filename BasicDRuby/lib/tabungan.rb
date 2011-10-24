# @author radityo.p.w (radityo.p.w@gmail.com)
# @version 1.0

class Tabungan

  def initialize

    @saldo = Hash.new

  end


  def nabung(penabung, jumlah)

    cek_nilai_awal penabung

    @saldo[penabung] = @saldo[penabung] + jumlah

  end


  def ambil(penarik, jumlah)

    cek_nilai_awal penarik

    if @saldo[penarik] < jumlah

      raise "jumlah duit gak cukup cuman "+@saldo[penarik]

    end

  end


  def cek_saldo(pengecek)

    cek_nilai_awal pengecek

    @saldo[pengecek]

  end

  private

  def cek_nilai_awal(key)

    if @saldo[key] == nil

      @saldo[key] = 0

    end


  end


end
