import java.io.IOException;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.RaspiPin;

public class MotorController {


    public static GpioPinDigitalOutput forward1; 
    public static GpioPinDigitalOutput backward1;
    public static GpioPinPwmOutput speedPin1; 
    public static GpioPinDigitalOutput forward2; 
    public static GpioPinDigitalOutput backward2;
    public static GpioPinPwmOutput speedPin2; 
    public static GpioController gpio;

    public static int SLOWEST1 = 380;
    public static int FASTEST1 = 700;

    public static int SLOWEST2 = 420;
    public static int FASTEST2 = 700;

    public static void main(String[] args) {


        /** create gpio controller */
        gpio = GpioFactory.getInstance();

        forward1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04); //Pi4J GPIO_04 is GPIO_23
        backward1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05); //Pi4J GPIO_05 is GPIO_24
        speedPin1 = gpio.provisionPwmOutputPin(RaspiPin.GPIO_26, "SPEED", 0); //Pi4J GPIO_26 is GPIO_12 //ENA
        forward2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00); //Pi4J GPIO_00 is GPIO17_
        backward2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02); //Pi4J GPIO_02 is GPIO_27
        speedPin2 = gpio.provisionPwmOutputPin(RaspiPin.GPIO_23, "SPEED", 0); //Pi4J GPIO_23 is GPIO_13 //ENA

        /** keep program running until user aborts (CTRL-C) */
        while (true) {
          keyPressed(forward1, backward1, speedPin1);
        }
    }

    public static void keyPressed(GpioPinDigitalOutput f, GpioPinDigitalOutput b, GpioPinPwmOutput p) {
    
        int x;
        try {
            x = System.in.read();
            switch(x) {
              case 102: //moving forward
                forward1.high();
                backward1.low();
                forward2.high();
                backward2.low();
                System.out.println("--> Setting Speed to " + SLOWEST1);
                speedPin1.setPwm(SLOWEST1);
                speedPin2.setPwm(SLOWEST2);
                System.out.println("Moving Forward"); 
                break;
              case 98: //moving backwards
                forward1.low();
                backward1.high();
                forward2.low();
                backward2.high();
                System.out.println("--> Setting Speed to " + SLOWEST1);
                speedPin1.setPwm(SLOWEST2);
                speedPin2.setPwm(SLOWEST1);
                System.out.println("--> Moving Backward"); 
                break;
              case 114: //moving right
                forward1.high();
                backward1.low();
              //  forward2.low();
              //  backward2.high();
                System.out.println("--> Setting Speed to " + SLOWEST1);
                speedPin1.setPwm(SLOWEST1);
              //  speedPin2.setPwm(SLOWEST2);
                System.out.println("Turning Right"); 
                break;
              case 108: //moving left 
              //  forward1.low();
              //  backward1.high();
                forward2.high();
                backward2.low();
                System.out.println("--> Setting Speed to " + SLOWEST1);
              //  speedPin1.setPwm(SLOWEST1);
                speedPin2.setPwm(SLOWEST2);
                System.out.println("Turning Left"); 
                break;
              case 115: //stopping 
                backward1.low();
                forward1.low();
                backward2.low();
                forward2.low();
                System.out.println("--> Stop."); 
                break;
              default:
                if (x!=10)
                  System.out.println("Value not handled: " +x); 
            }
        } catch (Exception e) {
            System.out.println("You haven't entered a number!!!");
        }
    }

}
