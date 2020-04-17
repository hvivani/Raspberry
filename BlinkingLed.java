import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;
 
public class BlinkingLed {
 
    public static void main(String[] args) {
    try {
        /** create gpio controller */
        final GpioController gpio = GpioFactory.getInstance();
 
        final GpioPinDigitalOutput ledPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29); //Pi4J GPIO_29 is GPIO_21
 
        /** Blink every second during 15 seconds*/
        //ledPin.blink(1000, 15000);

        int milliseconds=3000;
        
        /** Blink every second*/
        ledPin.blink(milliseconds);
 
        /** keep program running until user aborts (CTRL-C) */
        while (true) {
          Thread.sleep(500);
          System.out.println("sleeping now for 500 milliseconds...while Gianluca, mama, papa and Fiorella is watching...");
        }
 
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
