#this is a very simple example of what you can do with this library
import mou.valve as valve
from sys import argv

if (argv>2):
	ip = argv[1]
	port = argv[2]
else:
	ip = raw_input("Valve server ip: ")
	port = raw_input("Valve server port(27015): ")

Server = valve.server(ip,port)
infos = Server.info()

for info in infos:
	print info + " = " + str(infos[info])

raw_input("...")
