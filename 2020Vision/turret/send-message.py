import socket, message_pb2, sys

m = message_pb2.Message()
msg = None

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.connect(("192.168.1.101", 5810))

try:
	while True:
		cmds = input(">>> ").split(" ")

		if cmds[0] == "send" or cmds[0] == "s":
			if msg is None:
				continue
			s.sendall(bytes([len(msg)])+msg)
		elif cmds[0] == "change-v" or cmds[0] == "cv":
			m.v_angle = int(cmds[1])
			msg = m.SerializeToString()
		elif cmds[0] == "change-h" or cmds[0] == "ch":
			m.h_angle = int(cmds[1])
			msg = m.SerializeToString()
		elif cmds[0] == "change-d" or cmds[0] == "cd":
			m.distance = int(cmds[1])
			msg = m.SerializeToString()
		elif cmds[0] == "display":
			print(bytes([len(msg)])+msg)
		elif cmds[0] == "exit":
			sys.exit(0)
		else:
			print("invalid command")
except KeyboardInterrupt:
  pass
