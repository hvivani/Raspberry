import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.RaspiPin;
 
public class Motor {
 
    public static void main(String[] args) {
    try {
        /** create gpio controller */
        final GpioController gpio = GpioFactory.getInstance();

        final int SLOWEST = 310;
        final int FASTEST = 500;
 
        final GpioPinDigitalOutput forward = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04); //Pi4J GPIO_04 is GPIO_23
        final GpioPinDigitalOutput backward = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05); //Pi4J GPIO_05 is GPIO_24
     //   final GpioPinDigitalOutput motor = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06); //Pi4J GPIO_06 is GPIO_25 // ENA
     //   final GpioPinPwmOutput speedPin = gpio.provisionPwmOutputPin(RaspiPin.GPIO_23, "SPEED", 0); //Pi4J GPIO_23 is GPIO_13 //ENA
        final GpioPinPwmOutput speedPin = gpio.provisionPwmOutputPin(RaspiPin.GPIO_26, "SPEED", 0); //Pi4J GPIO_26 is GPIO_12 //ENA

        int milliseconds=5000;
     //   speedPin.setShutdownOptions(true); 
     //   motor.high();
        System.out.println("--> Engine ON");
        Thread.sleep(milliseconds);
        forward.high();
        backward.low();
        System.out.println("--> Setting Speed");
        speedPin.setPwm(550);
        System.out.println("--> Engine Rotating Forward");
        Thread.sleep(milliseconds);
        forward.low();
        System.out.println("--> Forward Stop");
        Thread.sleep(milliseconds);

        backward.high();
        forward.low();
        speedPin.setPwm(550);
        System.out.println("--> Engine Rotating Backward");
        Thread.sleep(milliseconds);

        backward.low();
        forward.low();
        System.out.println("--> Backward Stop");
        Thread.sleep(milliseconds);
     //   motor.low();
        System.out.println("--> Engine Off");

 
        /** keep program running until user aborts (CTRL-C) */
        //while (true) {
         // Thread.sleep(500);
          //System.out.println("sleeping now for 500 milliseconds...while Gianluca, mama, papa and Fiorella is watching...");
        //}
     
        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpio.shutdown();
 
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
