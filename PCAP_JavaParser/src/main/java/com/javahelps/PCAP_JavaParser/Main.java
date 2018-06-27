/**
 * 
 */
package com.javahelps.PCAP_JavaParser;

/**
 * @author carl
 *
 */

import io.pkts.PacketHandler;
import io.pkts.Pcap;
import io.pkts.buffer.Buffer;
import io.pkts.packet.Packet;
import io.pkts.packet.TCPPacket;
import io.pkts.packet.UDPPacket;
import io.pkts.packet.IPPacket;
import io.pkts.packet.PCapPacket;
import io.pkts.protocol.Protocol;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        final Pcap pcap = Pcap.openStream("src/capture.pcap");

        pcap.loop(new PacketHandler() {
            @Override
            public boolean nextPacket(Packet packet) throws IOException {

                if (packet.hasProtocol(Protocol.TCP)) {

                    TCPPacket tcpPacket = (TCPPacket) packet.getPacket(Protocol.TCP);

//                    Buffer buffer = tcpPacket.getTotalLength();
//                    if (buffer != null) {
//                        System.out.println("TCP: " + buffer);
//                    }
                    System.out.println(tcpPacket.getSourceIP()+" > "+ tcpPacket.getDestinationIP() +" : " + tcpPacket.getTotalLength() + ", "+ tcpPacket.getHeaderLength());
                }
                
                if (packet.hasProtocol(Protocol.IPv4)) {

                	IPPacket ipPacket = (IPPacket) packet.getPacket(Protocol.IPv4);
             
                	 System.out.println(ipPacket.getSourceIP()+" > "+ ipPacket.getDestinationIP() +" : " + ipPacket.getTotalLength() + ", "+ ipPacket.getHeaderLength());
                }
                
                if (packet.hasProtocol(Protocol.PCAP)) {
                	

                	PCapPacket pPacket = (PCapPacket) packet.getPacket(Protocol.PCAP);
                	
                	 System.out.println(pPacket.getTotalLength());
                }
                return true;
            }
        });
    }
}
