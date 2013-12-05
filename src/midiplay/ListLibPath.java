/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midiplay;

/**
 *
 * @author takanolab
 */
public class ListLibPath {
public static void main(String[] args) {
for (String path : System.getProperty("java.library.path").split(";"))
System.out.println(path);
}
}