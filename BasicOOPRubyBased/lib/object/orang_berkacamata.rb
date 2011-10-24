# @author radityo.p.w (radityo.p.w@gmail.com)
# @version 1.0

class OrangBerkacamata < Orang

  attr_accessor :kaca_mata

  def initialize

    super

    @daftar_kaca_mata = Kamus.new.get_daftar_kaca_mata

    @kaca_mata = @daftar_kaca_mata[:kaca_mata_orang]
    

  end


  def marah

    super

    @kaca_mata = @daftar_kaca_mata[:kaca_mata_kuda]

    teriak_sesuatu "hihihi"

  end



end
