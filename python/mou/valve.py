import struct
import udp

class server:
	INFO = '\xff\xff\xff\xffTSource Engine Query\x00'
	
	CHALLENGE = '\xff\xff\xff\xff\x55\xff\xff\xff\xff'
	
	def __init__(self, ip, port="27015"):
		self.udp = udp.udp(port)
		self.ip = ip
		
	def players(self):
		'''returns all players names kills time etc.'''
		self.udp.send(self.ip, self.CHALLENGE)
		challenge = self.udp.get_raw()
		id = challenge[5:]
		req = "\xff\xff\xff\xff\x55"+id
		self.udp.send(self.ip, req)
		raw = self.udp.get_raw()
		raw = raw.replace('\xff\xff\xff\xff','')
		
		players = ord(raw[1])
		
		raw = raw[3:]
		
		content = list()
		
		for x in xrange(int(players)):
			player = dict()
			#name
			end = raw.find('\x00')
			player['name'] = raw[:end]
			raw = raw[end+1:]
			#bytes
			end = 8
			infos = struct.unpack('lf',raw[:end])
			player['score'] = infos[0]
			player['time'] = infos[1]
			raw = raw[end+1:]
			
			content.append(player)
		
		return content
	
	def info(self):
		'''returns all server info under a dict'''
		self.udp.send(self.ip, self.INFO)
		raw = self.udp.get_raw()
		
		content = dict()
		strings = ['name','map','folder','game']
		
		bytes = ['gameid','players','maxplayers','bots','type', 'env']
		ishex = [0,1,1,1,0,0]
		#2 lists is easier than using a dict in this case
		
		raw = raw.replace('\xff\xff\xff\xff','')
		
		content['protocol'] = ord(raw[1])
		
		raw = raw[2:]
		
		for string in strings:
			end = raw.find('\x00')
			content[string] = raw[:end]
			raw = raw[end+1:]
			#+1 for the \x00
		raw = raw[:7]
		
		data = struct.unpack('hccccc',raw)
		
		
		for x in xrange(len(bytes)):
			if (ishex[x]):
				value = ord(data[x])
			else:
				value = data[x]
			
			content[bytes[x]] = value
		
		
		return content

