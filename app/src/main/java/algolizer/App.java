/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package algolizer;

import processing.core.PApplet;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        Display display = new Display();
        PApplet.runSketch(new String[] {"Algolizer"}, display);
    }
}