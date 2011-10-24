# @author radityo.p.w (radityo.p.w@gmail.com)
# @version 1.0

#require 'rubygems'
#require 'ruby-debug'
require 'drb'

@tabungan = DRbObject.new nil, 'druby://:9000'
#puts tabungan.cek_saldo(:didit)
#puts "welcome"
#debugger
#puts "end"