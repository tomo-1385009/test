/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midiplay;

import java.io.File;
import javax.sound.midi.*;

/**
 *
 * @author takanolab
 */
public class JavaSound {

    Sequencer sequencer = null;
    
    public static void main(String[] args) throws Exception {


        String fileName = "PennyLane.mid";
        File midiFile = new File(fileName);
        System.out.println("Sequence file name: " + fileName);

        // Using MidiSystem, convert the file to a sequence.
        Sequence sequence = MidiSystem.getSequence(midiFile);
        if (sequence != null) {
            // Print sequence information
            System.out.println(" length: "
                    + sequence.getTickLength() + " ticks");
            System.out.println(" duration: "
                    + sequence.getMicrosecondLength()
                    + " micro seconds");
            System.out.println(" division type: "
                    + sequence.getDivisionType());
        }
    }

    // MetaEventListener role
    public void meta(MetaMessage event) {
        if (event.getType() == 47) { // end of stream
            sequencer.stop();
            sequencer.close();
            System.exit(0);
        }
    } // meta

    public static void playChannel(MidiChannel channel,
            int[] notes, int[] velocities, int[] durations) {
        for (int i = 0; i < notes.length; i++) {
            channel.noteOn(notes[ i], velocities[ i]);
            try {
                Thread.sleep(durations[ i]);
            } catch (InterruptedException e) {
            }
        } // for
        for (int i = 0; i < notes.length; i++) {
            channel.noteOff(notes[ i]);
        }
    } // playChannel
}
