/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midiplay;

import java.io.*;
import javax.sound.midi.*;

/**
 *
 * @author takanolab
 */
class volumeTest {
    public static void main(String[] args) throws Exception {
        Receiver receiver = MidiSystem.getReceiver();
        ShortMessage chMsg = new ShortMessage();
        SysexMessage sysMsg = new SysexMessage();

        chMsg.setMessage(ShortMessage.PROGRAM_CHANGE, 7, 0);
        receiver.send(chMsg, -1);
//        chMsg.setMessage(ShortMessage.PROGRAM_CHANGE | 1, 66, 0);
//        receiver.send(chMsg, -1);

        for(byte i = 127 ; i >= 0 ;i -= 10) {
            byte[] data = new byte[] { (byte)0xF0, 0x7F, 0x7F, 4, 1, 0, i, (byte)0xF7 };
            sysMsg.setMessage(data, data.length);
            receiver.send(sysMsg, -1);

            chMsg.setMessage(ShortMessage.NOTE_ON, 60, 127);
            receiver.send(chMsg, -1);
//            chMsg.setMessage(ShortMessage.NOTE_ON | 1, 67, 127);
//            receiver.send(chMsg, -1);

            Thread.sleep(800);
        }

        System.out.println("Enter キーを押してプログラムを終了します>");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
    }
}