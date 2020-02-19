package frc.robot;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

import frc.robot.Messages.HighGoal;
import frc.robot.Messages.PowerCells;

class Server implements Runnable {
    // This is where identified target's data is stored
    public volatile static HighGoal highGoal = null;
    public volatile static PowerCells powerCells = null;
    // This is timestamp when the last packet was received
    public volatile static long lastUpdated = 0;
    private DatagramSocket server;

    Server(String host, int port) throws IOException {
        InetAddress addr = InetAddress.getByName(host);
        server = new DatagramSocket(port, addr);
    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] buffer = new byte[64];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                server.receive(packet);

                byte[] decodable = Arrays.copyOfRange(buffer, 2, buffer[0]+2);
                if (decodable.length == 0) continue;

                switch (buffer[0]) {
                    case 0:
                        highGoal = HighGoal.parseFrom(decodable);
                        break;

                    case 1:
                        powerCells = PowerCells.parseFrom(decodable);
                        break;
                }
                
                lastUpdated = System.currentTimeMillis();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}