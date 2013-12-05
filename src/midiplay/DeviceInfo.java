/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midiplay;

import javax.sound.midi.*;

/**
 *
 * @author takanolab
 */
public class DeviceInfo {
    public static void main(String[] args) {
        System.out.println("--このシステムのMIDIデバイス一覧--");
        for(MidiDevice.Info info : MidiSystem.getMidiDeviceInfo()) {
            System.out.println(info.getName() + " " + info.getVersion());
            System.out.println("供給会社名：" + info.getVendor());
            System.out.println("説明：" + info.getDescription());
            System.out.println("---");
        }
    }
}