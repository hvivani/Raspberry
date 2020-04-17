import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)
led = 21
GPIO.setup(led, GPIO.OUT)
# Switch on
GPIO.output(led, 1)
time.sleep(2)
# Switch off
GPIO.output(led, 0)
