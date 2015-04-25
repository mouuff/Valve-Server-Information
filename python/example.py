#this is a very simple example of what you can do with this library
import mou.valve as valve
from sys import argv

if (argv>1):
	Server = valve.server(*argv[1:])
else:
	ip = raw_input("Valve server ip: ")
	port = raw_input("Valve server port(27015): ")
	Server = valve.server(ip,port)

infos = Server.info()

for info in infos:
	print info + " = " + str(infos[info])

#players = Server.players()
#for player in players:
#	print player
