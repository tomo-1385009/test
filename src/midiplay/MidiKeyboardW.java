/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midiplay;

import javax.sound.midi.*;

//
// 2010.12.23
// Windows 7で音を鳴らすことができた．
//

public class MidiKeyboardW implements Receiver
{
    static final int DEVICE_IN  = 0;
    static final int DEVICE_OUT = 1;//ここを修正、私の場合1or2
    private MidiDevice.Info[] info;
    private MidiDevice in_device = null;
    private MidiDevice out_device = null;
    
    private static final String[]	sm_astrKeyNames = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    private static final String[]	sm_astrKeySignatures = {"Cb", "Gb", "Db", "Ab", "Eb", "Bb", "F", "C", "G", "D", "A", "E", "B", "F#", "C#"};

    public static void main(String[] args) {
        new MidiKeyboardW();
    }

    public MidiKeyboardW() {
        info = MidiSystem.getMidiDeviceInfo();
        MidiDevice dev = null;

        try {
            dev = MidiSystem.getMidiDevice(info[DEVICE_IN]);
        } catch (Throwable t) {
        }

        connectMidiIn(dev);

        try {
            dev = MidiSystem.getMidiDevice(info[DEVICE_OUT]);
        } catch (Throwable t) {
        }
        connectMidiOut(dev);

    }

    private void connectMidiIn(MidiDevice device) {

        try {

            device.open();
            Transmitter tx = device.getTransmitter();
            tx.setReceiver(this);
            in_device = device;

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void connectMidiOut(MidiDevice device) {
        try {
            device.open();
            out_device = device;
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void close() {
    }

    public void send(MidiMessage message, long timeStamp) {

        if (message instanceof ShortMessage) {
            ShortMessage sm = (ShortMessage) message;
            switch (sm.getCommand()) {
                case ShortMessage.NOTE_ON:
                    System.out.println(sm.getData1() + " " + getKeyName(sm.getData1()));
                    break;
                //case ShortMessage.NOTE_OFF:
                //break;
                default:
            }
        }

        try {
            out_device.getReceiver().send(message, timeStamp);
        } catch (MidiUnavailableException e) {
        }

    }
    
    	public static String getKeyName(int nKeyNumber)
	{
		if (nKeyNumber > 127)
		{
			return "illegal value";
		}
		else
		{
			int	nNote = nKeyNumber % 12;
			int	nOctave = nKeyNumber / 12;
			return sm_astrKeyNames[nNote] + (nOctave - 1);
		}
	}
}
