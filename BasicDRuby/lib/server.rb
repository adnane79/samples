#!/usr/bin/env ruby
# @author radityo.p.w (radityo.p.w@gmail.com)
# @version 1.0
$:.unshift File.join(File.expand_path(File.dirname(__FILE__)))
require 'drb'
require 'tabungan'

DRb.start_service 'druby://:9000', Tabungan.new
puts "Server running at #{DRb.uri}"

trap("INT") { DRb.stop_service }
DRb.thread.join