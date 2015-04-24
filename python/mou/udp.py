#!/usr/bin/env python
# -*- coding: utf-8 -*-

from threading import Thread
import os
import atexit
import socket
import sys


class udp:
	def __init__(self, port=12345):
		self.port = int(port)
		self.udp = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
		self.udp.settimeout(3)
		self.buffer = 1024
		self.raw = ''

	def send(self, ip, raw_data):
		'''send raw data'''
		self.udp.sendto(raw_data, (socket.gethostbyname(ip), self.port))

	def get_raw(self):
		'''get raw data, don't forget to bind with do_bind()'''
		self.raw = self.udp.recv(self.buffer)
		return self.raw

	def close(self):
		'''close connextion'''
		self.udp.close()
	
	def set_port(self, port):
		self.port = int(port)
		try:
			self.do_bind()
			return 0
		except socket.error:
			return 1
	
	def set_buffer(self, buffer=int(1024)):
		'''buffer should be a power of 2'''
		self.buffer = buffer

	def do_bind(self):
		'''Bind port'''
		try:
			self.udp.bind(('', self.port))
		except socket.error:
			self.udp.close()
			self.udp = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
			self.udp.bind(('', self.port))
