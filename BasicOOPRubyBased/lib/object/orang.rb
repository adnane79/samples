# @author radityo.p.w (radityo.p.w@gmail.com)
# @version 1.0

class Orang

  attr_accessor :mulut, :kulit, :jenis_kelamin, :mata, :rambut, :nama


  def initialize

    @nama = "pendekar syair berdarah"

    @mulut = :tersenyum

    @kulit = :putih

    @jenis_kelamin = :pria

    @rambut = :hitam

    @daftar_mata = Kamus.new.get_daftar_mata

    @mata = @daftar_mata[:mata_sehat]

  end


  def mengecat_rambut

    @rambut = :putih

  end

  def mengecat_rambut(params)

    @rambut = params

  end


  def marah

    @mulut = :cemberut

    @mata = @daftar_mata[:mata_sakit]

  end


  def menendang(orang)

    teriak_sesuatu "GUa Tendang Loh "+orang.nama

    orang.kesakitan

  end

  def kesakitan

    @mata = @daftar_mata[:mata_sakit]

    teriak_sesuatu "Gile Luuu"

  end


  def teriak_sesuatu(sesuatu)

    teriak = @nama+" said : "+sesuatu

    puts teriak

  end
  
end
